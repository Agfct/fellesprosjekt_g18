package gui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import model.Appointment;
import model.Employee;
import model.InvitationStatus;


/** The Calendar for the application.
 *  This View displays the days in the week (The non scrollable view ontop of the calendarPanel).
 *	It has a custom CalendarPanel that contains all the appointments.
 *  CalendarView contains t
 *  
 * @author Anders
 */
public class CalendarView extends JPanel {
	private Image backgroundImg;
	private Image calendarImg;
	private JScrollPane calendarScrollPane;
	private CalendarPanel calendarPanel;
	private Font font;
	private  ArrayList<Appointment> yourAppointments;
	private  ArrayList<Appointment> invitedAppointments;
	private  ArrayList<Appointment> otherAppointments;
	
	public CalendarView(){
//		backgroundImg = new ImageIcon(this.getClass().getResource("/backgrounds/calendarBackground.png")).getImage();
		backgroundImg = MainWindow.getBackgroundImage();
		calendarImg = new ImageIcon(this.getClass().getResource("/backgrounds/calendarHeader.png")).getImage();
		
		setLayout(new GridBagLayout());
		setOpaque(false);
		//Setting Size
		

		/** Setting CalendarPanel **/
		calendarPanel = MainWindow.getCalendarPanel();
		
		GridBagConstraints cLabel0 = new GridBagConstraints();
		cLabel0.insets = new Insets(40,0,0,0);
		cLabel0.gridx = 0;
		cLabel0.gridy = 0;
		calendarScrollPane = new JScrollPane(calendarPanel);
		calendarScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		calendarScrollPane.getVerticalScrollBar().setUnitIncrement(16);
		calendarScrollPane.setPreferredSize(new Dimension(1000,660));
//		calendarScrollPane.setBounds(200, 200, 1000, 600);
//		calendarScrollPane.getViewport().setViewPosition(new Point(200,200));
		calendarScrollPane.setBorder(BorderFactory.createEmptyBorder());
		calendarScrollPane.setOpaque(false);
		calendarPanel.setScrollPane(calendarScrollPane);
		add(calendarScrollPane,cLabel0);
	}

	
	// Overriding the paintComponent to get Background and Week Days
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        //background Image
        g2d.drawImage(backgroundImg, -200, -100, this);
        
        //The Calendar Image
        g2d.drawImage(calendarImg, 0, 0, this);
        
        
        
 
//TODO: Draw propper day/month for all days
		paintDays(g2d);

    }
    
    public void paintDays(Graphics2D g2d){
    	// Draw a req on the day that is today
//    	if(dagen i dag er i den uken som vises, så sjekk hvilken dag det er og tegn)/
//    	Mon: (51, 3, 128, 26). Tue: (183, 3, 129, 26). Wed: (316, 3, 129, 26). Thu: (449, 3, 129, 26) Fri: (582, 3, 129, 26)
//    	Sat: (715, 3, 129, 26). Sun(848, 3, 129, 26);
    	g2d.setColor(Color.DARK_GRAY);
    	Stroke oldStroke = g2d.getStroke();
    	g2d.setStroke(new BasicStroke(3));
    	g2d.drawRect(848, 3, 129, 26);
    	g2d.setStroke(oldStroke);
    	
        //Paint the Days
 		font = new Font("Tahoma", Font.PLAIN, 12);
 		g2d.setFont(font);
 		g2d.setColor(Color.BLACK);
    	//mon
    	g2d.drawString("6/3", 118, 20);
    	//tue
    	g2d.drawString("7/3", 251, 20);
    	//wed
    	g2d.drawString("8/3", 384, 20);
    	//Thu
    	g2d.drawString("8/3", 517, 20);
    	//Fri
    	g2d.drawString("9/3", 650, 20);
    	//Sat
    	g2d.drawString("10/3", 783, 20);
    	//Sun
    	g2d.drawString("11/3", 916, 20);
    }
    
	/** ADDING APPOINTMENTS TO CALENDAR **/
	public void createOtherAppointments(){
		//TODO: ADD THE CLIENT CALL FOR APPOINTMENTS
		otherAppointments = new ArrayList<Appointment>(); // REMOVE
		for (int i = 0; i < MainWindow.getEmployeeList().size(); i++) {
			otherAppointments.add(new Appointment(MainWindow.getEmployeeList().get(i)));			
		}
	}
	public void createYourAppointments(){
		//TODO: ADD THE CLIENT CALL FOR APPOINTMENTS
		yourAppointments = new ArrayList<Appointment>();
//		yourAppointments = new ArrayList<Appointment>(); // REMOVE
//		for (int i = 0; i < MainWindow.getEmployeeList().size(); i++) {
//			yourAppointments.add(new Appointment(MainWindow.getEmployeeList().get(i)));			
//		}
	}
	public void createInvitedAppointments(){
		//TODO: ADD THE CLIENT CALL FOR APPOINTMENTS
		invitedAppointments = new ArrayList<Appointment>(); // REMOVE

	}
	
	public ArrayList<Appointment> getYourAppointments(){
		return yourAppointments;
	}
	public ArrayList<Appointment> getInvitedAppointments(){
		return invitedAppointments;
	}
	public ArrayList<Appointment> getOtherAppointments(){
		return otherAppointments;
	}
	
    public void refreshBackgroundImg(){
    	backgroundImg = MainWindow.getBackgroundImage();
    	repaint();
    	calendarPanel.refreshBackgroundImg();
    }
}
