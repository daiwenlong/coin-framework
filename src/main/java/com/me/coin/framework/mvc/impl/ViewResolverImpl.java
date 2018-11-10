package com.me.coin.framework.mvc.impl;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.me.coin.framework.Constants;
import com.me.coin.framework.mvc.Result;
import com.me.coin.framework.mvc.View;
import com.me.coin.framework.mvc.ViewResolver;
import com.me.coin.framework.util.PropertyUtils;

public class ViewResolverImpl implements ViewResolver {

	@Override
	public void resolveView(HttpServletRequest request, HttpServletResponse response, Result result) {
		View view = result.getView();
		if ("json".equals(view.getName())) {
			try {
				// 指定内容类型为 JSON 格式
				response.setContentType("application/json"); 
				// 防止中文乱码
				response.setCharacterEncoding("utf-8");
				// 向响应中写入数据
				PrintWriter writer = response.getWriter();
				writer.write(JSONObject.toJSONString(result.getData())); 
				writer.flush();
				writer.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}else{
			try {
				//传递数据
				result.getData().forEach((k,v)->{
					request.setAttribute(k, v);
				});
				String pathPrefix = PropertyUtils.getProperty(Constants.JSP_PATH, "/WEB-INF/jsp/");
				request.getRequestDispatcher(pathPrefix+result.getJspPath()).forward(request, response);
			} catch (Exception e) {
				throw new RuntimeException(e);
			} 
		}

	}

}
