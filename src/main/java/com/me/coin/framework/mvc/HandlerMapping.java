package com.me.coin.framework.mvc;

import javax.servlet.http.HttpServletRequest;

/**
 * 处理器映射
 * @author dwl
 *
 */
public interface HandlerMapping {
	
	
	ActionHandler getHandler(HttpServletRequest request);

}
