package com.vcode.ticket.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vcode.ticket.ui.HomePage;

/**
 * 订单界面事件
 * @author hh
 * @param <T>
 *
 */
@Component
public class OrderEvent<T> {

	@Autowired
	private HomePage<T> Page;

	public void addEvents(){
		
	}
}
