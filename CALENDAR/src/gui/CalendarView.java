package gui;

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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;


/** The Calendar for the application.
 *  This View displays the days in the week (The non scrollable view ontop of the calendarPanel).
 *	It has a custom CalendarPanel that contains all the appointments.
 *  
 * @author Anders
 */
public class CalendarView extends JPanel {
	private Image backgroundImg;
	private Image calendarImg;
	private JScrollPane calendarScrollPane;
	private CalendarPanel calendarPanel;
	private Font font;
	
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
        
        
        //Paint the Days
		font = new Font("Tahoma", Font.PLAIN, 12);
		g2d.setFont(font);
		g2d.setColor(Color.BLACK);
//TODO: Draw propper day/month for all days
		g2d.drawString("6/3", 118, 20);

    }
    public void refreshBackgroundImg(){
    	backgroundImg = MainWindow.getBackgroundImage();
    	repaint();
    	calendarPanel.refreshBackgroundImg();
    }
}
