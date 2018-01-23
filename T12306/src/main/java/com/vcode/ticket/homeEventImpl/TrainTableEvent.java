package com.vcode.ticket.homeEventImpl;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vcode.ticket.ui.HomePage;

/**
 * 余票列表事件
 * @author hh
 * @param <T>
 *
 */
@Component
public class TrainTableEvent<T> implements MouseListener {

	@Autowired
	private HomePage<T> Page;

	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == e.BUTTON3) {
			int[] rows = Page.trainTable.getSelectedRows();
			for (int row : rows) {
				if (!Page.model_train.contains(Page.trainTable.getValueAt(row, 0))) {
					Page.model_train.addElement(Page.trainTable.getValueAt(row, 0));
				}
			}
		}
		if (e.getClickCount() == 2) {
			// TODO 开始提交订单
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
