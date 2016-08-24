package com.run.auth.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class WebApplicationContext implements ApplicationContextAware{
	
	private static ApplicationContext ctx;
	
	private WebApplicationContext(){}
	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		ctx = applicationContext;
		
	}
	public static<T> T getBean(Class clazz){
		return (T)ctx.getBean(clazz);
	}

}
