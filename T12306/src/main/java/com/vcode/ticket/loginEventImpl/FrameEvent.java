package com.vcode.ticket.loginEventImpl;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vcode.ticket.ui.LoginPage;

/**
 * 登录界面--面板事件
 * @author hh
 * @param <T>
 *
 */
@Component
public class FrameEvent<T> implements MouseListener {

	@Autowired
	private LoginPage<T> Page;
	
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON3){
        	//TODO 提交验证码
        }
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
