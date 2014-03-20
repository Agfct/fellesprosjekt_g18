package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;

import model.Appointment;

public class AppointmentAppWindow extends JComponent{
	
	private int sizeX;
	private int sizeY;
	private Appointment appointment;
	private int x;
	private int y;
	private Image backgroundImg;
	
	public AppointmentAppWindow(Appointment appointment , int x,int y){
//		setOpaque(false);
		this.appointment = appointment;
		this.x = x;
		this.y = y;
		
		this.sizeX = 400;
		this.sizeY = 400;

//		setBackground(new Color(59,89,182));
		backgroundImg = new ImageIcon(this.getClass().getResource("/backgrounds/appointmentAppViewBackground.png")).getImage();
		
		
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setSize(sizeX, sizeY);
	}
	
	
	// Overriding the paintComponent to get BackgroundImg
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        //background Image
        g2d.drawImage(backgroundImg, 0, 0, this);

    }
    public void setBackgroundImg(Image newImg){
    	backgroundImg = newImg;
    }
}
