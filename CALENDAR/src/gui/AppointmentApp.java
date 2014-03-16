package gui;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

	public AppointmentApp( int x, int y, int sizeX, int sizeY){
		setOpaque(false);
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.x = x;
		this.y = y;
		
		
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setSize(sizeX, sizeY);
//		setBackground(new Color(59,89,182));
		backgroundImg = new ImageIcon(this.getClass().getResource("/backgrounds/appointmentAppBackground.png")).getImage();
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
