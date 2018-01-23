package com.vcode.ticket.homeEventImpl;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vcode.ticket.ui.HomePage;

/**
 * 各类车型复选框事件
 * @author hh
 * @param <T>
 *
 */
@Component
public class TrainTypeEvent<T> implements MouseListener {
	
	private JCheckBox checkBox;		//复选框
		
	private JPanel panel;			//车型复选框的父类Panel
	
	private JCheckBox allCheckBox;	//全选复选框
	
	private String num;				//车型编号的第一个字母

	@Autowired
	private HomePage<T> Page;
	
//	public TrainTypeEvent(HomePage t,JCheckBox checkBox, JPanel panel_1, JCheckBox checkBox_22, String num){
//		this.checkBox = checkBox;
//		this.panel = panel_1;
//		this.allCheckBox = checkBox_22;
//		this.num = num;
//	}

	public void mouseClicked(MouseEvent e) {
		
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
