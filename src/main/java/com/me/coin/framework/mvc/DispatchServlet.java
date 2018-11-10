package com.me.coin.framework.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.me.coin.framework.mvc.impl.HandlerInvokerImpl;
import com.me.coin.framework.mvc.impl.HandlerMappingImpl;
import com.me.coin.framework.mvc.impl.ViewResolverImpl;

/**
 * 前端控制器
 * 
 * @author dwl
 *
 */
public class DispatchServlet extends HttpServlet{

	
	private static final long serialVersionUID = 1L;
	
	//处理器映射
    private HandlerMapping handlerMapping;
	//处理器调用
	private HandlerInvoker handlerInvoker;
	//视图解析
	private ViewResolver viewResolver;
	
	@Override
	public void init() throws ServletException {
		handlerMapping = new HandlerMappingImpl();
		handlerInvoker = new HandlerInvokerImpl();
		viewResolver = new ViewResolverImpl();
		Mvcs.init();
	}
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ActionHandler handler = handlerMapping.getHandler(req);
		try {
			Result result = (Result) handlerInvoker.invokerHandler(req, handler);
			viewResolver.resolveView(req, resp, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
