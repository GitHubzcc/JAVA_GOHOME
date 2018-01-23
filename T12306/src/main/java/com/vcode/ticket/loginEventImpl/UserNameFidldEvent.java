package com.vcode.ticket.loginEventImpl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.vcode.ticket.ui.LoginPage;
import com.vcode.ticket.utils.ComBoTextField;
import com.vcode.ticket.utils.ConfigUtils;

/**
 * 登录界面--用户名输入框事件
 * @author hh
 *
 */
public class UserNameFidldEvent {

	public static void addEvent(LoginPage<?> loginPage){
		//记住用户
//		List<String> items = new ArrayList<String>();
//		Map<String, String> map = null;
//		try {
//			map = ConfigUtils.getInstace().map;
//		} catch (Exception e1) {
//			loginPage.msgLabel.setText("配置读取文件失败");
//			loginPage.msgLabel.setForeground(Color.red);
//			e1.printStackTrace();
//		}
//		Set<String> set = map.keySet();
//		Iterator<String> it = set.iterator();
//		while(it.hasNext()){
//			items.add(it.next());
//		}
//        ComBoTextField.setupAutoComplete(loginPage.userNameFidld,loginPage.passwordField,items);
	}
}
