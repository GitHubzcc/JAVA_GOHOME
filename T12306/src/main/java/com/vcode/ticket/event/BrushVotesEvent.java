package com.vcode.ticket.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vcode.ticket.brushVotesEventImpl.SeatBtEvent;
import com.vcode.ticket.ui.HomePage;

/**
 * 刷票界面事件
 * @author hh
 * @param <T>
 *
 */
@Component
public class BrushVotesEvent<T> {

	@Autowired
	private HomePage<T> Page;

	public void addEvents(){
		//席别按钮事件
		Page.seatBt.addMouseListener(new SeatBtEvent<T>());
	}
}
