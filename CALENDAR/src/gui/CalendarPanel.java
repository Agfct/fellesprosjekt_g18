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

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

// The calendar Body
public class CalendarPanel extends JPanel {
	private JScrollPane calendarScrollPane;
	private Image calendarImg;
	private Image backgroundImg;
	private Font font;
	
	
	public CalendarPanel(){
		addMouseListener(new MAdapter(this));
		setOpaque(false);
		setLayout(null);
		calendarImg = new ImageIcon(this.getClass().getResource("/backgrounds/calendarBody.png")).getImage();
		backgroundImg = MainWindow.getBackgroundImage();
		
		setPreferredSize(new Dimension(979, 1440));
		drawAllAppointments(); //TEST
	}

	public void drawAllAppointments(){
//		for (int i = 0; i < appointments.size(); i++) { //Running trou all appointments
//			if(!appointments.get(i).getIsHidden()){ // if its not hidden
//				add(appointments.get(i).getAppointmentsApp());//put it in the pane
				AppointmentApp app1 = new AppointmentApp(48,0,133,120);
				add(app1);
				app1.setLocation(app1.getX(), app1.getY());
				AppointmentApp app2 = new AppointmentApp(181,0,133,60);
				add(app2);
				app2.setLocation(app2.getX(), app2.getY());
				//TODO: Create actuall appointments list and add, maby keep in array ?
//			}
//		}
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
