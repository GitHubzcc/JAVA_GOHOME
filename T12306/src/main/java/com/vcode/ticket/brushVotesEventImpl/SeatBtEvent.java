package com.vcode.ticket.brushVotesEventImpl;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import org.springframework.beans.factory.annotation.Autowired;

import com.vcode.ticket.ui.HomePage;
import com.vcode.ticket.utils.PopList;

/**
 * 席别按钮事件
 * @author hh
 * @param <T>
 *
 */
public class SeatBtEvent<T> implements MouseListener {
	
	@Autowired
	private HomePage<T> Page;
	
	public void mouseClicked(MouseEvent e) {
		DefaultListModel<Object> pupModel = new DefaultListModel<Object>();
		pupModel.addElement("商务座");
		pupModel.addElement("特等座");
		pupModel.addElement("一等座");
		pupModel.addElement("二等座");
		pupModel.addElement("高级软卧");
		pupModel.addElement("软卧");
		pupModel.addElement("硬卧");
		pupModel.addElement("软座");
		pupModel.addElement("硬座");
		pupModel.addElement("无座");
		pupModel.addElement("其它");
		PopList.initPopup(e.getComponent(), Page.seatTypeList, pupModel);
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
