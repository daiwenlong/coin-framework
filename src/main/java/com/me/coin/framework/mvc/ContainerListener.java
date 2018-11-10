package com.me.coin.framework.mvc;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

import com.me.coin.framework.Constants;
import com.me.coin.framework.util.PropertyUtils;

/**
 * 容器监听
 * @author dwl
 *
 */
@WebListener
public class ContainerListener implements ServletContextListener {

    /**
     * 容器初始化时调用
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        // 添加 Servlet 映射
        addServletMapping(servletContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

    private void addServletMapping(ServletContext context) {
        // 注册静态资源
        registerDefaultServlet(context);
        // 注册jsp
        registerJspServlet(context);
    }

    private void registerDefaultServlet(ServletContext context) {
        ServletRegistration defaultServlet = context.getServletRegistration("default");
        defaultServlet.addMapping("/index.html");
        String staticPath = PropertyUtils.getProperty(Constants.STATIC_PATH, "/static/");
        defaultServlet.addMapping(staticPath + "*");
    }

    private void registerJspServlet(ServletContext context) {
        ServletRegistration jspServlet = context.getServletRegistration("jsp");
        jspServlet.addMapping("/index.jsp");
        String jspPath = PropertyUtils.getProperty(Constants.JSP_PATH, "/WEB-INF/jsp/");
        jspServlet.addMapping(jspPath + "*");
    }

   

    
}
