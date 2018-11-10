package com.me.coin.framework.mvc;

import javax.servlet.http.HttpServletRequest;

/**
 * 处理器调用
 * @author dwl
 *
 */
public interface HandlerInvoker {
	
	Object invokerHandler(HttpServletRequest request, ActionHandler handler) throws Exception; 

}
