package com.kyle.bookstore.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class HttpFilter implements Filter {
    
/*
 * ���ڱ���FilterConfig����
 */
	private FilterConfig filterConfig;
/*
 * ����������ֱ�Ӹ���,��ֱ�Ӹ���,�����ܻᵼ��filterConfig��Ա������ʼ��ʧ��
 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		this.filterConfig= arg0;
		init();
	}
/*
 * ������̳еĳ�ʼ������,����ͨ��getFilterConfig()��ȡFilterConfig����
 */
	protected void init() {
	}
/*
 * ֱ�ӷ���init(ServletConfig) ��FilterConfig����
 */
	public FilterConfig getFilterConfig(){
		return filterConfig;
	}
  /*
	 * ԭ���� doFilter ����, �ڷ����ڲ��� ServletRequest �� ServletResponse 
	 * תΪ�� HttpServletRequest �� HttpServletResponse, �������� 
	 * doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	 * 
	 * ����д Filter �Ĺ��˷���������ֱ�Ӽ̳и÷���. ������̳�
	 * doFilter(HttpServletRequest request, HttpServletResponse response, 
	 *		FilterChain filterChain) ����
	 */	
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
       HttpServletRequest request = (HttpServletRequest) arg0;
       HttpServletResponse response = (HttpServletResponse) arg1;
       
       doFilter(request, response, arg2);
	}

	/*
	 * ���󷽷�, Ϊ Http ������,Ϊ���ڼ̳���ҳ��ֱ����doFilter,. ����ʵ�ֵķ���. 
	 * @param request
	 * @param response
	 * @param filterChain
	 * @throws IOException
	 * @throws ServletException
	 */
	public abstract void doFilter(HttpServletRequest request, HttpServletResponse response, 
			FilterChain filterChain) throws IOException, ServletException;
	@Override
	public void destroy() {
	}
}
