package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.Appointment;
import model.Invitation;

public class AppointmentAppWindow extends JComponent{
	
	private int sizeX;
	private int sizeY;
	private Appointment appointment;
	private int x;
	private int y;
	private Image backgroundImg;
	private Font font;
	private JTextArea descriptionField;
	
	public AppointmentAppWindow(Appointment appointment , int x,int y){
//		setOpaque(false);
		setLayout(new GridBagLayout());
		this.appointment = appointment;
		this.x = x;
		this.y = y;
		
		this.sizeX = 400;
		this.sizeY = 600;

//		setBackground(new Color(59,89,182));
		backgroundImg = new ImageIcon(this.getClass().getResource("/backgrounds/appointmentAppViewBackgroundT2.png")).getImage();
		
		
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setSize(sizeX, sizeY);
		
		// descriptionField
		GridBagConstraints cLabel0 = new GridBagConstraints();
		cLabel0.insets = new Insets(110,-182,0,0);
		cLabel0.gridx = 11;
		cLabel0.gridy = 1;
		cLabel0.gridwidth = 4;
		cLabel0.gridheight = 5;
//		cLabel9.ipady = 70;
		descriptionField = new JTextArea(10,20);
		descriptionField.setPreferredSize(new Dimension(10,20));
		descriptionField.setLineWrap(true);
		cLabel0.fill = GridBagConstraints.HORIZONTAL;
//		cLabel9.anchor = GridBagConstraints.LINE_START;
		descriptionField.setName("descriptionField");
		descriptionField.setEditable(false);
		// DESIGN FOR Field:
//		descriptionField.setBackground(MainWindow.getBckColor());
//		descriptionField.setForeground(MainWindow.getTxtColor());
		descriptionField.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 12));
		descriptionField.setText(appointment.getDescription());
		add(descriptionField,cLabel0);
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
        
        
        // Y: Title: 32, Creator 48
        ///invited/other: 64,  Status: 80, DELorUNINV: 96
        // if your created the appointment
        if(appointment.getCreator().getEmployee().equals(MainWindow.getUser())){
        	g2d.drawString(appointment.getTitle(), 250, 32);
        	font = new Font("Tahoma", Font.BOLD, 12);
        	g2d.setFont(font);
        	g2d.drawString("Creator: "+MainWindow.getUser().getName(), 250, 48);
        	if(appointment.isDeleted()){
            	font = new Font("Tahoma", Font.BOLD, 14);
            	g2d.setFont(font);
        		g2d.drawString("DELETED", 250, 96);
        	}
        
        	// if you are invited to the appointment
        }else if(appointment.getInvitation(MainWindow.getUser()) != null){
        	//text for invited
        	g2d.drawString(appointment.getTitle(), 250, 32);
        	font = new Font("Tahoma", Font.BOLD, 12);
        	g2d.setFont(font);
        	g2d.drawString("Creator: "+appointment.getCreator().getEmployee().getName(), 250, 48);
        	g2d.drawString("Invited Appointment", 250, 64);
        	g2d.drawString("Your Status: [ "+appointment.getInvitation(MainWindow.getUser()).getStatus().getStatusOnlyText()+" ]", 250, 80);
    		String hide;
    		boolean hidden = appointment.getInvitation(MainWindow.getUser()).isHidden();
    		if (hidden){
    			hide = "hidden";
    		}else{
    			hide = "visible";
    		}
    		g2d.drawString(hide, 100, 144);
    		
        	if(appointment.isDeleted()){
            	font = new Font("Tahoma", Font.BOLD, 14);
            	g2d.setFont(font);
        		g2d.drawString("DELETED", 250, 96);
        	}
        	else if(appointment.getInvitation(MainWindow.getUser()).isDeleted()){
            	font = new Font("Tahoma", Font.BOLD, 14);
            	g2d.setFont(font);
        		g2d.drawString("UNINVITED", 250, 96);
        	}
        	
        }else{ // other appointments
        	//text for invited
        	g2d.drawString(appointment.getTitle(), 250, 32);
        	font = new Font("Tahoma", Font.BOLD, 12);
        	g2d.setFont(font);
        	g2d.drawString("Creator: " + appointment.getCreator().getEmployee().getName(), 250, 48);
        	g2d.drawString("Other", 250, 64);
        }
        
        // Writing Appointment information
        //Time:
        String time = "Time: "+appointment.getStartTime()+"-"+appointment.getEndTime();
        String date = appointment.getDateString();
		String room;
		if(appointment.getRoom() != null && appointment.isInternal()){
			 room = appointment.getRoom().toString();
		}
		else if(appointment.isInternal()){
			 room = "TBA";
		}
		else{
			 room = appointment.getLocation();
		}
		g2d.drawString(room, 100, 128);
    	font = new Font("Tahoma", Font.BOLD, 12);
    	g2d.setFont(font);
    	g2d.drawString(time, 100, 112);
    	g2d.drawString(date, 100, 160);
    	g2d.drawString("Description:", 100, 176);
    	g2d.drawString("Participants: ", 350, 112);
    	
    	
    	// All participants for the appointment
    	if(appointment.getInvitations() != null){
    		int row = 128;
    		for (Invitation invite : appointment.getInvitations()) {
    			if(!invite.isDeleted()){
    				g2d.drawString(invite.getEmployee().getName()+ ", Status: " +invite.getStatus().getStatusOnlyText(), 350, (row));
    			}
    			row += 16;
			}
    	}
    }
    
    public void setBackgroundImg(Image newImg){
    	backgroundImg = newImg;
    }
}
