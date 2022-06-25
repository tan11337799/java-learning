package com.twhupup.myssm.myspringmvc;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewBaseServlet extends HttpServlet {

    private TemplateEngine templateEngine;

    // @Override
    public void init() throws ServletException {
        // 1.获取ServletContext对象
        ServletContext servletContext = this.getServletContext();
        // 2.创建Thymeleaf解析器对象
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
        // 3.给解析器对象设置参数
        templateResolver.setTemplateMode(TemplateMode.HTML);// 设置模板模式，HTML是默认模式
        templateResolver.setPrefix(servletContext.getInitParameter("view-prefix"));// 设置前缀
        templateResolver.setSuffix(servletContext.getInitParameter("view-suffix"));// 设置后缀
        templateResolver.setCacheTTLMs(60000L);// 设置缓存过期时间（毫秒）
        templateResolver.setCacheable(true);// 设置是否缓存
        templateResolver.setCharacterEncoding("utf-8");        // 设置服务器端编码方式
        // 4.创建模板引擎对象
        templateEngine = new TemplateEngine();
        // 5.给模板引擎对象设置模板解析器
        templateEngine.setTemplateResolver(templateResolver);
    }

    protected void processTemplate(String templateName, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 1.设置响应体内容类型和字符集
        resp.setContentType("text/html;charset=UTF-8");
        // 2.创建WebContext对象
        WebContext webContext = new WebContext(req, resp, getServletContext());
        // 3.处理模板数据
        templateEngine.process(templateName, webContext, resp.getWriter());
    }
}