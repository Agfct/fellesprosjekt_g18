package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Box;

public class LoginWindow extends JPanel implements ActionListener {
	private JButton loginBtn;
	private JButton cancelBtn;
	private JLabel headerLabel;
	private JLabel userNameLabel;
	private JLabel passwordLabel;
	private JTextField userNameField;
	private JPasswordField passwordField;
	private Image backgroundImg;
	
	public LoginWindow(){
		// Using a GridBagLayout for the Grid
		setLayout(new GridBagLayout());
		backgroundImg = new ImageIcon(this.getClass().getResource("/backgrounds/background1.png")).getImage();
		setOpaque(false);

		/** CREATING BUTTONS, LABELS AND TEXT FIELDS **/
		
		//Header Text
		GridBagConstraints cLabel0 = new GridBagConstraints();
		cLabel0.insets = new Insets(0,50,0,0);
		cLabel0.gridx = 1;
		cLabel0.gridy = 0;
		cLabel0.gridwidth = 2;
		headerLabel = new JLabel("iCALENDAR");
		headerLabel.setName("headerLabel");
		//DESIGN for the Label text
		headerLabel.setForeground(MainWindow.getTxtColor());
		headerLabel.setFont(new Font(MainWindow.getMFont(),Font.BOLD,28));
//		loginBtn.setPreferredSize(new Dimension(200, 400));
		add(headerLabel,cLabel0);
		
		//Username Label & TextField
		GridBagConstraints cLabel1 = new GridBagConstraints();
		cLabel1.gridx = 1;
		cLabel1.gridy = 2;
		userNameLabel = new JLabel("Username");
		userNameLabel.setName("userNameLabel");
//		loginBtn.setPreferredSize(new Dimension(200, 400));
		add(userNameLabel,cLabel1);
		
		GridBagConstraints cLabel2 = new GridBagConstraints();
		cLabel2.gridx = 2;
		cLabel2.gridy = 2;
		userNameField = new JTextField(15);
		userNameField.setName("userNameField");
//		loginBtn.setPreferredSize(new Dimension(200, 400));
		add(userNameField,cLabel2);
		
		//Password Label & TextField
		GridBagConstraints cLabel3 = new GridBagConstraints();
		cLabel3.gridx = 1;
		cLabel3.gridy = 3;
		passwordLabel = new JLabel("Password");
		passwordLabel.setName("passwordLabel");
//		loginBtn.setPreferredSize(new Dimension(200, 400));
		add(passwordLabel,cLabel3);
		
		GridBagConstraints cLabel4 = new GridBagConstraints();
		cLabel4.gridx = 2;
		cLabel4.gridy = 3;
		passwordField = new JPasswordField(15);
		passwordField.setName("passwordField");
//		loginBtn.setPreferredSize(new Dimension(200, 400));
		add(passwordField,cLabel4);
		
		//Login Button
		GridBagConstraints cLabel5 = new GridBagConstraints();
		cLabel5.insets = new Insets(20,50,0,0);
		cLabel5.gridx = 1;
		cLabel5.gridy = 5;
		loginBtn = new JButton("Log In");
		loginBtn.setName("loginBtn");
		loginBtn.addActionListener(this);
		// DESIGN FOR BUTTON:
		loginBtn.setBackground(MainWindow.getBckColor());
		loginBtn.setForeground(MainWindow.getTxtColor());
		loginBtn.setFocusPainted(false);
		loginBtn.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 12));
//		loginBtn.setPreferredSize(new Dimension(200, 400));
		add(loginBtn,cLabel5);
		
		//Cancel Button
		GridBagConstraints cLabel6 = new GridBagConstraints();
		cLabel6.insets = new Insets(20,0,0,20);
		cLabel6.anchor = GridBagConstraints.LINE_END;
		cLabel6.gridx = 2;
		cLabel6.gridy = 5;
		cancelBtn = new JButton("Cancel");
		cancelBtn.setName("cancelBtn");
		cancelBtn.addActionListener(this);
		// DESIGN FOR BUTTON:
		cancelBtn.setBackground(MainWindow.getBckColor());
		cancelBtn.setForeground(MainWindow.getTxtColor());
		cancelBtn.setFocusPainted(false);
		cancelBtn.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 12));
		add(cancelBtn,cLabel6);
		
		//Filling boxes (for empty spaces)
		GridBagConstraints cLabel7 = new GridBagConstraints();
		cLabel7.gridx = 1;
		cLabel7.gridy = 4;
		add(new JLabel(" "),cLabel7);

		GridBagConstraints cLabel8 = new GridBagConstraints();
		cLabel8.gridx = 3;
		cLabel8.gridy = 2;
		add(new JLabel("               "),cLabel8);
		
		GridBagConstraints cLabel9 = new GridBagConstraints();
		cLabel9.gridx = 1;
		cLabel9.gridy = 1;
		add(new JLabel("               "),cLabel9);
		
		
		
	}
	
	// Overriding the paintComponent to get GraidientPaint
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        //background Image
        g2d.drawImage(backgroundImg, 0, 0, this);
        
        //GRADIENT BACKGROUND
//        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//        int w = getWidth();
//        int h = getHeight();
//        Color color1 = Color.BLUE;
//        Color color2 = Color.WHITE;
//        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h/2, color2);
//        g2d.setPaint(gp);
//        g2d.fillRect(0, 0, w, h);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("("+this.getClass()+"):"+ "Pressing a button");
		if(e.getSource() == loginBtn){
			System.out.println("Logging inn");
			MainWindow.setCalendarMode();
			
		}
		else if(e.getSource() == cancelBtn){
			System.out.println("EXITING APPLICATION");
			System.exit(0);
		}
	}
}
