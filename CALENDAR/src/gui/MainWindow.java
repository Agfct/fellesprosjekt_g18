package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import model.Appointment;
import model.Employee;


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
	private static EditAppointmentsView editAppointmentsView;
	private static AppointmentsView appointmentsView;
	private static JPanel backgroundPanel; //TEST 
	private static Image backgroundImg; //TEST
	private static String font;
	private static Color textColor;
	private static Color btnBackgroundColor;
	private static ArrayList<Employee> allEmployees;
	
	private static Employee user;

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
		mainWindow.createEmployeeList();
	}
//	public static MainWindow getMainWindow(){
//		return mainWindow;
//	}
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
	protected static void newAppointmentView(Appointment newAppointment , boolean newOrEdit){
		//adding an newAppointmentView to the layerPane
		// True = newAppointmentViee, False = editAppointmentView
		newAppointmentView = new NewAppointmentView(newAppointment,newOrEdit);
		newAppointmentView.setBounds(0, 0, 1200, 800);
		layoutView.add(newAppointmentView,JLayeredPane.POPUP_LAYER,4);
	}
	protected static void removeNewAppointmentView(){
		layoutView.remove(newAppointmentView);
		layoutView.repaint();
	}
	public static NewAppointmentView getNewAppoitnmentsView(){
		return newAppointmentView;
	}
	public static CalendarPanel getCalendarPanel(){
		return calendarPanel;
	}
	
	//adding an appointmentView
	protected static void appointmentsView(){
		//adding an AppointmentsView to the layerPane
		appointmentsView = new AppointmentsView();
		appointmentsView.setBounds(0, 0, 1200, 800);
		layoutView.add(appointmentsView,JLayeredPane.POPUP_LAYER,4);
	}
	protected static void removeAppointmentsView(){
		layoutView.remove(appointmentsView);
		layoutView.repaint();
	}
	
	//adding an editAppointmentsView
	protected static void editAppointmentsView(){
		//adding an AppointmentsView to the layerPane
		editAppointmentsView = new EditAppointmentsView();
		editAppointmentsView.setBounds(0, 0, 1200, 800);
		layoutView.add(editAppointmentsView,JLayeredPane.POPUP_LAYER,4);
	}
	protected static void removeEditAppointmentsView(){
		layoutView.remove(editAppointmentsView);
		layoutView.repaint();
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
	public static Employee getUser(){
		return user;
	}
	public static Image getBackgroundImage(){
		return backgroundImg;
	}
	
	// WARNING CONNECT WITH DATABASE
	public void createEmployeeList(){
		//TEST
		Employee anders = new Employee("Anders");
		Employee silje = new Employee("Silje");
		Employee katrine = new Employee("Katrine");
		Employee are = new Employee("Are");
		Employee birger = new Employee("Birger");
		Employee stian = new Employee("Stian");

		allEmployees = new ArrayList<Employee>();
		allEmployees.add(anders);
		allEmployees.add(silje);
		allEmployees.add(katrine);
		allEmployees.add(are);
		allEmployees.add(birger);
		allEmployees.add(stian);
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
		System.out.println("REPAINTING ALL MUHAHA");
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
