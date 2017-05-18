package com.jflyfox.dudu.component.filter;

import com.jflyfox.util.StrUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JspDispatcherFilter implements Filter {
	private String prefix = "/jsp";

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();
		String ctx = req.getContextPath();
		if (StrUtils.isNotEmpty(ctx)) {
			uri = uri.substring(ctx.length());
		}
		request.getRequestDispatcher(prefix + uri).forward(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		String prefix = filterConfig.getInitParameter("prefix");
		if (StrUtils.isNotEmpty(prefix)) {
			this.prefix = prefix;
		}
	}

	public void destroy() {
	}
}
