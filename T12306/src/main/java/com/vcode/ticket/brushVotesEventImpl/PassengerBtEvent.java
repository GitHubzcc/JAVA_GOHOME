package com.vcode.ticket.brushVotesEventImpl;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import org.springframework.beans.factory.annotation.Autowired;

import com.vcode.ticket.ui.HomePage;
import com.vcode.ticket.utils.PopList;

/**
 * 乘车人按钮事件
 * @author hh
 * @param <T>
 *
 */
public class PassengerBtEvent<T> implements MouseListener {

	private JList list;

	@Autowired
	private HomePage<T> Page;
	
//	public PassengerBtEvent(HomePage t, JList List) {
//		super(t);
//		this.list = list;
//	}

	public void mouseClicked(MouseEvent e) {
		DefaultListModel<Object> pupModel2 = getPassengerDTOs();
		PopList.initPopup(e.getComponent(), list, pupModel2);
	}

	private DefaultListModel<Object> getPassengerDTOs() {
		// TODO Auto-generated method stub
		return null;
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
