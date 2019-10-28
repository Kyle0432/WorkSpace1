package com.kyle.bookstore.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kyle.bookstore.web.HttpFilter;

public class Encoding extends HttpFilter {
    private String encoding;
	@Override
	public void init()  {
    encoding = getFilterConfig().getServletContext().getInitParameter("encoding");
	}
	@Override
	public void doFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
					throws IOException, ServletException {
     request.setCharacterEncoding(encoding);
     filterChain.doFilter(request, response);	
	}
}
