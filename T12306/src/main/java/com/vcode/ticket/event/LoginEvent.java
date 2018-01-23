package com.vcode.ticket.event;

import javax.swing.ImageIcon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vcode.ticket.loginEventImpl.FrameEvent;
import com.vcode.ticket.loginEventImpl.LoginBtEvent;
import com.vcode.ticket.loginEventImpl.RefreshBtEvent;
import com.vcode.ticket.loginEventImpl.UserNameFidldEvent;
import com.vcode.ticket.loginEventImpl.VerificationCodeEvent;
import com.vcode.ticket.methods.LoginMethods;
import com.vcode.ticket.ui.LoginPage;

@Component
public class LoginEvent<T>{
	
	@Autowired
	private LoginPage<T> Page;
	
	@Autowired
	private VerificationCodeEvent<T> verificationCodeEvent;
	
	@SuppressWarnings("unused")
	@Autowired
	private LoginMethods<T> loginMethods;
	
	@Autowired
	private FrameEvent<T> frameEvent;
	
	@Autowired
	private RefreshBtEvent<T> refreshBtEvent;
	
	@Autowired
	private LoginBtEvent<T> loginBtEvent;

	public void addEvents(){
		
		//初始化cookie
		LoginMethods.ticket_init();
		
		//初始化验证码
		Page.verificationCode.setIcon(new ImageIcon(LoginMethods.getLoginCode()));
		
		//登录面板事件
		Page.frame.addMouseListener(frameEvent);
		
		//验证码（图片）事件
		Page.verificationCode.addMouseListener(verificationCodeEvent);
		
		//刷新按钮事件
		Page.refreshBt.addActionListener(refreshBtEvent);
		
		//登录按钮事件
		Page.loginBt.addActionListener(loginBtEvent);
		
		//用户名输入框事件
		UserNameFidldEvent.addEvent(Page);
		
	}
}
