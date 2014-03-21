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
import java.util.Calendar;
import java.util.Date;

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
        //Paint the Days
 		font = new Font("Tahoma", Font.PLAIN, 12);
 		g2d.setFont(font);
 		g2d.setColor(Color.BLACK);
 		Calendar c = Calendar.getInstance();
 		c.setTime(MainWindow.getDate());
 		boolean containsToday = false;
 		Calendar today = Calendar.getInstance();
    	//mon
 		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    	g2d.drawString(c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH)+1), 118, 20);
    	if (c.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR) && c.get(Calendar.YEAR) == today.get(Calendar.YEAR)){containsToday = true;}
    	//tue
    	c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
    	g2d.drawString(c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH)+1), 251, 20);
    	if (c.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR) && c.get(Calendar.YEAR) == today.get(Calendar.YEAR)){containsToday = true;}
    	//wed
    	c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
    	g2d.drawString(c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH)+1), 384, 20);
    	if (c.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR) && c.get(Calendar.YEAR) == today.get(Calendar.YEAR)){containsToday = true;}
    	//Thu
    	c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
    	g2d.drawString(c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH)+1), 517, 20);
    	if (c.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR) && c.get(Calendar.YEAR) == today.get(Calendar.YEAR)){containsToday = true;}
    	//Fri
    	c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
    	g2d.drawString(c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH)+1), 650, 20);
    	if (c.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR) && c.get(Calendar.YEAR) == today.get(Calendar.YEAR)){containsToday = true;}
    	//Sat
    	c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
    	g2d.drawString(c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH)+1), 783, 20);
    	if (c.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR) && c.get(Calendar.YEAR) == today.get(Calendar.YEAR)){containsToday = true;}
    	//Sun
    	c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
    	g2d.drawString(c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH)+1), 916, 20);
    	if (c.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR) && c.get(Calendar.YEAR) == today.get(Calendar.YEAR)){containsToday = true;}
    	
    	// Draw a req on the day that is today
//    	if(dagen i dag er i den uken som vises, så sjekk hvilken dag det er og tegn)/
//    	Mon: (51, 3, 128, 26). Tue: (183, 3, 129, 26). Wed: (316, 3, 129, 26). Thu: (449, 3, 129, 26) Fri: (582, 3, 129, 26)
//    	Sat: (715, 3, 129, 26). Sun(848, 3, 129, 26);
    	if (containsToday){
    		g2d.setColor(Color.DARK_GRAY);
    		Stroke oldStroke = g2d.getStroke();
    		g2d.setStroke(new BasicStroke(3));
    		if (today.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){g2d.drawRect(51, 3, 128, 26);}
    		else if (today.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY){g2d.drawRect(183, 3, 129, 26);}
    		else if (today.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY){g2d.drawRect(316, 3, 129, 26);}
    		else if (today.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY){g2d.drawRect(449, 3, 129, 26);}
    		else if (today.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY){g2d.drawRect(582, 3, 129, 26);}
    		else if (today.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){g2d.drawRect(715, 3, 129, 26);}
    		else if (today.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){g2d.drawRect(848, 3, 129, 26);}
    		
    		g2d.setStroke(oldStroke);
    	}
    }
    
    public void refreshBackgroundImg(){
    	backgroundImg = MainWindow.getBackgroundImage();
    	repaint();
    	calendarPanel.refreshBackgroundImg();
    }
}
