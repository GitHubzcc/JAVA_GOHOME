package com.vcode.ticket.brushVotesEventImpl;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;

import org.springframework.beans.factory.annotation.Autowired;

import com.vcode.ticket.ui.HomePage;

/**
 * 席别列表事件
 * @author hh
 * @param <T>
 *
 */
public class SeatTypeListEvent<T> implements MouseListener {

	@Autowired
	private HomePage<T> Page;

	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() >= 2) {
			if (Page.seatTypeList.getSelectedIndex() > -1) {
				DefaultListModel<Object> model_Seats = new DefaultListModel<Object>();
				for (int i = 0; i < Page.seatTypeList.getModel().getSize(); i++) {
					if (i != Page.seatTypeList.getSelectedIndex()) {
						model_Seats.addElement(Page.seatTypeList.getModel().getElementAt(i));
					}
				}
				Page.seatTypeList.setModel(model_Seats);
			}
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
