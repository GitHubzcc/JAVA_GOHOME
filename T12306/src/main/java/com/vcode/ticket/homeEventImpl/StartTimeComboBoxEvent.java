package com.vcode.ticket.homeEventImpl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vcode.ticket.ui.HomePage;

/**
 * 出发时间下拉框事件
 * @author hh
 * @param <T>
 *
 */
@Component
public class StartTimeComboBoxEvent<T> implements ActionListener {

	@Autowired
	private HomePage<T> Page;

	public void actionPerformed(ActionEvent e) {
		DefaultTableModel model = (DefaultTableModel) Page.trainTable.getModel();
		model.setRowCount(0);
		String[] time_arr = Page.startTimeComboBox.getSelectedItem().toString().split("—");
//		for (int i = 0; i < datalist.size(); i++) {
//			JSONObject obj = datalist.get(i);
//			String start_time;
//			try {
//				start_time = obj.get("start_time").toString();
//				DateFormat sdf = new SimpleDateFormat("HH:mm");
//				Date first_date = sdf.parse(time_arr[0]);
//				Date last_date = sdf.parse(time_arr[1]);
//				Date start_date = sdf.parse(start_time);
//				if (first_date.getTime() <= start_date.getTime()
//						&& start_date.getTime() < last_date.getTime()) {
//					// TODO addRow(new String[]{},obj);
//					addRow(new String[] {}, obj);
//				}
//			} catch (JSONException e1) {
//				e1.printStackTrace();
//			} catch (ParseException e1) {
//				e1.printStackTrace();
//			}
//		}
	}

}
