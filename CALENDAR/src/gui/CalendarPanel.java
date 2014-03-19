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
	
	
	public CalendarPanel(){
		addMouseListener(new MAdapter(this));
		setOpaque(false);
		setLayout(null);
		calendarImg = new ImageIcon(this.getClass().getResource("/backgrounds/calendarBody.png")).getImage();
		backgroundImg = MainWindow.getBackgroundImage();
		
		setPreferredSize(new Dimension(979, 1440));
	}

	public void addAllAppointments(){
		removeAll();
		appointments = MainWindow.getRequestHandler().getAllApointments(MainWindow.getUser()); // Your appointments (Invited and created)
		System.out.println("User employee id: " +MainWindow.getUser().getEmployeeID());
		System.out.println("user"+ MainWindow.getUser());
		System.out.println("appointment nr 1"+ appointments.get(0));
		System.out.println("1 før is hidden: " +appointments.get(0).getInvitation(MainWindow.getUser()));
		System.out.println("ARE TEST: "+appointments.get(0).getInvitation(MainWindow.getUser()).isHidden());
		System.out.println("2 før is hidden: " +appointments.get(0).getInvitation(MainWindow.getUser()).isHidden());
		// Get the appointments you have told the calendar that you want from other employees
		ArrayList<Employee> otherSelectedEmployees = MainWindow.getLeftView().getSelectedEmployees();
		//TODO: FILTERE ETTER HVILKEN DAG / UKE DU ER I IKKE TA ALLE PÅ EN GANG !!
		
		// Adding your appointments
		for (int i = 0; i < appointments.size(); i++) { //Running trough all appointments that are yours
			System.out.println("før is hidden " +appointments.get(i).getInvitation(MainWindow.getUser()));
			System.out.println("nr1"+ appointments.get(i).getInvitation(MainWindow.getUser()).isHidden());
			if(!appointments.get(i).getInvitation(MainWindow.getUser()).isHidden() || MainWindow.getLeftView().getShowHidden()){ // if its not hidden

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

    // Sending click to all JPanels in this panel
	private class MAdapter extends MouseAdapter {
		private CalendarPanel panel;
		
		public MAdapter(CalendarPanel panel){
			this.panel = panel;
		}
		
		public void mousePressed(MouseEvent event){
			for (int i = 0; i < panel.getComponentCount(); i++) {
				((AppointmentApp) panel.getComponent(i)).mouseClicked(event);
			}
		}
		public void mouseReleased(MouseEvent event){

		}
		public void mouseClicked(MouseEvent event){

		}
	}
}
