package com.me.coin.framework.mvc.impl;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.me.coin.framework.Constants;
import com.me.coin.framework.mvc.ActionHandler;
import com.me.coin.framework.mvc.HandlerMapping;
import com.me.coin.framework.mvc.NotFoundActionException;
import com.me.coin.framework.mvc.annotation.Act;
import com.me.coin.framework.mvc.annotation.Request;
import com.me.coin.framework.util.ClassHelper;
import com.me.coin.framework.util.PropertyUtils;
import com.me.coin.framework.util.Strings;

public class HandlerMappingImpl implements HandlerMapping{
	
	private static Map<String, ActionHandler> actMap = new HashMap<>();
	
	//初始化
	static {
		List<Class<?>> classes = ClassHelper.findClassBypackageName(PropertyUtils.getPropertyArray((Constants.ACT_PACJAGE)));
		classes.forEach(clazz->{
			if(clazz.isAnnotationPresent(Act.class)){
				String classReq = "";
				if(clazz.isAnnotationPresent(Request.class)){
					Request request = clazz.getAnnotation(Request.class);
				    classReq = request.value();
				    if(Strings.isEmpty(classReq))
				    	classReq = "/"+Strings.lowerFirst(clazz.getSimpleName());
				}
				Method methods[] = clazz.getDeclaredMethods();
				for(Method method : methods){
					if(method.isAnnotationPresent(Request.class)){
						Request mRequest = method.getAnnotation(Request.class);
						String mReq = mRequest.value();
						if(Strings.isEmpty(mReq))
							mReq = "/"+Strings.lowerFirst(method.getName());
						actMap.put(classReq+mReq, new ActionHandler(clazz, method));
					}
				}
			}
		});
	}

	@Override
	public ActionHandler getHandler(HttpServletRequest request) {
		String path = request.getServletPath() + request.getPathInfo();
		if(path.endsWith("/"))
			path = path.substring(0, path.length() - 1);
		if(!actMap.containsKey(path))
			throw new NotFoundActionException(String.format("找不到处理该路径的action：'%s'", path));
		return actMap.get(path);
	}

}
