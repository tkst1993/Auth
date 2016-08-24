package com.run.auth.context;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Decoder;

import com.run.auth.dto.Accordion;
import com.run.auth.entity.User;
import com.sun.org.apache.xalan.internal.utils.Objects;

public class UserContextFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest  request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		ResponseContext.setCurrent(response);
		
		if(request.getRequestURI().contains("login")){
			//删除cookie
			Cookie cookie = new Cookie("auth",null);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			chain.doFilter(servletRequest, servletResponse);
			return;
		}
		
		if(request.getRequestURI().contains("index.jsp")){
			chain.doFilter(servletRequest, servletResponse);
			return;
		}
		
		if(request.getRequestURI().endsWith(".css")||request.getRequestURI().endsWith(".js")
		||request.getRequestURI().endsWith(".jpg")||request.getRequestURI().endsWith(".gif"))
		{
			chain.doFilter(servletRequest, servletResponse);
			return;
		}
		
		String cookieValue = "";
		if(null != request.getCookies()){
			for(Cookie cookie : request.getCookies()){
				if(Objects.equals(cookie.getName(), "auth")){
					cookieValue = cookie.getValue();
					break;
				}
			}
			
		}
		
		String auth = getFromBase64(cookieValue);
		String[]array = auth.split("\\$");
		if(2==array.length){
			
			User user = LoginUserCache.get(array[0]);
			if(null == user){
				LoginUserHelper helper = WebApplicationContext.getBean(LoginUserHelper.class);
				helper.executeLogin(array[0], array[1]);
				user = LoginUserCache.get(array[0]);
			}
			if(null != user && Objects.equals(user.getPwd(), array[1])){
				List<Accordion> accordions = LoginUserCache.getAccorions(user.getName());
				request.setAttribute("accordion", accordions);
				UserContext.setCurrent(user);
				LoginUserCache.setCookie(user);
				chain.doFilter(servletRequest, servletResponse);
				return;
			}else{
				Cookie cookie = new Cookie("auth" , null);
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				response.sendRedirect("/index.jsp");
			}
		}else{
			Cookie cookie = new Cookie("auth" , null);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		chain.doFilter(servletRequest, servletResponse);

	}
	
	public static String getFromBase64(String s){
		byte[] b = null;
		String result = null;
		if(s != null){
			BASE64Decoder decoder = new BASE64Decoder();
			try{
				b = decoder.decodeBuffer(s);
				result = new String(b,"utf-8");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return result;
	}

	public void destroy() {

	}

}
