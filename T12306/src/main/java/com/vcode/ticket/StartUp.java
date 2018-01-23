package com.vcode.ticket;

import org.apache.log4j.Logger;

import com.vcode.ticket.event.BrushVotesEvent;
import com.vcode.ticket.event.HomeEvent;
import com.vcode.ticket.event.LoginEvent;
import com.vcode.ticket.event.OrderEvent;
import com.vcode.ticket.ui.HomePage;
import com.vcode.ticket.ui.LoginPage;

public class StartUp {
	
	private static Logger Log = Logger.getLogger(StartUp.class);
	
	@SuppressWarnings("unchecked")
	public static <T> void main(String[] args) {
		Log.info("正在加载资源文件...");
		LoginPage<T> loginPage = (LoginPage<T>) ApplicationContextFactory.getBean(LoginPage.class);
		Log.info("开始启动登录界面...");
		loginPage.frame.setVisible(true);
		
		Log.info("正在加载登录界面事件...");
		LoginEvent<T> loginEvent = (LoginEvent<T>) ApplicationContextFactory.getBean(LoginEvent.class);
		loginEvent.addEvents();
		
		Log.info("正在加载主界面...");
		HomePage<T> homePage = (HomePage<T>) ApplicationContextFactory.getBean(HomePage.class);
		
		Log.info("正在加载主界面事件...");
		HomeEvent<T> homeEvent = (HomeEvent<T>) ApplicationContextFactory.getBean(HomeEvent.class);
		homeEvent.addEvents();
		
		Log.info("正在加载刷票事件...");
		BrushVotesEvent<T> brushVotesEvent = (BrushVotesEvent<T>) ApplicationContextFactory.getBean(BrushVotesEvent.class);
		brushVotesEvent.addEvents();
		
		Log.info("正在加载订单界面事件...");
		OrderEvent<T> orderEvent = (OrderEvent<T>) ApplicationContextFactory.getBean(OrderEvent.class);
		orderEvent.addEvents();
		Log.info("加载完毕...");
		
	}
}
