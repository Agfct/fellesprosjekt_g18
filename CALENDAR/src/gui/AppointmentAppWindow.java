package gui;

import java.awt.Color;
import java.awt.Font;
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
	private Font font;
	
	public AppointmentAppWindow(Appointment appointment , int x,int y){
//		setOpaque(false);
		this.appointment = appointment;
		this.x = x;
		this.y = y;
		
		this.sizeX = 400;
		this.sizeY = 600;

//		setBackground(new Color(59,89,182));
		backgroundImg = new ImageIcon(this.getClass().getResource("/backgrounds/appointmentAppViewBackgroundT2.png")).getImage();
		
		
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
        
        font = new Font("Tahoma", Font.BOLD, 18);
        g2d.setFont(font);
        g2d.setColor(Color.WHITE);
        
        // if your created the appointment
        if(appointment.getCreator().getEmployee().equals(MainWindow.getUser())){
        	g2d.drawString(appointment.getTitle(), 250, 16);
        	font = new Font("Tahoma", Font.BOLD, 12);
        	g2d.setFont(font);
        	g2d.drawString("Creator: "+MainWindow.getUser().getName(), 250, 32);
        
        	// if you are invited to the appointment
        }else if(appointment.getInvitation(MainWindow.getUser()) != null){
        	//text for invited
        	g2d.drawString(appointment.getTitle(), 250, 16);
        	font = new Font("Tahoma", Font.BOLD, 12);
        	g2d.setFont(font);
        	g2d.drawString("Invited Appointment", 250, 32);
        	g2d.drawString("Your Status: [ "+appointment.getInvitation(MainWindow.getUser()).getStatus().getStatusOnlyText()+" ]", 250, 48);
        	if(appointment.isDeleted()){
            	font = new Font("Tahoma", Font.BOLD, 14);
            	g2d.setFont(font);
        		g2d.drawString("DELETED", 250, 64);
        	}
        	else if(appointment.getInvitation(MainWindow.getUser()).isDeleted()){
            	font = new Font("Tahoma", Font.BOLD, 14);
            	g2d.setFont(font);
        		g2d.drawString("UNINVITED", 250, 64);
        	}
        	
        }else{ // other appointments
        	//text for invited
        	g2d.drawString(appointment.getTitle(), 250, 16);
        	font = new Font("Tahoma", Font.BOLD, 12);
        	g2d.setFont(font);
        	g2d.drawString("Other", 250, 32);
        	g2d.drawString("Creator: " + appointment.getCreator().getEmployee().getName(), 250, 48);
        }

    }
    public void setBackgroundImg(Image newImg){
    	backgroundImg = newImg;
    }
}
