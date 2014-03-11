package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicArrowButton;

/** TopView (JPanel)
 *  It contains the buttons for change in week (today, back, forth), and the logOut button
 *	It changes the calendar view based on week and goes to login screen through the MainView.MainWindow.setLoginMode();.
 *  
 * @author Anders
 */
public class TopView extends JPanel implements ActionListener{
	private JButton previousWeekBtn;
	private JButton nextWeekBtn;
	private JButton todayBtn;
	private JButton logOutBtn;
	private JLabel userNameLabel;
	private JLabel weekLabel;

	public TopView(){
		// Using a GridBagLayout for the Grid
		setLayout(new GridBagLayout());
		setOpaque(false);
//		setBackground(Color.GREEN);
		/** CREATING BUTTONS, LABELS AND TEXT FIELDS **/
		
		//Username Label
		GridBagConstraints cLabel0 = new GridBagConstraints();
		cLabel0.insets = new Insets(0,50,0,0);
		cLabel0.gridx = 0;
		cLabel0.gridy = 0;
		cLabel0.anchor = GridBagConstraints.LINE_START;
//		cLabel0.gridwidth = 2;
		userNameLabel = new JLabel("Username"); //replace with actual name
		userNameLabel.setName("userNameLabel");
		//DESIGN for the Label text
		userNameLabel.setForeground(MainWindow.getTxtColor());
		userNameLabel.setFont(new Font(MainWindow.getMFont(),Font.BOLD,18));
//		loginBtn.setPreferredSize(new Dimension(200, 400));
		add(userNameLabel,cLabel0);

		//previousWeekBtn
		GridBagConstraints cLabel1 = new GridBagConstraints();
//		cLabel1.insets = new Insets(20,50,0,0);
		cLabel1.gridx = 4;
		cLabel1.gridy = 0;
		previousWeekBtn = new JButton("<--");
		previousWeekBtn.setName("previousWeekBtn");
		previousWeekBtn.addActionListener(this);
		// DESIGN FOR BUTTON:
		previousWeekBtn.setBackground(MainWindow.getBckColor());
		previousWeekBtn.setForeground(MainWindow.getTxtColor());
		previousWeekBtn.setFocusPainted(false);
		previousWeekBtn.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 12));
//		loginBtn.setPreferredSize(new Dimension(200, 400));
		add(previousWeekBtn,cLabel1);
		
		//nextWeekBtn
		GridBagConstraints cLabel2 = new GridBagConstraints();
//		cLabel2.insets = new Insets(20,50,0,0);
		cLabel2.gridx = 5;
		cLabel2.gridy = 0;
		nextWeekBtn = new JButton("-->");
		nextWeekBtn.setName("nextWeekBtn");
		nextWeekBtn.addActionListener(this);
		// DESIGN FOR BUTTON:
		nextWeekBtn.setBackground(MainWindow.getBckColor());
		nextWeekBtn.setForeground(MainWindow.getTxtColor());
		nextWeekBtn.setFocusPainted(false);
		nextWeekBtn.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 12));
//		loginBtn.setPreferredSize(new Dimension(200, 400));
		add(nextWeekBtn,cLabel2);
		
		//Username Label
		GridBagConstraints cLabel3 = new GridBagConstraints();
//		cLabel0.insets = new Insets(0,50,0,0);
		cLabel3.gridx = 7;
		cLabel3.gridy = 0;
//		cLabel0.gridwidth = 2;
		weekLabel = new JLabel("Feb 23 - Mar 1, 2014 "); //replace with actual name
		weekLabel.setName("weekLabel");
		//DESIGN for the Label text
		weekLabel.setForeground(MainWindow.getTxtColor());
		weekLabel.setFont(new Font(MainWindow.getMFont(),Font.BOLD,18));
//		loginBtn.setPreferredSize(new Dimension(200, 400));
		add(weekLabel,cLabel3);
		
		//logOutBtn
		GridBagConstraints cLabel4 = new GridBagConstraints();
//		cLabel1.insets = new Insets(20,50,0,0);
		cLabel4.gridx = 10;
		cLabel4.gridy = 0;
		logOutBtn = new JButton("Log Out");
		logOutBtn.setName("logOutBtn");
		logOutBtn.addActionListener(this);
		// DESIGN FOR BUTTON:
		logOutBtn.setBackground(MainWindow.getBckColor());
		logOutBtn.setForeground(MainWindow.getTxtColor());
		logOutBtn.setFocusPainted(false);
		logOutBtn.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 12));
//		loginBtn.setPreferredSize(new Dimension(200, 400));
		add(logOutBtn,cLabel4);
		
		//todayBtn
		GridBagConstraints cLabel5 = new GridBagConstraints();
//		cLabel1.insets = new Insets(20,50,0,0);
		cLabel5.gridx = 2;
		cLabel5.gridy = 0;
		todayBtn = new JButton("Today");
		todayBtn.setName("todayBtn");
		todayBtn.addActionListener(this);
		// DESIGN FOR BUTTON:
		todayBtn.setBackground(MainWindow.getBckColor());
		todayBtn.setForeground(MainWindow.getTxtColor());
		todayBtn.setFocusPainted(false);
		todayBtn.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 12));
//		loginBtn.setPreferredSize(new Dimension(200, 400));
		add(todayBtn,cLabel5);
		
		
		//Filling boxes (for empty spaces)
		// Between Username and todayBtn
		GridBagConstraints cLabel6 = new GridBagConstraints();
		cLabel6.gridx = 1;
		cLabel6.gridy = 0;
		add(new JLabel("                     " +
				"                            " +
				"                            "),cLabel6);
		
		//between todayBtn and prevWeekBtn
		GridBagConstraints cLabel7 = new GridBagConstraints();
		cLabel7.gridx = 3;
		cLabel7.gridy = 0;
		add(new JLabel("                      "),cLabel7);
		
		// Between arrows and week display
		GridBagConstraints cLabel8 = new GridBagConstraints();
		cLabel8.gridx = 6;
		cLabel8.gridy = 0;
		add(new JLabel("                 "),cLabel8);
		
		//between week display and Log out btn
		GridBagConstraints cLabel9 = new GridBagConstraints();
		cLabel9.gridx = 9;
		cLabel9.gridy = 0;
		add(new JLabel("                                  " +
				"                                         "),cLabel9);

	}
	
    /** LISTENERS FOR THE ENTIRE JPANEL **/
    /** WHEN FIELDS ARE MODIFIED CHANGES ARE REGISTERED HERE **/
	//What happens when the different buttons are pressed.
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("("+this.getClass()+"):"+ "Pressing a button");
		
		// If Logout Button is pressed
		if(e.getSource() == logOutBtn){
			System.out.println("Logging Out");
			/** FIX HERE OR IN MAIN WINDOW **/
			// Saving data?
			// Get prev login info?
			// Everything is going down, is everything taken care of ?
			MainWindow.setLoginMode(); 	
		}
		// If the "Today button is pressed
		else if(e.getSource() == todayBtn){
			System.out.println("Going to Todays week");
		}
		// If the "<--" Previous week button is pressed
		else if(e.getSource() == previousWeekBtn){
			System.out.println("Going to the Previous Week");
			// 
		}
		// If the "-->" Next week button is pressed
		else if(e.getSource() == nextWeekBtn){
			System.out.println("Going to the Next Week");
			// 
		}
	}
	
	// Overriding the paintComponent to get GraidientPaint
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//        int w = getWidth();
//        int h = getHeight();
//        Color color1 = Color.BLUE;
//        Color color2 = Color.WHITE;
//        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
//        g2d.setPaint(gp);
//        g2d.fillRect(0, 0, w, h);
//    }
}
