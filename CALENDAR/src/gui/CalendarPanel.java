package gui;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Appointment;
import model.Employee;

// The calendar Body
public class CalendarPanel extends JPanel {
	private JScrollPane calendarScrollPane;
	private Image calendarImg;
	private Image backgroundImg;
	private Font font;
	private ArrayList<Appointment> appointments;
	private AppointmentAppWindow appointmentAppWindow;
	private boolean isHit;
	
	
	public CalendarPanel(){
		addMouseListener(new MAdapter(this));
		setOpaque(false);
		setLayout(null);
		calendarImg = new ImageIcon(this.getClass().getResource("/backgrounds/calendarBody.png")).getImage();
		backgroundImg = MainWindow.getBackgroundImage();
		
		isHit = false;
		setPreferredSize(new Dimension(979, 1440));
	}

	public void addAllAppointments(){
		removeAll();
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		long start = c.getTimeInMillis();
		String weekText = c.getTime().toString().substring(4, 10) + " - "; //Month: MMM
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		long end = c.getTimeInMillis();
		weekText += c.getTime().toString().substring(4,10);
		weekText += ", " + c.get(Calendar.YEAR);
		appointments = MainWindow.getRequestHandler().getAllApointmentsByWeek(start, end, MainWindow.getUser()); // Your appointments by week (Invited and created)
		// Get the appointments you have told the calendar that you want from other employees
		ArrayList<Employee> otherSelectedEmployees = MainWindow.getLeftView().getSelectedEmployees();
		
		//TODO: FILTERE ETTER HVILKEN DAG / UKE DU ER I IKKE TA ALLE PÅ EN GANG !!
		
		// Adding your appointments
		for (int i = 0; i < appointments.size(); i++) { //Running trough all appointments that are yours
			// if (you made it. Or you didnt make it and its not hidden)
			if(appointments.get(i).getCreator().getEmployee().equals(MainWindow.getUser()) || ((!appointments.get(i).getCreator().getEmployee().equals(MainWindow.getUser())) && 
					(!appointments.get(i).getInvitation(MainWindow.getUser()).isHidden() || MainWindow.getLeftView().getShowHidden()))){ // if its not hidden
				AppointmentApp app = new AppointmentApp(appointments.get(i));
				add(app);
				app.setLocation(app.getX(), app.getY());
			}
		}
		// Adding others appointments
		for (Employee employee : otherSelectedEmployees) {
			// Other Employees appointments (Invited and created)
			ArrayList<Appointment> otherAppointments = MainWindow.getRequestHandler().getAllApointments(employee); 
			for (int i = 0; i < otherAppointments.size(); i++) {
				if(!appointments.contains(otherAppointments.get(i))){
					AppointmentApp app = new AppointmentApp(otherAppointments.get(i));
					add(app);
					app.setLocation(app.getX(), app.getY());
				}
			}
		}
		
	}
	// adding appointmentApp Window
	public void setAppointmentAppWindow(Appointment appointment, int x ,int  y){
		//adding an AppointmentsAppwindow to the layerPane
		removeAppointmentAppWindow();
		appointmentAppWindow = new AppointmentAppWindow(appointment, x, y);
		setComponentZOrder(appointmentAppWindow, 0);//TODO: FIX
		add(appointmentAppWindow);
		appointmentAppWindow.setLocation(x, y);
	}
	public void removeAppointmentAppWindow(){
		if (appointmentAppWindow != null ){
			remove(appointmentAppWindow);
		}
		repaint();
	}
	
	// Overriding the paintComponent to get Background
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        //background Image
//        g2d.drawImage(backgroundImg, -200, -100, this);
        
        
        //The Calendar Image
        g2d.drawImage(calendarImg, 0, 0, this);
        

    }
    
	public void setScrollPane(JScrollPane newCalendarScrollPane){
		calendarScrollPane = newCalendarScrollPane;
		
	}
	
    public void refreshBackgroundImg(){
    	backgroundImg = MainWindow.getBackgroundImage();
    	repaint();
    }
    // if an appointment got hit
    public void gotHit(){
    	isHit = true;
    }
    public void checkIfHit(){
    	// If you didnt press any apps
    	if(!isHit){
    		removeAppointmentAppWindow();
    	}
    	isHit = false;
    }
    // Sending click to all JPanels in this panel
	private class MAdapter extends MouseAdapter {
		private CalendarPanel panel;
		
		public MAdapter(CalendarPanel panel){
			this.panel = panel;
		}
		
		public void mousePressed(MouseEvent event){
			for (int i = 0; i < panel.getComponentCount(); i++) {
				if( panel.getComponent(i) instanceof AppointmentApp){
					((AppointmentApp) panel.getComponent(i)).mouseClicked(event);
				}
			}
			checkIfHit();
		}
		public void mouseReleased(MouseEvent event){

		}
		public void mouseClicked(MouseEvent event){

		}
	}
}
