package com.vcode.ticket;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class ApplicationContextFactory {
	
	private static ApplicationContext appContext = null;
	
	private static Logger Log = Logger.getLogger(ApplicationContextFactory.class);
	
	static{
		Log.info("初始化Bean工厂  start");
		appContext = new AnnotationConfigApplicationContext("com.vcode.ticket");
		Log.info("初始化Bean工厂  end");
	}
	
	public static <T> Object getBean(Class<T> clazz){
		return appContext.getBean(clazz);
	}

}
