package com.vcode.ticket.ui;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.vcode.http.client.VHttpResponse;
import com.vcode.http.client.methods.VHttpPost;
import com.vcode.http.utils.VBrowser;
import com.vcode.http.utils.VHttpUtils;
import com.vcode.ticket.utils.HttpUtils;
import com.vcode.ticket.utils.JTextAreaExt;

/**
 * 刷票界面
 * 
 * 座位编号： 硬座：1 软座：2 硬卧：3 软卧：4 一等座：M 二等座：O 商务座：9
 * 
 * 
 * @author huahao
 *
 */
@org.springframework.stereotype.Component
public class HomePage<T> {

	public HomePage<T> window;
	private JFrame frame;
	public JTextField setOutFidld;
	public JTextField dateField;
	public JTextField destinationField;
	public JLabel exchangeLabel;
	public JComboBox<Object> startTimeComboBox;
	public JTable trainTable;
	private int[] mouse_gis = new int[2];
	public JLabel setOutLabel;

	public JPanel trainTypePanel;
	public JCheckBox allTrainType;
	public JCheckBox Gtrain;
	public JCheckBox Ctrain;
	public JCheckBox Dtrain;
	public JCheckBox Ttrain;
	public JCheckBox Ztrain;
	public JCheckBox Ktrain;
	public JCheckBox Ptrain;
	public JCheckBox Ltrain;
	public JCheckBox QTtrain;

	public JPanel seatTypePanel;
	public JCheckBox allSeatType;
	public JCheckBox SWseat;
	public JCheckBox TDseat;
	public JCheckBox YDseat;
	public JCheckBox RDseat;
	public JCheckBox GRseat;
	public JCheckBox RWseat;
	public JCheckBox YWseat;
	public JCheckBox RZseat;
	public JCheckBox YZseat;
	public JCheckBox WZseat;
	public JCheckBox QTseat;
	
	public JButton brushVotesBt;
	public JCheckBox trainModel;
	
	public JButton seatBt;
	public JButton passengerBt;
	public JButton clearTrainBt;

	public Map<String, String[]> map = (Map<String, String[]>) HttpUtils.getCitiInfo();
	public Map<String, JSONObject> userMap = new HashMap<String, JSONObject>();
	public List<JSONObject> datalist = new ArrayList<JSONObject>();
	public JTextAreaExt logArea;
	public SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
	public SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
	public JList<Object> trainList;
	public JList<Object> seatTypeList;
	public JList<Object> passengerList;
	public String REPEAT_SUBMIT_TOKEN = "";
	public String key_check_isChange = "";
	public DefaultListModel<Object> model_train = new DefaultListModel<Object>();
	public JTable table_1;
	public int ticket_type = 0;
	public boolean result = true;
	public JSpinner brushVotesFrequency;
	public String sb;
	public int[] seatOthers;
	public boolean isRun = false;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage window = new HomePage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void show(HomePage window) {
		this.window = window;
		window.frame.setVisible(true);
	}
	
//	public void show() {
//		window.frame.setVisible(true);
//	}

	/**
	 * Create the application.
	 */
	public HomePage() {
		VBrowser.getInstance();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		frame = new JFrame();
		frame.setBounds(100, 100, 1110, 720);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setOutFidld = new JTextField();
		setOutFidld.setLocation(104, 51);
		setOutFidld.setSize(90, 23);
		setOutFidld.setColumns(30);

		setOutLabel = new JLabel("出发地");
		setOutLabel.setBounds(43, 52, 55, 18);

		JLabel destinationLabel = new JLabel("目的地");
		destinationLabel.setBounds(236, 54, 55, 18);

		JLabel dateLabel = new JLabel("日  期");
		dateLabel.setBounds(382, 54, 41, 21);
		frame.getContentPane().setLayout(null);

		dateField = new JTextField();
		dateField.setBounds(431, 54, 86, 23);

		dateField.setColumns(10);

		destinationField = new JTextField();
		destinationField.setLocation(281, 53);
		destinationField.setSize(90, 23);
		destinationField.setColumns(10);

		JLabel startTimeLabel = new JLabel("发车时间");
		startTimeLabel.setBounds(529, 55, 65, 18);

		startTimeComboBox = new JComboBox<Object>();
		startTimeComboBox.setBounds(600, 53, 105, 21);
		startTimeComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		startTimeComboBox.setModel(new DefaultComboBoxModel<Object>(
				new String[] { "00:00—24:00", "00:00—08:00", "08:00—12:00", "12:00—20:00", "20:00—24:00" }));

		/**
		 * adult: "1", child: "2", student: "3", disability: "4"
		 */
		JRadioButton adultRadioBt = new JRadioButton("成人");
		adultRadioBt.setLocation(714, 52);
		adultRadioBt.setSize(61, 21);
		adultRadioBt.setSelected(true);

		JRadioButton studentRadioBt = new JRadioButton("学生");
		studentRadioBt.setLocation(771, 52);
		studentRadioBt.setSize(60, 21);

		JRadioButton childRadioBt = new JRadioButton("儿童");
		childRadioBt.setLocation(827, 52);
		childRadioBt.setSize(60, 21);

		JRadioButton disabilityRadioBt = new JRadioButton("军残");
		disabilityRadioBt.setBounds(885, 52, 58, 21);

		ButtonGroup passengerTypeRadio = new ButtonGroup();
		passengerTypeRadio.add(adultRadioBt);
		passengerTypeRadio.add(studentRadioBt);
		passengerTypeRadio.add(childRadioBt);
		passengerTypeRadio.add(disabilityRadioBt);

		brushVotesBt = new JButton("手动查票");
		brushVotesBt.setBounds(953, 85, 86, 67);

		trainModel = new JCheckBox("刷票模式");
		trainModel.setBounds(945, 53, 100, 21);
		frame.getContentPane().add(trainModel);
		frame.getContentPane().add(brushVotesBt);

		JLabel label_4 = new JLabel("车  型");
		label_4.setBounds(43, 89, 41, 18);
		JLabel label_5 = new JLabel("席  别");
		label_5.setBounds(43, 123, 41, 18);

		trainTypePanel = new JPanel();
		trainTypePanel.setBounds(104, 80, 837, 31);
		seatTypePanel = new JPanel();
		seatTypePanel.setBounds(104, 117, 837, 31);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 164, 1001, 237);
		trainTable = new JTable();
		trainTable.setRowHeight(20);
		trainTable.setFillsViewportHeight(true);
		trainTable.setSurrendersFocusOnKeystroke(true);
		trainTable.setFont(new Font("宋体", Font.PLAIN, 12));
		scrollPane.setViewportView(trainTable);
		trainTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		trainTable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "\u8F66\u6B21", "\u51FA\u53D1\u5730", "\u76EE\u7684\u5730", "\u5386\u65F6",
						"\u53D1\u8F66\u65F6\u95F4", "\u5230\u8FBE\u65F6\u95F4", "\u5546\u52A1", "\u7279\u7B49",
						"\u4E00\u7B49", "\u4E8C\u7B49", "\u9AD8\u8F6F", "\u8F6F\u5367", "\u786C\u5367", "\u8F6F\u5EA7",
						"\u786C\u5EA7", "\u65E0\u5EA7", "\u5176\u5B83", "\u5907\u6CE8" }));
		trainTable.getColumnModel().getColumn(0).setPreferredWidth(55);
		trainTable.getColumnModel().getColumn(1).setPreferredWidth(70);
		trainTable.getColumnModel().getColumn(2).setPreferredWidth(70);
		trainTable.getColumnModel().getColumn(3).setPreferredWidth(65);
		trainTable.getColumnModel().getColumn(4).setPreferredWidth(70);
		trainTable.getColumnModel().getColumn(5).setPreferredWidth(70);
		trainTable.getColumnModel().getColumn(6).setPreferredWidth(65);
		trainTable.getColumnModel().getColumn(7).setPreferredWidth(65);
		trainTable.getColumnModel().getColumn(8).setPreferredWidth(65);
		trainTable.getColumnModel().getColumn(9).setPreferredWidth(65);
		trainTable.getColumnModel().getColumn(10).setPreferredWidth(65);
		trainTable.getColumnModel().getColumn(11).setPreferredWidth(65);
		trainTable.getColumnModel().getColumn(12).setPreferredWidth(65);
		trainTable.getColumnModel().getColumn(13).setPreferredWidth(65);
		trainTable.getColumnModel().getColumn(14).setPreferredWidth(65);
		trainTable.getColumnModel().getColumn(15).setPreferredWidth(65);
		trainTable.getColumnModel().getColumn(16).setPreferredWidth(65);
		setTableSize();

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(43, 411, 1001, 242);

		JPanel p1 = new JPanel();
		tabbedPane.add(p1, "刷票界面");
		p1.setLayout(null);

		logArea = new JTextAreaExt();
		logArea.setEnabled(false);
		logArea.setEditable(false);
		logArea.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(logArea);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		scroll.setBounds(0, 0, 413, 205);
		logArea.setBorder(BorderFactory.createTitledBorder("日志输出: "));
		p1.add(scroll);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(BorderFactory.createTitledBorder("乘车信息设置: "));
		panel_3.setBounds(423, 5, 563, 200);
		p1.add(panel_3);
		panel_3.setLayout(null);

		JLabel brushVotesFrequencyLabel = new JLabel("刷票频率：");
		brushVotesFrequencyLabel.setBounds(8, 32, 80, 15);
		panel_3.add(brushVotesFrequencyLabel);

		brushVotesFrequency = new JSpinner();
		brushVotesFrequency.setBounds(91, 29, 78, 22);
		panel_3.add(brushVotesFrequency);
		brushVotesFrequency.setModel(new SpinnerNumberModel(new Integer(1000), null, null, new Integer(100)));

		JLabel passenger = new JLabel("乘    客：");
		passenger.setBounds(10, 167, 69, 15);
		panel_3.add(passenger);

		JLabel seatLabel = new JLabel("优先席别：");
		seatLabel.setBounds(10, 119, 80, 15);
		panel_3.add(seatLabel);

		JCheckBox partialSubmission = new JCheckBox("部分提交");
		partialSubmission.setBounds(300, 28, 90, 23);
		panel_3.add(partialSubmission);

		JCheckBox noSeatNotSubmitted = new JCheckBox("无座不提交");
		noSeatNotSubmitted.setBounds(180, 28, 110, 23);
		panel_3.add(noSeatNotSubmitted);
		noSeatNotSubmitted.setSelected(true);

		JLabel TrainLabel = new JLabel("车    次：");
		TrainLabel.setBounds(10, 74, 69, 15);
		panel_3.add(TrainLabel);

		seatBt = new JButton("席别");
		seatBt.setBounds(480, 115, 73, 23);
		panel_3.add(seatBt);

		passengerBt = new JButton("乘车人");
		passengerBt.setBounds(480, 163, 73, 23);
		panel_3.add(passengerBt);

		clearTrainBt = new JButton("清空");
		clearTrainBt.setBounds(480, 70, 73, 23);
		panel_3.add(clearTrainBt);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(86, 70, 384, 25);
		panel_3.add(scrollPane_2);

		trainList = new JList<Object>(model_train);
		scrollPane_2.setViewportView(trainList);
		trainList.setVisibleRowCount(1);
		trainList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		trainList.setLayoutOrientation(JList.HORIZONTAL_WRAP);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(86, 117, 384, 25);
		panel_3.add(scrollPane_3);

		seatTypeList = new JList<Object>();
		scrollPane_3.setViewportView(seatTypeList);
		seatTypeList.setVisibleRowCount(1);
		seatTypeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		seatTypeList.setLayoutOrientation(JList.HORIZONTAL_WRAP);

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(86, 163, 384, 25);
		panel_3.add(scrollPane_4);

		passengerList = new JList<Object>();
		scrollPane_4.setViewportView(passengerList);
		passengerList.setVisibleRowCount(1);
		passengerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		passengerList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		JPanel p2 = new JPanel();
		tabbedPane.add(p2, "订单界面");
		p2.setLayout(null);

		final JButton btnNewButton_1 = new JButton("刷新订单列表");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO 刷新订单列表按键事件
			}
		});
		btnNewButton_1.setBounds(20, 19, 105, 32);
		p2.add(btnNewButton_1);

		table_1 = new JTable();
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_1.setFillsViewportHeight(true);
		table_1.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "\u8F66\u6B21", "\u8BA2\u5355\u53F7", "\u4E58\u5BA2\u59D3\u540D",
						"\u53D1\u8F66\u65F6\u95F4", "\u51FA\u53D1\u5730", "\u76EE\u7684\u5730", "\u7968\u79CD",
						"\u5E2D\u522B", "\u8F66\u53A2", "\u5EA7\u4F4D", "\u7968\u4EF7", "\u72B6\u6001" }));
		table_1.getColumnModel().getColumn(3).setPreferredWidth(124);

		JScrollPane scrollPane_1 = new JScrollPane(table_1);
		scrollPane_1.setBounds(10, 62, 976, 140);
		p2.add(scrollPane_1);

		JPanel panel = new JPanel();
		panel.setBounds(10, 0, 465, 60);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "订单操作: ", TitledBorder.LEADING,
				TitledBorder.TOP, null, SystemColor.controlText));
		p2.add(panel);
		panel.setLayout(null);

		JButton button_4 = new JButton("继续支付");
		button_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("继续支付");
			}
		});
		button_4.setBounds(205, 19, 81, 32);
		panel.add(button_4);

		final JButton button_3 = new JButton("取消订单");
		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (table_1.getSelectedRows().length > 0) {
					if (table_1.getValueAt(table_1.getSelectedRow(), 1) != null) {
						int firm_cancle = JOptionPane.showConfirmDialog(null, "是否取消订单?", "确认框",
								JOptionPane.YES_NO_OPTION);
						if (firm_cancle == 0) {
							// TODO 取消订单
						}
					}
				}
			}
		});
		button_3.setBounds(120, 19, 81, 32);
		panel.add(button_3);

		JButton button_5 = new JButton("改签");
		button_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("改签");
			}
		});
		button_5.setBounds(290, 19, 81, 32);
		panel.add(button_5);

		JButton button_6 = new JButton("退票");
		button_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("退票");
			}
		});
		button_6.setBounds(375, 19, 81, 32);
		panel.add(button_6);

		JPanel p3 = new JPanel();
		tabbedPane.add(p3, "消息提醒");
		JPanel p4 = new JPanel();
		tabbedPane.add(p4, "其它功能");
		GroupLayout gl_p4 = new GroupLayout(p4);
		gl_p4.setHorizontalGroup(gl_p4.createParallelGroup(Alignment.LEADING).addGap(0, 996, Short.MAX_VALUE));
		gl_p4.setVerticalGroup(gl_p4.createParallelGroup(Alignment.LEADING).addGap(0, 205, Short.MAX_VALUE));
		p4.setLayout(gl_p4);
		trainTypePanel.setLayout(null);

		allTrainType = new JCheckBox("全部车型");
		allTrainType.setBounds(14, 5, 92, 23);
		trainTypePanel.add(allTrainType);
		allTrainType.setSelected(true);

		Gtrain = new JCheckBox("高铁-G");
		Gtrain.setBounds(110, 5, 80, 23);
		trainTypePanel.add(Gtrain);
		Gtrain.setSelected(true);

		Ctrain = new JCheckBox("城铁-C");
		Ctrain.setBounds(194, 5, 80, 23);
		trainTypePanel.add(Ctrain);
		Ctrain.setSelected(true);

		Dtrain = new JCheckBox("动车-D");
		Dtrain.setBounds(278, 5, 80, 23);
		trainTypePanel.add(Dtrain);
		Dtrain.setSelected(true);

		Ttrain = new JCheckBox("特快-T");
		Ttrain.setBounds(362, 5, 80, 23);
		trainTypePanel.add(Ttrain);
		Ttrain.setSelected(true);

		Ztrain = new JCheckBox("直达-Z");
		Ztrain.setBounds(446, 5, 80, 23);
		trainTypePanel.add(Ztrain);
		Ztrain.setSelected(true);

		Ktrain = new JCheckBox("快车-K");
		Ktrain.setBounds(530, 5, 80, 23);
		trainTypePanel.add(Ktrain);
		Ktrain.setSelected(true);

		Ptrain = new JCheckBox("普客");
		Ptrain.setBounds(614, 5, 58, 23);
		trainTypePanel.add(Ptrain);
		Ptrain.setSelected(true);

		Ltrain = new JCheckBox("临客");
		Ltrain.setBounds(686, 5, 58, 23);
		trainTypePanel.add(Ltrain);
		Ltrain.setSelected(true);

		QTtrain = new JCheckBox("其它");
		QTtrain.setBounds(758, 5, 58, 23);
		trainTypePanel.add(QTtrain);
		QTtrain.setSelected(true);
		seatTypePanel.setLayout(null);

		allSeatType = new JCheckBox("全部席别");
		allSeatType.setBounds(17, 4, 86, 23);
		seatTypePanel.add(allSeatType);
		allSeatType.setSelected(true);

		SWseat = new JCheckBox("商务");
		SWseat.setBounds(107, 4, 62, 23);
		seatTypePanel.add(SWseat);
		SWseat.setSelected(true);

		TDseat = new JCheckBox("特等");
		TDseat.setBounds(173, 4, 62, 23);
		seatTypePanel.add(TDseat);
		TDseat.setSelected(true);

		YDseat = new JCheckBox("一等");
		YDseat.setBounds(239, 4, 62, 23);
		seatTypePanel.add(YDseat);
		YDseat.setSelected(true);

		RDseat = new JCheckBox("二等");
		RDseat.setBounds(305, 4, 62, 23);
		seatTypePanel.add(RDseat);
		RDseat.setSelected(true);

		GRseat = new JCheckBox("高软");
		GRseat.setBounds(371, 4, 62, 23);
		seatTypePanel.add(GRseat);
		GRseat.setSelected(true);

		RWseat = new JCheckBox("软卧");
		RWseat.setBounds(437, 4, 62, 23);
		seatTypePanel.add(RWseat);
		RWseat.setSelected(true);

		YWseat = new JCheckBox("硬卧");
		YWseat.setBounds(503, 4, 62, 23);
		seatTypePanel.add(YWseat);
		YWseat.setSelected(true);

		RZseat = new JCheckBox("软座");
		RZseat.setBounds(569, 4, 62, 23);
		seatTypePanel.add(RZseat);
		RZseat.setSelected(true);

		YZseat = new JCheckBox("硬座");
		YZseat.setBounds(635, 4, 62, 23);
		seatTypePanel.add(YZseat);
		YZseat.setSelected(true);

		WZseat = new JCheckBox("无座");
		WZseat.setBounds(701, 4, 62, 23);
		seatTypePanel.add(WZseat);
		WZseat.setSelected(true);

		QTseat = new JCheckBox("其它");
		QTseat.setBounds(767, 4, 62, 23);
		seatTypePanel.add(QTseat);
		QTseat.setSelected(true);

		exchangeLabel = new JLabel(new ImageIcon("arrow.png"));
		exchangeLabel.setBounds(198, 54, 30, 13);
		exchangeLabel.setText("◄►");

		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(trainModel);
		frame.getContentPane().add(brushVotesBt);
		frame.getContentPane().add(tabbedPane);
		frame.getContentPane().add(scrollPane);
		frame.getContentPane().add(label_4);
		frame.getContentPane().add(setOutLabel);
		frame.getContentPane().add(setOutFidld);
		frame.getContentPane().add(exchangeLabel);
		frame.getContentPane().add(destinationLabel);
		frame.getContentPane().add(destinationField);
		frame.getContentPane().add(dateLabel);
		frame.getContentPane().add(dateField);
		frame.getContentPane().add(startTimeComboBox);
		frame.getContentPane().add(startTimeLabel);
		frame.getContentPane().add(studentRadioBt);
		frame.getContentPane().add(adultRadioBt);
		frame.getContentPane().add(disabilityRadioBt);
		frame.getContentPane().add(childRadioBt);
		frame.getContentPane().add(seatTypePanel);
		frame.getContentPane().add(trainTypePanel);
		frame.getContentPane().add(label_5);

		frame.addMouseListener(new MouseAdapter() { // 匿名内部类，鼠标事件
			public void mouseClicked(MouseEvent e) { // 鼠标完成点击事件
				if (e.getButton() == MouseEvent.BUTTON1) { // e.getButton就会返回点鼠标的那个键，左键还是右健，3代表右键
					int x = e.getX(); // 得到鼠标x坐标
					int y = e.getY(); // 得到鼠标y坐标
					mouse_gis[0] = x;
					mouse_gis[1] = y;
				}
			}
		});
	}

	/**
	 * 
	 * 校验刷票信息是否填写完毕
	 * 
	 */
	private void checkbrushVotesInfo() {
		// 验证出发地、目的地、时间等是否填写，没有则不刷票
		if ("".equals(setOutFidld.getText())) {
			logArea.append(format.format(new Date()) + "：请输入出发地\r\n");
			return;
		}
		if ("".equals(destinationField.getText())) {
			logArea.append(format.format(new Date()) + "：请输入目的地\r\n");
			return;
		}
		if ("".equals(dateField.getText())) {
			logArea.append(format.format(new Date()) + "：请输入日期\r\n");
			return;
		}
		if (passengerList.getModel().getSize() <= 0) {
			logArea.append(format.format(new Date()) + "：请选择乘车人\r\n");
			return;
		}
		if (seatTypeList.getModel().getSize() <= 0) {
			logArea.append(format.format(new Date()) + "：请先选择席别\r\n");
			return;
		}
		if (ticket_type == 1) {
			if (trainList.getModel().getSize() <= 0) {
				logArea.append(format.format(new Date()) + "：请先切换到手动查票模式下，查询出车次后，并右键添加到车次列表后再切为自动刷票模式\r\n");
				return;
			}
		}

		ListModel<Object> model = passengerList.getModel();
		StringBuffer sb2 = new StringBuffer();
		for (int i = 0; i < model.getSize(); i++) {
			sb2.append(model.getElementAt(i).toString());
			if (i != model.getSize() - 1) {
				sb2.append(",");
			}
		}

		sb = packagingBrushTicketInfo();
		seatOthers = packagingseatOther();
		// new BrushTicketMethods(window).start();// 刷票
	}

	/**
	 * 席别选择框方法 当选中该席别时，列表中对应的列显示，否则隐藏 当有隐藏列时，全部席别选择框设为未选中，否则选中
	 * 
	 * @param checkBox
	 *            点击的复选框
	 * @param col_num
	 *            对应在列表是第几列
	 * @param width
	 *            对应在列表的列原本的宽度
	 * @param panel
	 *            该组复选框的父类容器
	 * @param checkBox2
	 *            全部席别复选框
	 */
	private void CheckMethods(JCheckBox checkBox, int col_num, int width, JPanel panel, JCheckBox checkBox2) {
		if (!checkBox.isSelected()) {
			trainTable.getColumnModel().getColumn(col_num).setMinWidth(0);
			trainTable.getColumnModel().getColumn(col_num).setMaxWidth(0);
			trainTable.getColumnModel().getColumn(col_num).setPreferredWidth(0);
		} else {
			trainTable.getColumnModel().getColumn(col_num).setMinWidth(width);
			trainTable.getColumnModel().getColumn(col_num).setMaxWidth(width);
			trainTable.getColumnModel().getColumn(col_num).setPreferredWidth(width);
		}
		boolean check = true;
		Component[] comps = panel.getComponents();
		for (int i = 0; i < comps.length; i++) {
			Component comp = comps[i];
			if (comp instanceof JCheckBox) {
				JCheckBox box = (JCheckBox) comp;
				if (!"全部席别".equals(box.getText())) {
					if (!box.isSelected()) {
						check = false;
					}
				}
			}
		}
		if (check) {
			checkBox2.setSelected(true);
		} else {
			checkBox2.setSelected(false);
		}
	}

	/**
	 * 车次复选框功能
	 * 
	 * @param checkBox
	 *            复选框
	 * @param panel_1
	 *            车次复选框的父类容器
	 * @param checkBox_22
	 *            全部车次按钮
	 * @param num
	 *            车次编号的第一个字母
	 */
	private void CheckMethods2(JCheckBox checkBox, JPanel panel_1, JCheckBox checkBox_22, String num) {
		if (checkBox.isSelected()) {
			// 遍历其它车次的值，全部都是true，则勾选全部车次，否则全部车次不勾选
			Component[] comps = panel_1.getComponents();
			boolean check = true;
			for (int i = 0; i < comps.length; i++) {
				Component comp = comps[i];
				if (comp instanceof JCheckBox) {
					JCheckBox box = (JCheckBox) comp;
					if (!"全部车次".equals(box.getText())) {
						if (!box.isSelected()) {
							check = false;
							break;
						}
					}
				}
			}
			if (check) {
				checkBox_22.setSelected(true);
			} else {
				checkBox_22.setSelected(false);
			}
			// 显示行
			for (int i = 0; i < datalist.size(); i++) {
				JSONObject obj = datalist.get(i);
				String train_code;
				try {
					train_code = obj.get("station_train_code").toString();
					if (train_code.toUpperCase().startsWith(num)) {
						// TODO addRow(new String[]{},obj);
						addRow(new String[] {}, obj);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		} else {
			// 全部车次的值改为false
			checkBox_22.setSelected(false);
			// 隐藏行
			DefaultTableModel model = (DefaultTableModel) trainTable.getModel();
			int row_num = trainTable.getRowCount();
			for (int i = row_num - 1; i >= 0; i--) {
				String train_code = trainTable.getValueAt(i, 0).toString();
				if (train_code.toUpperCase().startsWith(num)) {
					model.removeRow(i);
				}
			}
		}
	}

	/**
	 * 选中所有行和列
	 */
	public void checkAllColRow() {
		// 处理行
		Component[] comps = trainTypePanel.getComponents();
		for (int i = 0; i < comps.length; i++) {
			Component comp = comps[i];
			if (comp instanceof JCheckBox) {
				JCheckBox box = (JCheckBox) comp;
				box.setSelected(true);
			}
		}
		for (int i = 0; i < datalist.size(); i++) {
			JSONObject obj = datalist.get(i);
			// TODO addRow(new String[]{},obj);
			addRow(new String[] {}, obj);
		}

		// 处理列
		Component[] comps2 = seatTypePanel.getComponents();
		for (int i = 0; i < comps2.length; i++) {
			Component comp = comps2[i];
			if (comp instanceof JCheckBox) {
				JCheckBox box = (JCheckBox) comp;
				box.setSelected(true);
			}
		}
		for (int i = 0; i < comps2.length; i++) {
			Component comp = comps2[i];
			if (comp instanceof JCheckBox) {
				JCheckBox box = (JCheckBox) comp;
				if (!"全部席别".equals(box.getText())) {
					int col_size = trainTable.getColumnModel().getColumnCount();
					int col_num = 0;
					for (int j = 0; j < col_size; j++) {
						if (box.getText().equals(trainTable.getColumnModel().getColumn(j).getHeaderValue())) {
							col_num = j;
						}
					}
					CheckMethods(box, col_num, box.getWidth(), seatTypePanel, (JCheckBox) comps2[0]);
				}
			}
		}
	}

	/**
	 * 打印信息
	 * 
	 * @param data
	 */
	public void printLog(String data) {
		logArea.append(format.format(new Date()) + "：" + data + "\r\n");
	}

	/**
	 * 
	 * 组装刷票信息
	 * 
	 */
	private String packagingBrushTicketInfo() {
		StringBuffer sb = new StringBuffer();
		sb.append("leftTicketDTO.train_date=");
		sb.append(dateField.getText() + "&");
		sb.append("leftTicketDTO.from_station=");
		sb.append(map.get(setOutFidld.getText())[2] + "&");
		sb.append("leftTicketDTO.to_station=");
		sb.append(map.get(destinationField.getText())[2] + "&");
		sb.append("purpose_codes=ADULT");
		return sb.toString();
	}

	/**
	 * 
	 * 组装票类型参数，用于自动刷票使用
	 * 
	 */
	public int[] packagingseatOther() {
		// 组装自动刷票判断
		ListModel<Object> model2 = seatTypeList.getModel();
		int[] seatOther = new int[model2.getSize()];
		result = true;
		if (ticket_type == 1) {
			for (int i = 0; i < model2.getSize(); i++) {
				if ("商务座".equalsIgnoreCase(model2.getElementAt(i).toString().trim())) {
					seatOther[i] = 32;
				} else if ("特等座".equalsIgnoreCase(model2.getElementAt(i).toString().trim())) {
					seatOther[i] = 25;
				} else if ("一等座".equalsIgnoreCase(model2.getElementAt(i).toString().trim())) {
					seatOther[i] = 31;
				} else if ("二等座".equalsIgnoreCase(model2.getElementAt(i).toString().trim())) {
					seatOther[i] = 30;
				} else if ("高级软卧".equalsIgnoreCase(model2.getElementAt(i).toString().trim())) {
					seatOther[i] = 21;
				} else if ("软卧".equalsIgnoreCase(model2.getElementAt(i).toString().trim())) {
					seatOther[i] = 23;
				} else if ("硬卧".equalsIgnoreCase(model2.getElementAt(i).toString().trim())) {
					seatOther[i] = 28;
				} else if ("软座".equalsIgnoreCase(model2.getElementAt(i).toString().trim())) {
					seatOther[i] = 24;
				} else if ("硬座".equalsIgnoreCase(model2.getElementAt(i).toString().trim())) {
					seatOther[i] = 29;
				} else if ("无座".equalsIgnoreCase(model2.getElementAt(i).toString().trim())) {
					seatOther[i] = 26;
				} else if ("其它".equalsIgnoreCase(model2.getElementAt(i).toString().trim())) {
					seatOther[i] = 22;
				}
			}

			// 于2017-04-24号以后失效
			// for (int i=0;i<model2.getSize();i++) {
			// if
			// ("商务座".equalsIgnoreCase(model2.getElementAt(i).toString().trim()))
			// {
			// seatOther[i] = "swz_num";
			// }else if
			// ("特等座".equalsIgnoreCase(model2.getElementAt(i).toString().trim()))
			// {
			// seatOther[i] = "tz_num";
			// }else if
			// ("一等座".equalsIgnoreCase(model2.getElementAt(i).toString().trim())){
			// seatOther[i] = "zy_num";
			// }else if
			// ("二等座".equalsIgnoreCase(model2.getElementAt(i).toString().trim())){
			// seatOther[i] = "ze_num";
			// }else if
			// ("高级软卧".equalsIgnoreCase(model2.getElementAt(i).toString().trim())){
			// seatOther[i] = "gr_num";
			// }else if
			// ("软卧".equalsIgnoreCase(model2.getElementAt(i).toString().trim())){
			// seatOther[i] = "rw_num";
			// }else if
			// ("硬卧".equalsIgnoreCase(model2.getElementAt(i).toString().trim())){
			// seatOther[i] = "yw_num";
			// }else if
			// ("软座".equalsIgnoreCase(model2.getElementAt(i).toString().trim())){
			// seatOther[i] = "rz_num";
			// }else if
			// ("硬座".equalsIgnoreCase(model2.getElementAt(i).toString().trim())){
			// seatOther[i] = "yz_num";
			// }else if
			// ("无座".equalsIgnoreCase(model2.getElementAt(i).toString().trim())){
			// seatOther[i] = "wz_num";
			// }else if
			// ("其它".equalsIgnoreCase(model2.getElementAt(i).toString().trim())){
			// seatOther[i] = "qt_num";
			// }
			// }
		}
		return seatOther;
	}

	/**
	 * 设置表格样式
	 */
	public void setTableSize() {
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < trainTable.getColumnModel().getColumnCount(); i++) {
			trainTable.getColumnModel().getColumn(i).setPreferredWidth(65);
			trainTable.getColumnModel().getColumn(i).setCellRenderer(render);
		}
		for (int i = 4; i < 6; i++) {
			trainTable.getColumnModel().getColumn(i).setPreferredWidth(90);
			trainTable.getColumnModel().getColumn(i).setCellRenderer(render);
		}
	}

	/**
	 * 获取乘客列表
	 * 
	 * @return
	 */
	private DefaultListModel<Object> getPassengerDTOs() {
		DefaultListModel<Object> model_Seats = new DefaultListModel<Object>();
		VHttpPost post = new VHttpPost("https://kyfw.12306.cn/otn/confirmPassenger/getPassengerDTOs");
		VHttpResponse res = VBrowser.execute(post);
		String body = VHttpUtils.outHtml(res.getBody());
		try {
			JSONObject res_obj = new JSONObject(body);
			JSONObject userListObj = (JSONObject) res_obj.get("data");
			if (userListObj.length() < 1) {
				logArea.append(format.format(new Date()) + "：" + new JSONObject(body).get("messages") + "\r\n");
			} else {
				JSONArray userArr = (JSONArray) (userListObj.get("normal_passengers"));
				for (int i = 0; i < userArr.length(); i++) {
					JSONObject obj = (JSONObject) userArr.get(i);
					model_Seats.addElement(obj.get("passenger_name"));
					userMap.put(obj.get("passenger_name").toString(), obj);
				}
			}
		} catch (JSONException e) {
			logArea.append(format.format(new Date()) + "：获取乘客列表失败，请联系作者QQ：3094759846\r\n");
		}
		return model_Seats;
	}

	/**
	 * 添加一行数据
	 * 
	 * @param obj
	 */
	public void addRow(String[] rows, JSONObject jsMap) {
		DefaultTableModel model = (DefaultTableModel) trainTable.getModel();
		Vector<String> vector = new Vector<String>();
		try {
			if (rows.length > 32) {
				String sw = "--";
				if (rows[32] == null || "".equals(rows[32].trim())) {
					sw = rows[32];
				}
				vector.add(rows[3]);
				vector.add(jsMap.getString(rows[6]));
				vector.add(jsMap.getString(rows[7]));
				vector.add(rows[10]);
				vector.add(rows[8]);
				vector.add(rows[9]);
				vector.add(sw);
				vector.add(rows[25]);
				vector.add(rows[31]);
				vector.add(rows[30]);
				vector.add(rows[21]);
				vector.add(rows[23]);
				vector.add(rows[28]);
				vector.add(rows[24]);
				vector.add(rows[29]);
				vector.add(rows[26]);
				vector.add(rows[22]);
				if ("Y".equalsIgnoreCase(rows[11])) {
					vector.add("可预订");
				} else {
					vector.add("不可预订");
				}
				model.addRow(vector);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
