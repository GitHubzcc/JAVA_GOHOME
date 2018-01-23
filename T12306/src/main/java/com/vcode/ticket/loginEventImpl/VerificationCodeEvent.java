package com.vcode.ticket.loginEventImpl;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import org.apache.log4j.Logger;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vcode.ticket.ui.LoginPage;

/**
 * 登录界面--验证码（图片）事件
 * @author hh
 * @param <T>
 *
 */
@Component
public class VerificationCodeEvent<T> implements MouseListener {
	
	private static Logger Log = Logger.getLogger(VerificationCodeEvent.class.getName());
	
	@Autowired
	private LoginPage<T> Page;
	
	public void mouseClicked(MouseEvent e) {
		final JComponent p3 = (JComponent) Page.frame.getLayeredPane();
		if (e.getButton()==e.BUTTON1) {
			ImageIcon icon = new ImageIcon(LoginPage.class.getResource("../image/12306.jpg"));
			final JLabel jb3 = new JLabel(icon);
			jb3.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					p3.remove(jb3);
					p3.repaint();
				}
			});
			p3.add(jb3, new Integer(-3)); // 将按钮jb3，放置在内容面板之下
			jb3.setSize(icon.getIconWidth(), icon.getIconHeight());
			jb3.setLocation(e.getX()+60-(icon.getIconWidth()/2), e.getY()+139-(icon.getIconHeight()/2));
		}else if(e.getButton() == MouseEvent.BUTTON3){
        	//loginMethods.CheckCode();
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
