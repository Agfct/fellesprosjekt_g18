package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import networkClient.Client;
import networkClient.RequestHandler;
import networkDiv.Packet;
import model.Appointment;
import model.Employee;
import model.Invitation;


/** The Main (and Only) JFrame for the application.
 *  It contains the program main method and it starts the application.
 *	It Creates the loginWindow, and the scroll and layer panes.
 *  
 * @author Anders
 */
public class MainWindow extends JFrame{
	private static MainWindow mainWindow;
	private static JLayeredPane layoutView;
	private static JScrollPane mainScrollPane;
//	private static JScrollPane calendarViewScrollPane;
	private static LoginView loginWindow;
	private static TopView topView;
	private static LeftView leftView;
	private static CalendarView calendarView;
	private static CalendarPanel calendarPanel;
	private static NewAppointmentView newAppointmentView;
	private static EmailPanel emailPanel;
	private static EditAppointmentsView editAppointmentsView;
	private static AppointmentAppWindow appointmentAppWindow;
	private static AppointmentsView appointmentsView;
	private static JPanel backgroundPanel; //TEST 
	private static Image backgroundImg; //TEST
	private static String font;
	private static Color textColor;
	private static Color btnBackgroundColor;
	private static ArrayList<Employee> allEmployees;
	private static Client networkClient;
	private static RequestHandler requestHandler;
	private static Date date;
	private static boolean aWindowIsUp;
	
	/** The Main user "you", of the program **/
	private static Employee user;


	//ip and port for testing
	private static String serverIP = "localhost";
	private static int serverPort = 6060;



	public static void main(String[] args) {
		new MainWindow(); // Start
		
	}
	
	public MainWindow(){
		//Setup for the main window, (size and other details)
		mainWindow = this;
		mainWindow.setSize(400, 200);
		mainWindow.setResizable(true);
		mainWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainWindow.setTitle("I CALENDAR");
		mainWindow.setLocationRelativeTo(null);
		
		//font and Color for the program
		font = "Tahoma";
		textColor = Color.WHITE;
		btnBackgroundColor = new Color(59,89,182);
		backgroundImg = new ImageIcon(mainWindow.getClass().getResource("/backgrounds/background1.png")).getImage();

		
		//background TEST (creating a background Panel with an Image)
		backgroundPanel = new JPanel(){
			@Override
			  protected void paintComponent(Graphics g) {

			    super.paintComponent(g);
			        g.drawImage(backgroundImg, 0, 0, null);
			}	
		};
		backgroundPanel.setBounds(0,0,2000,2000);
		aWindowIsUp = false;
		
		//adding network listener
		networkClient = new Client(serverIP, serverPort);
		requestHandler = new RequestHandler(networkClient);
		
		//Getting employees from database
		mainWindow.createEmployeeList();
		
		// JPanels
		calendarPanel = new CalendarPanel(); //Panel must be created before the CalendarView
		loginWindow = new LoginView();
		topView = new TopView();
		topView.setBounds(0, 0, 1200, 100); // setting the area for the TopView (x,y,width,height)
		leftView = new LeftView();
		leftView.setBounds(0,100,200,700); // setting the area for the LeftView (x,y,width,height)
		calendarView = new CalendarView();
		calendarView.setBounds(200,100,1000,700); // setting the area for the Calendar (x,y,width,height)
		
		// Panes
		mainScrollPane = new JScrollPane();
		mainScrollPane.setName("mainScrollPane");
//		calendarViewScrollPane = new JScrollPane();
//		calendarViewScrollPane.setName("calendarViewScrollPane");
//		calendarViewScrollPane.setBounds(200, 100, 1000, 700);
//		calendarViewScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//		calendarViewScrollPane.setBorder(BorderFactory.createEmptyBorder());
		layoutView =  new JLayeredPane();
		layoutView.setPreferredSize(new Dimension(1181, 754)); //Size so that Scroll Bars reacts
		layoutView.add(backgroundPanel,JLayeredPane.DEFAULT_LAYER,0); //Background TEST	
		layoutView.add(topView,JLayeredPane.PALETTE_LAYER,1); //adding the topView to the DEFAULT (bottom) layer of the layoutPane
		layoutView.add(leftView,JLayeredPane.PALETTE_LAYER,2); //adding the topView to the DEFAULT (bottom) layer of the layoutPane
		layoutView.add(calendarView,JLayeredPane.PALETTE_LAYER,3); //adding the calendarView ScrollPane 
//		layoutView.add(calendarViewScrollPane,JLayeredPane.PALETTE_LAYER,3); //adding the calendarView ScrollPane 
//		calendarViewScrollPane.getViewport().add(calendarView); // adding the calendarView to the ScrollPane
		

		
		//adding stuff
		mainWindow.setContentPane(mainScrollPane);
		mainScrollPane.getViewport().add(loginWindow);
		mainWindow.setVisible(true);
		TimeZone tz = TimeZone.getTimeZone("Europe/Oslo");
		TimeZone.setDefault(tz);
		date = new Date();
		

	}
	public static MainWindow getMainWindow(){
		return mainWindow;
	}
	// Returns the mainScrollPane for use outside of class
	public static JScrollPane getMainScrollPane(){
		return mainScrollPane;
	}
	// This changes from the LoginView to the 3 main views (Top,Left and Calendar)
	protected static void setCalendarMode(){
		mainScrollPane.getViewport().remove(loginWindow);
		mainWindow.setSize(1216, 838); // Resize the window
		mainWindow.setLocationRelativeTo(null); // Putting the rezised window back into the middle
		mainScrollPane.getViewport().add(layoutView); // adding the LayoutView
		changeDate();
		
		
	}

	//This changes from Calendar (3 view) mode to the LoginView
	protected static void setLoginMode(){
		// Putting everything back to loginWindow mode
		mainScrollPane.getViewport().remove(layoutView);
		mainWindow.setSize(400, 200);
		mainWindow.setLocationRelativeTo(null); // Putting the rezised window back into the middle
		mainScrollPane.getViewport().add(loginWindow);
	}
	
	//adding an newAppointmentView
	protected static void newAppointmentView(Appointment newAppointment , boolean newOrEdit ,String from){
		//adding an newAppointmentView to the layerPane
		// True = newAppointmentViee, False = editAppointmentView
		aWindowIsUp = true;
		newAppointmentView = new NewAppointmentView(newAppointment,newOrEdit, from);
		newAppointmentView.setBounds(0, 0, 1200, 800);
		layoutView.add(newAppointmentView,JLayeredPane.POPUP_LAYER,4);
	}
	protected static void removeNewAppointmentView(){
		aWindowIsUp = false;
		layoutView.remove(newAppointmentView);
		layoutView.repaint();
		calendarPanel.addAllAppointments();
	}
	public static NewAppointmentView getNewAppoitnmentsView(){
		return newAppointmentView;
	}
	public static TopView getTopView(){
		return topView;
	}
	public static LeftView getLeftView(){
		return leftView;
	}
	public static CalendarPanel getCalendarPanel(){
		return calendarPanel;
	}
	
	//adding an appointmentView
	protected static void appointmentsView(){
		//adding an AppointmentsView to the layerPane
		aWindowIsUp = true;
		appointmentsView = new AppointmentsView();
		appointmentsView.setBounds(0, 0, 1200, 800);
		layoutView.add(appointmentsView,JLayeredPane.POPUP_LAYER,4);
	}
	protected static void removeAppointmentsView(){
		aWindowIsUp = false;
		layoutView.remove(appointmentsView);
		layoutView.repaint();
		calendarPanel.addAllAppointments();
	}
	
	//adding an editAppointmentsView
	protected static void editAppointmentsView(){
		//adding an AppointmentsView to the layerPane
		aWindowIsUp = true;
		editAppointmentsView = new EditAppointmentsView();
		editAppointmentsView.setBounds(0, 0, 1200, 800);
		layoutView.add(editAppointmentsView,JLayeredPane.POPUP_LAYER,4);
	}
	protected static void removeEditAppointmentsView(){
		aWindowIsUp = false;
		layoutView.remove(editAppointmentsView);
		layoutView.repaint();
		calendarPanel.addAllAppointments();
	}
	// adding emailPanels
	protected static void newEmailPanel(Invitation invitation){
		//adding an AppointmentsView to the layerPane
		emailPanel = new EmailPanel(invitation);
		emailPanel.setBounds(0, 0, 1200, 800);
		layoutView.add(emailPanel,JLayeredPane.DRAG_LAYER,5);
	}
	protected static void removeEmailPanel(){
		layoutView.remove(emailPanel);
		layoutView.repaint();
	}
	// adding appointmentApp Window
	public static void setAppointmentAppWindow(Appointment appointment, int x ,int  y){
		//adding an AppointmentsAppwindow to the layerPane
		if(appointmentAppWindow != null ){
			layoutView.remove(appointmentAppWindow);
		}
		// if no other window is up
		if(!aWindowIsUp){
			appointmentAppWindow = new AppointmentAppWindow(appointment, x, y);
			appointmentAppWindow.setBounds(400, 200, 600, 400);
			layoutView.add(appointmentAppWindow,JLayeredPane.DRAG_LAYER,5);
		}
	}
	public static void removeAppointmentAppWindow(){
		if(appointmentAppWindow != null ){
			layoutView.remove(appointmentAppWindow);
		}
		layoutView.repaint();
	}
	
	public static RequestHandler getRequestHandler(){
		return requestHandler;
	}
	// Type of Font used in program
	public static String getMFont(){
		return font;
	}
	// Color used on the Front (Color of the Text)
	public static Color getTxtColor(){
		return textColor;
	}
	// Color used on the Background (Button Color and so on)
	public static Color getBckColor(){
		return btnBackgroundColor;
	}
	
	public static void setUser(Employee setUser){
		user = setUser;
	}
	public static Employee getUser(){
		return user;
	}
	public static Image getBackgroundImage(){
		return backgroundImg;
	}
	
	// WARNING CONNECT WITH DATABASE
	public void createEmployeeList(){
		System.out.println("dette er null" + getRequestHandler());
		allEmployees = getRequestHandler().getAllEmployees();
		allEmployees.remove(getUser());
		
	}
	public static ArrayList<Employee> getEmployeeList(){
		return allEmployees;
		
		
	}
	//Setting color and theme for CALENDAR
	public static void setFontAndColor(int nr){
		if(nr == 1){
			font = "Tahoma";
			textColor = Color.WHITE;
			btnBackgroundColor = new Color(194,32,42);
			backgroundImg = new ImageIcon(mainWindow.getClass().getResource("/backgrounds/background2.png")).getImage();
			mainWindow.repaintAll(); //repaint all views
		}
		else{ //Default color
			font = "Tahoma";
			textColor = Color.WHITE;
			btnBackgroundColor = new Color(59,89,182);
			backgroundImg = new ImageIcon(mainWindow.getClass().getResource("/backgrounds/background1.png")).getImage();
			mainWindow.repaintAll(); //repaint all views
		}
	}
	private void repaintAll(){
		loginWindow.setBackgroundImg(backgroundImg);
		loginWindow.repaint();
		topView.repaint();
		leftView.repaint();
		layoutView.repaint();
		calendarView.refreshBackgroundImg();
		for (int i = 0; i < leftView.getComponentCount(); i++) {
			if (leftView.getComponent(i).getBackground() != new JTextField().getBackground() &&  
					leftView.getComponent(i).getBackground() != new JComboBox().getBackground()){
				leftView.getComponent(i).setBackground(MainWindow.getBckColor());
				leftView.getComponent(i).setForeground(MainWindow.getTxtColor());
			}
		}
		for (int i = 0; i < loginWindow.getComponentCount(); i++) {
			if (loginWindow.getComponent(i).getBackground() != new JTextField().getBackground() &&  
					loginWindow.getComponent(i).getBackground() != new JComboBox().getBackground()){
				loginWindow.getComponent(i).setBackground(MainWindow.getBckColor());
				loginWindow.getComponent(i).setForeground(MainWindow.getTxtColor());
			}
		}
		for (int i = 0; i < topView.getComponentCount(); i++) {
			if (topView.getComponent(i).getBackground() != new JTextField().getBackground() &&  
					topView.getComponent(i).getBackground() != new JComboBox().getBackground()){
				topView.getComponent(i).setBackground(MainWindow.getBckColor());
				topView.getComponent(i).setForeground(MainWindow.getTxtColor());
			}
		}
	}
	// Sending login request and attempting to log in
	public static void requestLogin(String user, String password){		
		System.out.println("MainWindow: Sending loginRequest...");
		Packet response = requestHandler.loginRequest(user, password);
		System.out.println("MainWindow: Response received" + response.getName());

		if (response.getName().equals("LOGIN_ACCEPTED")){
			Employee newUser = requestHandler.getEmployeeByParticipantID(((Employee) response.getObject(0)).getParticipantID());
			System.out.println("EMPLOYEE ID: "+ newUser.getEmployeeID());
			System.out.println("PARTICIPANTS ID: "+ newUser.getParticipantID());
			setUser(newUser);
			getTopView().setUserName(newUser.getName());
			mainWindow.createEmployeeList();
			getLeftView().addEmployees();
			setCalendarMode();
		}
		else{
			// add warning message here
			System.out.println("MainWindow: can not log inn");
			setLoginMode();
		}
	}
	
	public static void setDate(Date newDate){
		date = newDate;
		changeDate();
	}
	public static Date getDate(){
		return date;
	}
	
	public static void changeDate(){
		topView.setTopViewDate();
		leftView.setLeftViewWeek();
		calendarView.repaint();
		removeAppointmentAppWindow();
		calendarPanel.addAllAppointments();
		
	}

	// TEST FOR TIME ALTERNATIVES
	public static ArrayList<String> getTimeArray(){
		ArrayList<String> timeArray = new ArrayList<String>();
		for (int i = 0; i < 25; i++) {
			int k = 0;
			if( i == 24){ //special case when 24:00
				timeArray.add(i+":00");
			}
			else{
				for (int j = 0; j < 2; j++) {
					if( i == 24){
						timeArray.add(i+":00");
					}
					else{
						if (k==0){
							timeArray.add(i+":00");
							k = 30;
						}
						else{
							timeArray.add(i+":30");
							k = 0;
						}
					}
				}
			}
		}
		return timeArray;
	}

}
