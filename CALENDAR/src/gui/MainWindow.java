package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;


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
	private static JScrollPane calendarViewScrollPane;
	private static LoginView loginWindow;
	private static TopView topView;
	private static LeftView leftView;
	private static CalendarView calendarView;
	private static NewAppointmentView newAppointmentView;
	private static JPanel backgroundPanel; //TEST 
	private static Image backgroundImg; //TEST
	private static String font;
	private static Color textColor;
	private static Color btnBackgroundColor;

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
		
		//background TEST (creating a background Panel with an Image)
		backgroundImg = new ImageIcon(this.getClass().getResource("/backgrounds/background1.png")).getImage();
		backgroundPanel = new JPanel(){
			@Override
			  protected void paintComponent(Graphics g) {

			    super.paintComponent(g);
			        g.drawImage(backgroundImg, 0, 0, null);
			}	
		};
		backgroundPanel.setBounds(0,0,2000,2000);
		
		// JPanels
		loginWindow = new LoginView();
		topView = new TopView();
		topView.setBounds(0, 0, 1200, 100); // setting the area for the TopView (x,y,width,height)
		leftView = new LeftView();
		leftView.setBounds(0,100,200,700); // setting the area for the LeftView (x,y,width,height)
		calendarView = new CalendarView();
//		calendarView.setBounds(200,100,1000,900); // setting the area for the Calendar (x,y,width,height)
		
		// Panes
		mainScrollPane = new JScrollPane();
		mainScrollPane.setName("mainScrollPane");
		calendarViewScrollPane = new JScrollPane();
		calendarViewScrollPane.setName("calendarViewScrollPane");
		calendarViewScrollPane.setBounds(200, 100, 1000, 700);
		calendarViewScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		layoutView =  new JLayeredPane();
		layoutView.setPreferredSize(new Dimension(1181, 754)); //Size so that Scroll Bars reacts
		layoutView.add(backgroundPanel,JLayeredPane.DEFAULT_LAYER,0); //Background TEST	
		layoutView.add(topView,JLayeredPane.PALETTE_LAYER,1); //adding the topView to the DEFAULT (bottom) layer of the layoutPane
		layoutView.add(leftView,JLayeredPane.PALETTE_LAYER,2); //adding the topView to the DEFAULT (bottom) layer of the layoutPane
		layoutView.add(calendarViewScrollPane,JLayeredPane.PALETTE_LAYER,3); //adding the calendarView ScrollPane 
		calendarViewScrollPane.getViewport().add(calendarView); // adding the calendarView to the ScrollPane
			
		
		//adding stuff
		mainWindow.setContentPane(mainScrollPane);
		mainScrollPane.getViewport().add(loginWindow);
		mainWindow.setVisible(true);
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
	
	//adding an appointment view
	protected static void newAppointmentView(){
		//adding an newAppointmentView to the layerPane
		newAppointmentView = new NewAppointmentView();
		newAppointmentView.setBounds(0, 0, 1200, 800);
		layoutView.add(newAppointmentView,JLayeredPane.POPUP_LAYER,4);
//		layoutView.remove(newAppointmentView);
		
		//ONE WAY (nearly)
//		newAppointmentView = new NewAppointmentView();
//		newAppointmentView.setPreferredSize(new Dimension(1181, 754));
//		mainScrollPane.getViewport().add(newAppointmentView);
//		mainScrollPane.getViewport().remove(newAppointmentView);
	}
	protected static void removeNewAppointmentView(){
		layoutView.remove(newAppointmentView);
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