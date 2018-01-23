package com.vcode.ticket.loginEventImpl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vcode.ticket.ApplicationContextFactory;
import com.vcode.ticket.ui.HomePage;
import com.vcode.ticket.ui.LoginPage;

/**
 * 登录界面--登录按钮事件
 * @author hh
 * @param <T>
 *
 */
@Component
public class LoginBtEvent<T> implements ActionListener {
	
	private Logger Log = Logger.getLogger(LoginBtEvent.class.getName());

	@Autowired
	private LoginPage<T> Page;
	
	public void actionPerformed(ActionEvent e) {
		//loginMethods.CheckCode();
		Page.frame.setVisible(false);
		HomePage<T> homePage = (HomePage<T>) ApplicationContextFactory.getBean(HomePage.class);
		homePage.show(homePage);
	}

}
