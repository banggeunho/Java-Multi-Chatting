package Chat.Sources.Client.Src;
import Chat.Sources.Client.Src.Calc;
import java.awt.Panel;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.*;

public class TapPanel extends Panel implements CommonSettings,ActionListener
{
	TapPanel(ChatClient parent)
	{
	  /***********Initialize the Components***********/
	  chatclient = parent;
	  
	  Panel Tappanel = new Panel(new BorderLayout());
	  CardLayout cardlayout = new CardLayout();
	  Panel MainPanel = new Panel(cardlayout);
	  
	  /*******User Panel Coding Starts***********/
	  Panel UserPanel = new Panel(new BorderLayout());
	  UserCanvas = new ListViewCanvas(chatclient,USER_CANVAS);
	  
	  UserScrollView = new ScrollView(UserCanvas,true,true,TAPPANEL_CANVAS_WIDTH,TAPPANEL_CANVAS_HEIGHT,SCROLL_BAR_SIZE);
	  UserCanvas.scrollview = UserScrollView;	  	 
	  UserPanel.add("Center",UserScrollView);
	  
	  Panel UserButtonPanel = new Panel(new BorderLayout());
	  CmdSendDirect = new CustomButton(chatclient,"Send Direct Message");
	  CmdSendDirect.addActionListener(this);
	  UserButtonPanel.add("North",CmdSendDirect);
	  CmdIgnoreUser = new CustomButton(chatclient,"Ignore User");
	  CmdIgnoreUser.addActionListener(this);
	  UserButtonPanel.add("Center",CmdIgnoreUser);
	  UserPanel.add("South",UserButtonPanel);
	  
	  /********Room Panel Coding Starts***********/
	  Panel RoomPanel = new Panel(new BorderLayout());
	  RoomCanvas = new ListViewCanvas(chatclient,ROOM_CANVAS);
	  
	  RoomScrollView = new ScrollView(RoomCanvas,true,true,TAPPANEL_CANVAS_WIDTH,TAPPANEL_CANVAS_HEIGHT,SCROLL_BAR_SIZE);
	  RoomCanvas.scrollview = RoomScrollView;	  
	  RoomPanel.add("Center",RoomScrollView);	  
	  
	  Panel RoomButtonPanel = new Panel(new BorderLayout());
	  Panel RoomCountPanel = new Panel(new BorderLayout());
	  Label LblCaption = new Label("ROOM COUNT",1);
	  RoomCountPanel.add("North",LblCaption);
	  TxtUserCount = new TextField();
	  TxtUserCount.setEditable(false);
	  RoomCountPanel.add("Center",TxtUserCount);	  	  
	  RoomButtonPanel.add("Center",RoomCountPanel);
	  
	  CmdChangeRoom = new CustomButton(chatclient,"Change Room");
	  CmdChangeRoom.addActionListener(this);
	  RoomButtonPanel.add("South",CmdChangeRoom);
	  
	  RoomPanel.add("South",RoomButtonPanel);
	  
	  
	  /********Image Panel Coding Starts***********/
	  Panel ImagePanel = new Panel(new BorderLayout());
	  imagecanvas = new ImageCanvas(chatclient);
	  ImageScrollView = new ScrollView(imagecanvas,true,true,TAPPANEL_CANVAS_WIDTH,TAPPANEL_CANVAS_HEIGHT,SCROLL_BAR_SIZE);
	  imagecanvas.scrollview = ImageScrollView;
	  
	  
	  
	  /**********Add Icons into MessageObject *********/
	  imagecanvas.AddIconsToMessageObject();
	  ImagePanel.add("Center",ImageScrollView);
	  
	  /* Make new Panel to plus functions 
	   * 
	   * 
	   * 
	   * */
	  Panel PlusPanel = new Panel(new BorderLayout());
	  Pluscanvas = new ImageCanvas(chatclient);
	  PlusScrollView = new ScrollView(Pluscanvas,true,true,TAPPANEL_CANVAS_WIDTH,TAPPANEL_CANVAS_HEIGHT,SCROLL_BAR_SIZE);
	  Pluscanvas.scrollview = PlusScrollView;
	  PlusPanel.add("Center",PlusScrollView);
	  
	  Panel PlusButtonPanel = new Panel(new BorderLayout());
	  Abob = new CustomButton(chatclient,"FindMealTime");
	  Abob.addActionListener(this);
	  PlusButtonPanel.add("North",Abob);
	  Calculator = new CustomButton(chatclient,"Calculator");
	  Calculator.addActionListener(this);
	  PlusButtonPanel.add("Center",Calculator);
	  Calendar = new CustomButton(chatclient,"Calendar");
	  Calendar.addActionListener(this);
	  PlusButtonPanel.add("South",Calendar);
	  PlusPanel.add("North",PlusButtonPanel);

	  /*
	   * 
	   * 
	   * 
	  Panel UserButtonPanel = new Panel(new BorderLayout());
	  CmdSendDirect = new CustomButton(chatclient,"Send Direct Message");
	  CmdSendDirect.addActionListener(this);
	  UserButtonPanel.add("North",CmdSendDirect);
	  CmdIgnoreUser = new CustomButton(chatclient,"Ignore User");
	  CmdIgnoreUser.addActionListener(this);
	  UserButtonPanel.add("Center",CmdIgnoreUser);
	  UserPanel.add("South",UserButtonPanel);
	   * 
	   * 
	   */
	  
	  /*********Add All the Panel in to Main Panel*********/
	  MainPanel.add("UserPanel",UserPanel);
	  MainPanel.add("RoomPanel",RoomPanel);
	  MainPanel.add("ImagePanel",ImagePanel);
	  MainPanel.add("PlusPanel", PlusPanel);
	  cardlayout.show(MainPanel,"UserPanel");
	  BorderPanel borderpanel = new BorderPanel(this,chatclient,cardlayout,MainPanel,TAPPANEL_WIDTH,TAPPANEL_HEIGHT);
	  
	  borderpanel.addTab("USERS","UserPanel");
	  //borderpanel.addTab("ROOMS","RoomPanel");
	  borderpanel.addTab("EMOTICONS","ImagePanel");
	  borderpanel.addTab("PLUS","PlusPanel");
	  
	  Tappanel.add(borderpanel);
	  add("Center",Tappanel);	  		  
	  
	  
	  /********Common Things***********/	  	    	  
	}
	
	/***********Action Listener coding **********/
	public void actionPerformed(ActionEvent evt)
	{
		if(evt.getSource().equals(CmdChangeRoom))
		{
			/******** Change Room Coding *********/
			chatclient.ChangeRoom();	
		}
		
		if(evt.getSource().equals(CmdIgnoreUser))
		{			
			if(evt.getActionCommand().equals("Ignore User"))
			{
				UserCanvas.IgnoreUser(true);				
			}
			else
			{
				UserCanvas.IgnoreUser(false);					
			}
		}
		
		if(evt.getSource().equals(CmdSendDirect))
		{
			UserCanvas.SendDirectMessage();	
		}	
		
		if(evt.getSource().equals(Abob)) {
			Abob_main a1 = new Abob_main();
		}
		
		if(evt.getSource().equals(Calculator)) {
			Calc c1 = new Calc();
		}
		
		if(evt.getSource().equals(Calendar)) {
			SwingCalender m1 = new SwingCalender();
		}
	}
	
	
	
	/*********Global Variable Declarations***********/	
	ChatClient chatclient;
	protected TextField TxtUserCount;
	ScrollView ImageScrollView,UserScrollView,RoomScrollView,PlusScrollView;
	protected ImageCanvas Pluscanvas;
	protected ImageCanvas imagecanvas;
	protected ListViewCanvas UserCanvas,RoomCanvas;
	Button CmdChangeRoom,CmdIgnoreUser,CmdSendDirect,Calculator,Abob,Calendar;
}