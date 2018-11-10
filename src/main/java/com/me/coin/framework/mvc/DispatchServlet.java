package com.me.coin.framework.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.me.coin.framework.mvc.impl.HandlerInvokerImpl;
import com.me.coin.framework.mvc.impl.HandlerMappingImpl;

/**
 * 前端控制器
 * 
 * @author dwl
 *
 */
public class DispatchServlet extends HttpServlet{

	
	private static final long serialVersionUID = 1L;
	
	
    private HandlerMapping handlerMapping;
	
	private HandlerInvoker handlerInvoker;
	
	@Override
	public void init() throws ServletException {
		handlerMapping = new HandlerMappingImpl();
		handlerInvoker = new HandlerInvokerImpl();
		Mvcs.init();
	}
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ActionHandler handler = handlerMapping.getHandler(req);
		try {
			Object object = handlerInvoker.invokerHandler(req, handler);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void destroy() {
		super.destroy();
	}

}
