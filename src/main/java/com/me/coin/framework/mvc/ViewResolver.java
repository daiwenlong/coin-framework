package com.me.coin.framework.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 视图解析
 * @author dwl
 *
 */
public interface ViewResolver {
	
	void resolveView(HttpServletRequest request,HttpServletResponse response,Result result);

}
