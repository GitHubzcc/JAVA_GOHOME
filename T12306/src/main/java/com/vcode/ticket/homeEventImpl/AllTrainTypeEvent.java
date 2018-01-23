package com.vcode.ticket.homeEventImpl;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JCheckBox;

import org.springframework.beans.factory.annotation.Autowired;

import com.vcode.ticket.ui.HomePage;

/**
 * 全部车型复选框事件
 * @author hh
 * @param <T>
 *
 */
@org.springframework.stereotype.Component
public class AllTrainTypeEvent<T> implements MouseListener {

	@Autowired
	private HomePage<T> Page;

	public void mouseClicked(MouseEvent e) {
		//TODO 未完成
		Component[] comps = Page.seatTypePanel.getComponents();
		for (int i = 0; i < comps.length; i++) {
			Component comp = comps[i];
			if (comp instanceof JCheckBox) {
				JCheckBox box = (JCheckBox) comp;
				if (Page.allSeatType.isSelected()) {
					box.setSelected(true);
				} else {
					box.setSelected(false);
				}
			}
		}
		for (int i = 0; i < comps.length; i++) {
			Component comp = comps[i];
			if (comp instanceof JCheckBox) {
				JCheckBox box = (JCheckBox) comp;
				if (!"全部席别".equals(box.getText())) {
					int col_size = Page.trainTable.getColumnModel().getColumnCount();
					int col_num = 0;
					for (int j = 0; j < col_size; j++) {
						if (box.getText().equals(Page.trainTable.getColumnModel().getColumn(j).getHeaderValue())) {
							col_num = j;
						}
					}
				}
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
