package com.example.filter;

import com.example.model.UserSession;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthenticationFilter implements Filter {
    
    // Danh sách các URL không cần đăng nhập
    private static final List<String> EXCLUDED_PATHS = Arrays.asList(
        "/login.do",
        "/loginShow.do", 
        "/logout.do",
        "/index.jsp",
        "/css/",
        "/js/",
        "/images/"
    );
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code if needed
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        String requestURI = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();
        String path = requestURI.substring(contextPath.length());
        
        // Kiểm tra xem có phải URL được loại trừ không
        boolean isExcluded = EXCLUDED_PATHS.stream().anyMatch(excluded -> 
            path.startsWith(excluded) || path.equals("/") || path.isEmpty());
        
        if (isExcluded) {
            chain.doFilter(request, response);
            return;
        }
        
        // Kiểm tra session đăng nhập
        HttpSession session = httpRequest.getSession(false);
        UserSession userSession = null;
        
        if (session != null) {
            userSession = (UserSession) session.getAttribute("userSession");
        }
        
        if (userSession == null || !userSession.isLoggedIn()) {
            // Chưa đăng nhập, lưu URL hiện tại và redirect về login
            if (session == null) {
                session = httpRequest.getSession();
            }
            session.setAttribute("returnUrl", requestURI);
            httpResponse.sendRedirect(contextPath + "/loginShow.do");
            return;
        }
        
        // Đã đăng nhập, cho phép tiếp tục
        chain.doFilter(request, response);
    }
    
    @Override
    public void destroy() {
        // Cleanup code if needed
    }
}
