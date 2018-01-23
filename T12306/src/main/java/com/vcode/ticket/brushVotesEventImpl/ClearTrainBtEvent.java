package com.vcode.ticket.brushVotesEventImpl;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.springframework.beans.factory.annotation.Autowired;

import com.vcode.ticket.ui.HomePage;

/**
 * 清空按钮事件
 * @author hh
 * @param <T>
 *
 */
public class ClearTrainBtEvent<T> implements MouseListener {

	@Autowired
	private HomePage<T> Page;
	
	public void mouseClicked(MouseEvent e) {
		Page.model_train.removeAllElements();
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
