package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

// The calendar Body
public class CalendarPanel extends JPanel{
	private JScrollPane calendarScrollPane;
	private Image calendarImg;
	private Image backgroundImg;
	private Font font;
	
	
	public CalendarPanel(){
		setOpaque(false);
		calendarImg = new ImageIcon(this.getClass().getResource("/backgrounds/calendarBody.png")).getImage();
		backgroundImg = MainWindow.getBackgroundImage();
		
		setPreferredSize(new Dimension(979, 1440));
		
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
}
