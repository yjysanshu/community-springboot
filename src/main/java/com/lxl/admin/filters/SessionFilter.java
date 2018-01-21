package com.lxl.admin.filters;

import com.lxl.common.consts.HeaderConst;
import com.lxl.common.util.HeaderUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SessionFilter implements Filter {

    /**
     * 封装，不需要过滤的list列表
     */
    protected static List<Pattern> patterns = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        patterns.add(Pattern.compile("site/index"));
        patterns.add(Pattern.compile("favicon.ico"));
        System.out.println("自定义的过滤器init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("自定义的过滤器doFilter");
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String url = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        if (url.startsWith("/") && url.length() > 1) {
            url = url.substring(1);
        }

        if (isInclude(url)){
            filterChain.doFilter(httpRequest, httpResponse);
            return;
        } else {
            System.out.println(HeaderUtil.getHeader(httpRequest, HeaderConst.X_TOKEN));
            HttpSession session = httpRequest.getSession();
            if (session.getAttribute("") != null){
                // session存在
                filterChain.doFilter(httpRequest, httpResponse);
                return;
            } else {
                // session不存在 准备跳转失败
                /* RequestDispatcher dispatcher = request.getRequestDispatcher(path);
                    dispatcher.forward(request, response);*/
                filterChain.doFilter(httpRequest, httpResponse);
                return;
            }
        }
    }

    @Override
    public void destroy() {
        System.out.println("自定义的过滤器destroy");
    }

    /**
     * 是否需要过滤
     * @param url
     * @return
     */
    private boolean isInclude(String url) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(url);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }
}
