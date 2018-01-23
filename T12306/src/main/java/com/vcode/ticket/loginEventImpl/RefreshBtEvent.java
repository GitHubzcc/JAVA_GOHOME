package com.vcode.ticket.loginEventImpl;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

import org.springframework.beans.factory.annotation.Autowired;

import com.vcode.ticket.ui.LoginPage;

/**
 * 登录界面--刷新按钮事件
 * @author hh
 * @param <T>
 *
 */
@org.springframework.stereotype.Component
public class RefreshBtEvent<T> implements ActionListener {

	@Autowired
	private LoginPage<T> Page;

	public void actionPerformed(ActionEvent e) {
		Page.verificationCode.setIcon(new ImageIcon(""));
		JComponent p3 = (JComponent)Page.frame.getLayeredPane();
		Component[] cons = p3.getComponents();
		for (Component con : cons) {
			if (con instanceof JLabel) {
				p3.remove(con);
			}
		}
		Page.frame.repaint(); 
	}

}
