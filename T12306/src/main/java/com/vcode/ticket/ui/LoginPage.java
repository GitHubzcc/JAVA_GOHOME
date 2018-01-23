package com.vcode.ticket.ui;

import java.awt.Color;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.springframework.stereotype.Component;

import com.vcode.ticket.utils.ConfigUtils;

/**
 * 登录界面
 * @author Administrator
 *
 */
@Component
public class LoginPage<T> {

	public JButton refreshBt;
	public JFrame frame;
	public JTextField userNameFidld;
	public JPasswordField passwordField;
	public JLabel verificationCode;
	public JLabel msgLabel;
	public JCheckBox rememberCheckBox;
	public SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
	public JButton loginBt;
	public boolean pic = true;
	
	public LoginPage() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		frame = new JFrame("欢迎使用V代码抢票工具——Login");
		frame.setBounds(100, 100, 440, 465);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		userNameFidld = new JTextField();
		userNameFidld.setBounds(123, 23, 230, 33);
		frame.getContentPane().add(userNameFidld);
		userNameFidld.setColumns(10);
		
		JLabel userNameLabel = new JLabel("用户名");
		userNameLabel.setBounds(61, 32, 54, 15);
		frame.getContentPane().add(userNameLabel);
		
		JLabel passwordLabel = new JLabel("密  码");
		passwordLabel.setBounds(61, 76, 54, 15);
		frame.getContentPane().add(passwordLabel);
		
		JLabel verificationCodeLabel = new JLabel("验证码");
		verificationCodeLabel.setBounds(61, 114, 54, 15);
		frame.getContentPane().add(verificationCodeLabel);
		
		verificationCode = new JLabel(new ImageIcon(ConfigUtils.initVerificationCode()));
		verificationCode.setBounds(60, 139, 293, 191);
		frame.getContentPane().add(verificationCode);
		
		refreshBt = new JButton("刷新");
		refreshBt.setBounds(123, 110, 104, 23);
		frame.getContentPane().add(refreshBt);
		
		passwordField = new JPasswordField("");
		passwordField.setEchoChar('♫');
		passwordField.setToolTipText("");
		passwordField.setBounds(123, 67, 230, 33);
		frame.getContentPane().add(passwordField);
		
		msgLabel = new JLabel("提示：选择完验证码后，右击同样可以提交哦");
		msgLabel.setBounds(61, 340, 337, 29);
		msgLabel.setForeground(Color.blue);
		frame.getContentPane().add(msgLabel);
		
		loginBt = new JButton("登录");
		loginBt.setBounds(61, 366, 303, 51);
		frame.getContentPane().add(loginBt);
        
        rememberCheckBox = new JCheckBox("记住");
        rememberCheckBox.setSelected(true);
        rememberCheckBox.setBounds(354, 29, 116, 21);
        frame.getContentPane().add(rememberCheckBox);
	}
}
