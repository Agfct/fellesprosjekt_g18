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
		c.setTime(MainWindow.getDate());
		int year = c.get(Calendar.YEAR);
		int week = c.get(Calendar.WEEK_OF_YEAR);
		c.clear();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.WEEK_OF_YEAR, week);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		long start = c.getTimeInMillis();
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		long end = c.getTimeInMillis();
		appointments = MainWindow.getRequestHandler().getAllApointmentsByWeek(start, end, MainWindow.getUser()); // Your appointments by week (Invited and created)
		// Get the appointments you have told the calendar that you want from other employees
		ArrayList<Employee> otherSelectedEmployees = MainWindow.getLeftView().getSelectedEmployees();
		
		// Adding your appointments
		//Running trough all appointments that are yours
		for (Appointment appointment : appointments) {
			// if (you made it. Or you didnt make it and its not hidden)
			if(appointment.getCreator().getEmployee().equals(MainWindow.getUser()) || ((!appointment.getCreator().getEmployee().equals(MainWindow.getUser())) && 
					(!appointment.getInvitation(MainWindow.getUser()).isHidden() || MainWindow.getLeftView().getShowHidden()))){ // if its not hidden
				if(!(appointment.getCreator().getEmployee().equals(MainWindow.getUser()) && appointment.isDeleted())){
					AppointmentApp app = new AppointmentApp(appointment);
					add(app);
					app.setLocation(app.getX(), app.getY());
				}
			}
		}
		// Adding others appointments
		for (Employee employee : otherSelectedEmployees) {
			// Other Employees appointments (Invited and created)
			ArrayList<Appointment> otherAppointments = MainWindow.getRequestHandler().getAllApointmentsByWeek(start, end, employee);
			for (int i = 0; i < otherAppointments.size(); i++) {
				if(!appointments.contains(otherAppointments.get(i))){
					AppointmentApp app = new AppointmentApp(otherAppointments.get(i));
					app.setFetchedEmployee(employee);
					add(app);
					app.setLocation(app.getX(), app.getY());
				}
			}
		}
		
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
    		MainWindow.removeAppointmentAppWindow();
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
