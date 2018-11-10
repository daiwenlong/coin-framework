package com.me.coin.framework.mvc.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.me.coin.framework.mvc.ActionHandler;
import com.me.coin.framework.mvc.HandlerInvoker;
import com.me.coin.framework.mvc.Mvcs;
import com.me.coin.framework.mvc.ParamNotAnnoException;
import com.me.coin.framework.mvc.annotation.Entity;
import com.me.coin.framework.mvc.annotation.Param;
import com.me.coin.framework.util.ClassUtils;

public class HandlerInvokerImpl implements HandlerInvoker{

	@Override
	public Object invokerHandler(HttpServletRequest request, ActionHandler handler) throws Exception {
		Class<?> actionClass = handler.getActClazz();
		Method method = handler.getMethod();
		//从容器中获取实例对象
		Object action = Mvcs.getIoc().getBean(actionClass);
		List<Object> params = getParamsList(request,method);
		method.setAccessible(true);
		Object result = method.invoke(action, params.toArray());
		return result;
	}
	
	
	/**
	 * 获取参数列表
	 * @param request
	 * @param method
	 * @return
	 */
	private List<Object> getParamsList(HttpServletRequest request, Method method){
		List<Object> params = new ArrayList<>();
		Parameter[] parameters = method.getParameters(); 
		for(Parameter parameter:parameters){
			Param param = parameter.getAnnotation(Param.class);
			Entity entity = parameter.getAnnotation(Entity.class);
			if(entity == null){
				if(null == param)
					throw new ParamNotAnnoException(method.getName()+"方法中参数未@Param");
				params.add(castParam(parameter.getType(),request.getParameter(param.value())));
			}else{
				params.add(castParam(parameter.getType(),entity.value(),request));
			}
			
		}
		return params;
	}
	
	
	/**
	 * 处理常见类型参数
	 * @return
	 */
	private Object castParam(Class<?> type, String param){
		Object obj = null;
		if(ClassUtils.isDouble(type)){
			obj = Double.parseDouble(param);
		}else if(ClassUtils.isInt(type)){
			obj = Integer.parseInt(param);
		}else if(ClassUtils.isLong(type)){
			obj = Long.parseLong(param);
		}else{
			obj = param;
		}
		return obj;
	}
	
	/**
	 * 处理对象类型参数
	 * @param type
	 * @param prefix 前缀
	 * @param request
	 * @return
	 */
	private Object castParam(Class<?> type, String prefix, HttpServletRequest request){
		Field[] fields = type.getDeclaredFields();
		Map<String, Object> map = new HashMap<>();
		for(Field field:fields){
			map.put(field.getName(), request.getParameter(prefix+field.getName()));
		}
		return JSONObject.parseObject(JSONObject.toJSONString(map), type);
	}

}
