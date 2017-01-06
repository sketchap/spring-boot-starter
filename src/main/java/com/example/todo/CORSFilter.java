package com.example.todo;


import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.*;

// todo use cors annotation
@Component
public class CORSFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;

        response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        response.setHeader(ACCESS_CONTROL_ALLOW_METHODS, "POST, GET, OPTIONS, DELETE, PATCH");
        response.setHeader(ACCESS_CONTROL_MAX_AGE, "3600");
        response.setHeader(ACCESS_CONTROL_ALLOW_HEADERS, "x-requested-with, origin, content-type, accept");

        chain.doFilter(req, response);
    }

    @Override
    public void destroy() {}
}
