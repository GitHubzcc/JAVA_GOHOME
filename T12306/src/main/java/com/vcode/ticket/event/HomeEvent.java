package com.vcode.ticket.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vcode.ticket.homeEventImpl.AllSeatTypeEvent;
import com.vcode.ticket.homeEventImpl.AllTrainTypeEvent;
import com.vcode.ticket.homeEventImpl.BrushVotesBtEvent;
import com.vcode.ticket.homeEventImpl.DateFieldEvent;
import com.vcode.ticket.homeEventImpl.ExchangeLabelEvent;
import com.vcode.ticket.homeEventImpl.SeatTypeEvent;
import com.vcode.ticket.homeEventImpl.SetOutFidldEvent;
import com.vcode.ticket.homeEventImpl.StartTimeComboBoxEvent;
import com.vcode.ticket.homeEventImpl.TrainModelEvent;
import com.vcode.ticket.homeEventImpl.TrainTypeEvent;
import com.vcode.ticket.homeEventImpl.TrainTableEvent;
import com.vcode.ticket.ui.HomePage;

@Component
public class HomeEvent<T> {

	@Autowired
	private HomePage<T> Page;
	
	@Autowired
	private ExchangeLabelEvent<T> exchangeLabelEvent;
	
	@Autowired
	private StartTimeComboBoxEvent<T> startTimeComboBoxEvent;
	
	@Autowired
	private AllTrainTypeEvent<T> allTrainTypeEvent;
	
	@Autowired
	private TrainTypeEvent<T> trainTypeEvent;
	
	@Autowired
	private AllSeatTypeEvent<T> allSeatTypeEvent;
	
	@Autowired
	private SeatTypeEvent<T> seatTypeEvent;

	@Autowired
	private TrainModelEvent<T> trainModelEvent;
	
	@Autowired
	private BrushVotesBtEvent<T> brushVotesBtEvent;
	
	@Autowired
	private TrainTableEvent<T> trainTableEvent;

	public void addEvents(){
		//出发地、目的地输入框事件
		SetOutFidldEvent.addEvent(Page);
		
		//出发地与目的地呼唤按键事件
		Page.exchangeLabel.addMouseListener(exchangeLabelEvent);
		
		//为日期输入框添加日期控件
		DateFieldEvent.addEvent(Page);
		
		//发车时间事件
		Page.startTimeComboBox.addActionListener(startTimeComboBoxEvent);
		
		//车型复选框事件
		Page.allTrainType.addMouseListener(allTrainTypeEvent);//全部车型
		Page.Gtrain.addMouseListener(trainTypeEvent);
//		Page.Gtrain.addMouseListener(new TrainTypeEvent(Page, Page.Ctrain, Page.trainTypePanel, Page.allTrainType, "C"));
//		Page.Gtrain.addMouseListener(new TrainTypeEvent(Page, Page.Dtrain, Page.trainTypePanel, Page.allTrainType, "D"));
//		Page.Gtrain.addMouseListener(new TrainTypeEvent(Page, Page.Ttrain, Page.trainTypePanel, Page.allTrainType, "T"));
//		Page.Gtrain.addMouseListener(new TrainTypeEvent(Page, Page.Ztrain, Page.trainTypePanel, Page.allTrainType, "Z"));
//		Page.Gtrain.addMouseListener(new TrainTypeEvent(Page, Page.Ktrain, Page.trainTypePanel, Page.allTrainType, "K"));
//		Page.Gtrain.addMouseListener(new TrainTypeEvent(Page, Page.Ptrain, Page.trainTypePanel, Page.allTrainType, "P"));
//		Page.Gtrain.addMouseListener(new TrainTypeEvent(Page, Page.Ltrain, Page.trainTypePanel, Page.allTrainType, "L"));
//		Page.Gtrain.addMouseListener(new TrainTypeEvent(Page, Page.QTtrain, Page.trainTypePanel, Page.allTrainType, "QT"));
		
		//席别复选框事件
		Page.allSeatType.addMouseListener(allSeatTypeEvent);//全部席别
		Page.SWseat.addMouseListener(seatTypeEvent);
		Page.TDseat.addMouseListener(seatTypeEvent);
		Page.YDseat.addMouseListener(seatTypeEvent);
		Page.RDseat.addMouseListener(seatTypeEvent);
		Page.GRseat.addMouseListener(seatTypeEvent);
		Page.RWseat.addMouseListener(seatTypeEvent);
		Page.YWseat.addMouseListener(seatTypeEvent);
		Page.RZseat.addMouseListener(seatTypeEvent);
		Page.YZseat.addMouseListener(seatTypeEvent);
		Page.WZseat.addMouseListener(seatTypeEvent);
		Page.QTseat.addMouseListener(seatTypeEvent);
		
		//刷票模式事件
		Page.trainModel.addMouseListener(trainModelEvent);
		
		//刷票按钮事件
		Page.brushVotesBt.addMouseListener(brushVotesBtEvent);
		
		//余票列表事件
		Page.trainTable.addMouseListener(trainTableEvent);
	}
}
