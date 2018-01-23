package com.vcode.ticket.homeEventImpl;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import com.vcode.ticket.ui.HomePage;
import com.vcode.ticket.utils.ComBoTextField;
import com.vcode.ticket.utils.HttpUtils;

/**
 * 出发地和目的地输入框事件，使其能点击下拉弹出城市列表
 * @param Page
 */
public class SetOutFidldEvent {

	public static void addEvent(HomePage<?> Page){
		Map<String, String[]> map = (Map<String, String[]>) HttpUtils.getCitiInfo();
		ArrayList<String> keyItems = new ArrayList<String>();
		Set<String> set = map.keySet();
		for (String s : set) {
			keyItems.add(s);
		}
		ComBoTextField.setupAutoComplete2(Page.setOutFidld, keyItems, map);
		ComBoTextField.setupAutoComplete2(Page.destinationField, keyItems, map);
	}
}
