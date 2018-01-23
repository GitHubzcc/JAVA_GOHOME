package com.vcode.ticket.brushVotesEventImpl;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.springframework.beans.factory.annotation.Autowired;

import com.vcode.ticket.ui.HomePage;

/**
 * 车次列表事件
 * @author hh
 * @param <T>
 *
 */
public class TrainListEvent<T> implements MouseListener {

	@Autowired
	private HomePage<T> Page;

	public void mouseClicked(MouseEvent e) {
		if (Page.trainList.getSelectedIndex() > -1) {
			Page.model_train.remove(Page.trainList.getSelectedIndex());
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
