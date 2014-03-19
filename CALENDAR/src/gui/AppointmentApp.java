package gui;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.Appointment;

/** This is the small window appearing in the CalendarPanel
 * 
 * @author Anders
 *
 */
public class AppointmentApp extends JPanel {
	private int sizeX;
	private int sizeY;
	private Appointment appointment;
	private int x;
	private int y;
	private Font font;
	private Image backgroundImg;

	// Days:
	// Default size = 133
	// Y is minutes y = 0 gives 00:00 in the morning
	// X is Day. Mon: 48 to 181, Tue: 181 to 314, Wed: 314 to 447, Thu: 447 to 580,
	// Fri: 580 to 713, Sat: 713 to 846, Sun: 856 to 977
	public AppointmentApp( Appointment appointment){
		setOpaque(false);
		this.appointment = appointment;
		setDateAndTime();

//		setBackground(new Color(59,89,182));
		backgroundImg = new ImageIcon(this.getClass().getResource("/backgrounds/appointmentAppBackground.png")).getImage();
	}
	
	public void setDateAndTime(){
		
		Calendar c = Calendar.getInstance();
		c.setTime(appointment.getDate());
		if (c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {x = 48;}
		else if(c.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {x = 181;}
		else if(c.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {x = 314;}
		else if(c.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {x = 447;}
		else if(c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {x = 580;}
		else if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {x = 713;}
		else if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {x = 856;}
		
		this.sizeX = 133; // Default subject to change
		this.sizeY = appointment.getDuration();
		this.y = appointment.getStartTimeAsInt();
		
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setSize(sizeX, sizeY);
	}
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        //background Image
        g2d.drawImage(backgroundImg, 0, 0, this);
        
        
        //The Calendar Image
//        g2d.drawImage(calendarImg, 0, 0, this);
        

		font = new Font("Tahoma", Font.BOLD, 12);
		g2d.setFont(font);
		g2d.setColor(Color.WHITE);
//		g2d.drawString(appointment.getTitle(), 177, 290);
		g2d.drawString("Meeting", 16, 16);
		
    }
    
    // Mouse Listner for the JPanel, so when people press the appointment they get the detail screen
	public void mouseClicked(MouseEvent e) {
		int eventX = (e.getX()); // for adjustments, not really needed after all
		int eventY = (e.getY());
		if (eventX > getX() && eventX < (getX() + getSizeX()) && 
				eventY > getY() && eventY < (getY() + getSizeY())){
			System.out.println("Appointment Panel Clicked");
			// Open edit window
			//TODO: Create edit Window
		}

	}
	
    public int getSizeX() {
		return sizeX;
	}

	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}
	public int getSizeY() {
		return sizeY;
	}

	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	

}
