package com.me.coin.framework.mvc;

import javax.servlet.http.HttpServletRequest;

/**
 * 处理器y映射
 * @author dwl
 *
 */
public interface HandlerMapping {
	
	
	ActionHandler getHandler(HttpServletRequest request);

}
