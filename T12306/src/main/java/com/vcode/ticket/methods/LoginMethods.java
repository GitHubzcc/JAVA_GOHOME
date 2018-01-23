package com.vcode.ticket.methods;

import java.awt.Color;
import java.awt.Component;
import java.io.IOException;
import java.io.InputStream;
import org.apache.log4j.Logger;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.vcode.http.client.VHttpResponse;
import com.vcode.http.client.methods.VHttpGet;
import com.vcode.http.client.methods.VHttpPost;
import com.vcode.http.client.parames.VParames;
import com.vcode.http.utils.VBrowser;
import com.vcode.http.utils.VHttpUtils;
import com.vcode.ticket.ApplicationContextFactory;
import com.vcode.ticket.loginEventImpl.VerificationCodeEvent;
import com.vcode.ticket.ui.HomePage;
import com.vcode.ticket.ui.LoginPage;
import com.vcode.ticket.utils.ConfigUtils;

/**
 * 登录界面的逻辑和方法
 * @author Administrator
 * @param <T>
 *
 */
@org.springframework.stereotype.Component
public class LoginMethods<T> {
	
	@Autowired
	private LoginPage<T> Page;

	private static Logger Log = Logger.getLogger(LoginMethods.class.getName());
	
	private String newCode;
	

	/**
	 * 登录界面，用于获取cookie
	 */
	public static void ticket_init(){
		VHttpGet get = new VHttpGet("https://kyfw.12306.cn/otn/login/init");
		VHttpResponse res = VBrowser.execute(get);	
		res.getEntity().disconnect();							
	}
	
	/**
	 * 获取登录界面验证码
	 * @return
	 */
	public static byte[] getLoginCode(){
		byte[] data = null;
		try{
			VHttpGet get = new VHttpGet("https://kyfw.12306.cn/otn/passcodeNew/getPassCodeNew?module=login&rand=sjrand&"+Math.random());
			VHttpResponse res = VBrowser.execute(get);								//获取验证码
			data = VHttpUtils.InputStreamToByte(res.getBody());
		}catch(Exception e){
			try {
				InputStream in = VerificationCodeEvent.class.getResource("../image/loadError.png").openStream();
				data = VHttpUtils.InputStreamToByte(in);
			} catch (IOException e1) {
				Log.error("获取图片失败！");
				Log.error(e1.toString());
			}
			Log.error("获取验证码失败！");
		}
		return data;	
	}
	
	/**
	 * 表单校验
	 * @return
	 */
	private boolean IsChoiceCode(){
		if ("".equals(Page.userNameFidld.getText())) {
			Page.msgLabel.setText("提示：请填写用户名");
			Page.msgLabel.setForeground(Color.red);
			return false;
		}
		if (Page.passwordField.getPassword().length==0) {
			Page.msgLabel.setText("提示：请填写密码");
			Page.msgLabel.setForeground(Color.red);
			return false;
		}
		JComponent p3 = (JComponent)Page.frame.getLayeredPane();
		if (p3.getComponents().length<=0) {
			Page.msgLabel.setText("提示：请选择验证码");
			Page.msgLabel.setForeground(Color.red);
			return false;
		}
		return true;
	}
	
	/**
	 * 验证验证码是否正确
	 */
	public void CheckCode(){
		
		if (!IsChoiceCode()) {
			return;
		}
		JComponent p3 = (JComponent)Page.frame.getLayeredPane();
		Component[] cons = p3.getComponents();
		for (int i=0;i<cons.length;i++) {
			if (cons[i] instanceof JLabel) {
				JLabel lb = (JLabel)cons[i];
				newCode += lb.getX()-64+(lb.getIcon().getIconWidth()/2) + "," ;
				newCode += lb.getY()-179+(lb.getIcon().getIconHeight()/2) + "";
				if (i<cons.length-1) {
					newCode += ",";
				}
			}
		}
		Page.msgLabel.setText("当前验证码是："+newCode);
		
		VHttpPost post = new VHttpPost("https://kyfw.12306.cn/otn/passcodeNew/checkRandCodeAnsyn");
		VParames parames = new VParames();
		parames.put("rand", "sjrand");
		parames.put("randCode", newCode);
		post.setParames(parames);							//装配提交的Form
		VHttpResponse res = VBrowser.execute(post);
		String body = VHttpUtils.outHtml(res.getBody());		//将网页内容转为文本
		try {
			JSONObject json = new JSONObject(body);
			JSONObject json2 = new JSONObject(json.get("data").toString());
			if ("1".equals(json2.get("result"))) {
				Page.msgLabel.setText("验证码正确，开始提交表单");
				res.getEntity().disconnect();
				login();
			}else {
				Page.msgLabel.setText("提示：验证码错误");
				Page.msgLabel.setForeground(Color.red);
				res.getEntity().disconnect();
				Page.verificationCode.setIcon(new ImageIcon(getLoginCode()));
				clearCode();
			}
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void login(){
		//开始提交登录信息
		VHttpPost post = new VHttpPost("https://kyfw.12306.cn/otn/login/loginAysnSuggest");
		VParames parames = new VParames();
		parames.put("loginUserDTO.user_name", Page.userNameFidld.getText());
		parames.put("randCode", newCode);
		parames.put("userDTO.password", new String(Page.passwordField.getPassword()));
		
		post.setParames(parames);
		VHttpResponse res = VBrowser.execute(post);
		String body = VHttpUtils.outHtml(res.getBody());			//将网页内容转为文本
		try {
			JSONObject json = new JSONObject(body);
			if ("true".equals(json.get("status").toString())) {
				JSONObject json2 = new JSONObject(json.get("data").toString());
				if (json2.length()>1 && "Y".equals(json2.get("loginCheck").toString())) {
					Page.msgLabel.setText("登录成功，正在跳转到主页");
				}else {
					System.out.println(json.get("messages"));
					System.exit(0);
				}
			}else {
				System.out.println(json.get("messages"));
				System.exit(0);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		res.getEntity().disconnect();
		
		//开始第二次登录
		post = new VHttpPost("https://kyfw.12306.cn/otn/login/userLogin");
		VParames parames2 = new VParames();
		parames2.put("_json_att", "");
		post.setParames(parames2);								//装配参数
		res = VBrowser.execute(post);								//提交登录
		if (res.getEntity().getStaus()==200){
			if (VHttpUtils.outHtml(res.getBody()).contains("欢迎您登录中国铁路客户服务中心网站")){		//验证是否登录成功
				Page.msgLabel.setText("登录成功");
				
				//处理记住密码
				boolean check = Page.rememberCheckBox.isSelected();
				String[] str = new String[]{Page.userNameFidld.getText(),new String(Page.passwordField.getPassword())};
				if (check){
					try {
						ConfigUtils.getInstace().rememberPass(str);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else {
					try {
						ConfigUtils.getInstace().removePass(str);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				HomePage<T> window = (HomePage<T>) ApplicationContextFactory.getBean(HomePage.class);
				window.printLog("登录成功,欢迎使用V代码抢票工具");
				window.printLog("双击车次即可提交订单哦,右击可将车次加入自动刷票的预选车次中,更多隐藏功能等你发现！");
				Page.frame.dispose();
				window.show(window);
			}else {
				Page.msgLabel.setText("提示：登录失败");
				Page.msgLabel.setForeground(Color.red);
			}
			res.getEntity().disconnect();
		}
	}
	
	/**
	 * 
	 * 清空验证码
	 * 
	 */
	public void clearCode(){
		newCode = "";
		JComponent p3 = (JComponent)Page.frame.getLayeredPane();
		Component[] cons1 = p3.getComponents();
		for (Component con : cons1) {
			if (con instanceof JLabel) {
				p3.remove(con);
			}
		}
		Page.frame.repaint(); 
	}
}
