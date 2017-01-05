package com.example.todo;

import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.FilterChain;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN;
import static org.springframework.http.HttpMethod.GET;

public class CORSFilterTest {

    private static final String BACKEND_TODO_COM = "backend-todo.com";

    private FilterChain filterChain = new MockFilterChain();


    @BeforeMethod
    public void setUp() throws Exception {
    }

    @Test
    public void shouldSetAccessControlHeader() throws Exception {
        // given
        CORSFilter filter = new CORSFilter();
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        req.addHeader(HttpHeaders.ORIGIN, BACKEND_TODO_COM);
        req.setMethod(GET.toString());

        // when
        filter.doFilter(req, res, filterChain);

        // then
        assertThat(res.getHeader(ACCESS_CONTROL_ALLOW_ORIGIN), is("*"));
    }
}