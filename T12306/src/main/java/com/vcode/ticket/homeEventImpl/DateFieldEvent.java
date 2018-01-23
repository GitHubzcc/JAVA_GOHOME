package com.vcode.ticket.homeEventImpl;

import com.vcode.ticket.ui.HomePage;
import com.vcode.ticket.utils.Chooser;

/**
 * 为日期输入框添加日期控件
 * @author hh
 *
 */
public class DateFieldEvent {

	public static void addEvent(HomePage<?> Page){
		Chooser ser2 = Chooser.getInstance("yyyy-MM-dd");
		ser2.register(Page.dateField);
	}
}
