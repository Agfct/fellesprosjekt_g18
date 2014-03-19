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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


import model.Appointment;
import model.Employee;
import model.Invitation;


public class EmailPanel extends JPanel implements ActionListener, FocusListener {
	private JLabel headerLabel;


	private JButton cancelButton;
	private JButton saveButton;
	private JButton sendEmailButton;
	private JLabel nameLabel;
	private JLabel emailLabel;
	private JLabel messageLabel;
	private JTextField nameField;
	private JTextField emailField;
	private JTextArea editMessageField;


	private String name;
	private String email;
	private String message;


	private Image backgroundImg;


	public EmailPanel(Invitation invite){


		//Information for the message
		name = invite.getEmployee().getName();
		String desc = invite.getAppointment().getDescription();
		email = invite.getEmployee().getEmail();
		String startTime = invite.getAppointment().getStartTime();
		String endTime = invite.getAppointment().getEndTime();
		String location = invite.getAppointment().getLocation();


		System.out.println(name);
		System.out.println(endTime);
		System.out.println(startTime);


		setLayout(new GridBagLayout());
		//				backgroundImg = new ImageIcon(this.getClass().getResource("/backgrounds/background1.png")).getImage();
		backgroundImg = MainWindow.getBackgroundImage();
		setOpaque(false);


		/** CREATING BUTTONS, LABELS AND TEXT FIELDS **/


		//Header Text
//		GridBagConstraints cLabel0 = new GridBagConstraints();
//		cLabel0.insets = new Insets(0,0,0,0);
//		cLabel0.gridx = 2;
//		cLabel0.gridy = 0;
//		cLabel0.gridwidth = 2;
//		cLabel0.anchor = GridBagConstraints.LINE_START;
//		headerLabel = new JLabel("Email");
//		headerLabel.setName("headerLabel");
//		//DESIGN for the Label text
//		headerLabel.setForeground(MainWindow.getTxtColor());
//		headerLabel.setFont(new Font(MainWindow.getMFont(),Font.BOLD,28));
//		//				loginBtn.setPreferredSize(new Dimension(200, 400));
//		add(headerLabel,cLabel0);


		//name Label & TextField
		GridBagConstraints cLabel1 = new GridBagConstraints();
		cLabel1.gridx = 1;
		cLabel1.gridy = 2;
		nameLabel = new JLabel("Name");
		nameLabel.setName("externalNameLabel");
		//				loginBtn.setPreferredSize(new Dimension(200, 400));
		add(nameLabel,cLabel1);


		GridBagConstraints cLabel2 = new GridBagConstraints();
		cLabel2.gridx = 2;
		cLabel2.gridy = 2;
		cLabel2.anchor = GridBagConstraints.LINE_START;
		nameField = new JTextField(30);
		nameField.setName("externalNameField");
		nameField.setText(name);
		nameField.setEditable(false);
		this.name = nameField.getText();
		//				loginBtn.setPreferredSize(new Dimension(200, 400));
		add(nameField,cLabel2);


		//email Label & TextField
		GridBagConstraints cLabel3 = new GridBagConstraints();
		cLabel3.gridx = 1;
		cLabel3.gridy = 3;
		emailLabel = new JLabel("Email");
		emailLabel.setName("emailLabel");
		//				loginBtn.setPreferredSize(new Dimension(200, 400));
		add(emailLabel,cLabel3);


		GridBagConstraints cLabel4 = new GridBagConstraints();
		cLabel4.gridx = 2;
		cLabel4.gridy = 3;
		cLabel4.anchor = GridBagConstraints.LINE_START;
		emailField = new JTextField(30);
		emailField.setName("emailField");
		if(email != null){
			emailField.setText(email);
		}
		else if(email == null){
			emailField.setText("siljechristensen92@gmail.com");
		}
		this.email = emailField.getText();
		add(emailField,cLabel4);


		//MessageLabel
		GridBagConstraints cLabel11 = new GridBagConstraints();
		cLabel11.gridx = 1;
		cLabel11.gridy = 5;
		messageLabel = new JLabel("Message ");
		messageLabel.setName("messageLabel");
		add(messageLabel, cLabel11);


		//MessageField
		GridBagConstraints cLabel12 = new GridBagConstraints();
		cLabel12.gridx = 2;
		cLabel12.gridy = 5;
		cLabel12.gridheight = 5;
		cLabel12.gridwidth = 2;
		editMessageField = new JTextArea(5,30);
		//editDescriptionField.setPreferredSize(new Dimension(5,35));
		editMessageField.setLineWrap(true);
		cLabel12.fill = GridBagConstraints.HORIZONTAL;
		cLabel12.anchor = GridBagConstraints.LINE_START;
		editMessageField.setName("messageField");
		if(editMessageField.getText().trim().length() == 0){
			editMessageField.setText("Hello " + name + "! " + '\n' + '\n' + "You have been invited to a meeting at  "+ location + " from "  + startTime + " until " + endTime + ". " + '\n'+ desc + '\n'+ '\n' + "Please responnd as soon as possible.");
		}
		this.message = editMessageField.getText();
		editMessageField.addFocusListener(this);
		// DESIGN FOR Field:
		editMessageField.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 12));
		//descriptionScrollPane = new JScrollPane(descriptionField); //NOT WORKING
		add(editMessageField,cLabel12);


		//save Button
//		GridBagConstraints cLabel5 = new GridBagConstraints();
//		cLabel5.insets = new Insets(20,50,0,0);
//		cLabel5.gridx = 1;
//		cLabel5.gridy = 10;
//		saveButton = new JButton("Save");
//		saveButton.setName("saveButton");
//		saveButton.addActionListener(this);
//		// DESIGN FOR BUTTON:
//		saveButton.setBackground(MainWindow.getBckColor());
//		saveButton.setForeground(MainWindow.getTxtColor());
//		saveButton.setFocusPainted(false);
//		saveButton.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 12));
//		//				loginBtn.setPreferredSize(new Dimension(200, 400));
//		add(saveButton,cLabel5);


		//Send Email Button
		GridBagConstraints cLabel6 = new GridBagConstraints();
		cLabel6.insets = new Insets(20,50,0,0);
		cLabel6.gridx = 2;
		cLabel6.gridy = 10;
		sendEmailButton = new JButton("Send email");
		sendEmailButton.setName("sendEmailButton");
		sendEmailButton.addActionListener(this);
		// DESIGN FOR BUTTON:
		sendEmailButton.setBackground(MainWindow.getBckColor());
		sendEmailButton.setForeground(MainWindow.getTxtColor());
		sendEmailButton.setFocusPainted(false);
		sendEmailButton.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 12));
		//				loginBtn.setPreferredSize(new Dimension(200, 400));
		add(sendEmailButton,cLabel6);

		//Cancel Button
		GridBagConstraints cLabel7 = new GridBagConstraints();
		cLabel7.insets = new Insets(20,0,0,0);
		cLabel7.anchor = GridBagConstraints.LINE_END;
		cLabel7.gridx = 3;
		cLabel7.gridy = 10;
		cancelButton = new JButton("Cancel");
		cancelButton.setName("cancelBtn");
		cancelButton.addActionListener(this);
		// DESIGN FOR BUTTON:
		cancelButton.setBackground(MainWindow.getBckColor());
		cancelButton.setForeground(MainWindow.getTxtColor());
		cancelButton.setFocusPainted(false);
		cancelButton.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 12));
		add(cancelButton,cLabel7);

		//Filling boxes (for empty spaces)
		GridBagConstraints cLabel8 = new GridBagConstraints();
		cLabel8.gridx = 1;
		cLabel8.gridy = 4;
		add(new JLabel(" "),cLabel8);


		GridBagConstraints cLabel9 = new GridBagConstraints();
		cLabel9.gridx = 3;
		cLabel9.gridy = 2;
		add(new JLabel("               "),cLabel9);


		GridBagConstraints cLabe10 = new GridBagConstraints();
		cLabe10.gridx = 1;
		cLabe10.gridy = 1;
		add(new JLabel("               "),cLabe10);


		GridBagConstraints cLabe13 = new GridBagConstraints();
		cLabe13.gridx = 1;
		cLabe13.gridy = 7;
		add(new JLabel("               "),cLabe13);

		//To make the sendEmail and cancel button look nice
		GridBagConstraints cLabe14 = new GridBagConstraints();
		cLabe14.gridx = 1;
		cLabe14.gridy = 10;
		add(new JLabel("                              "),cLabe14);


		//				GridBagConstraints cLabe14 = new GridBagConstraints();
		//				cLabe14.gridx = 1;
		//				cLabe14.gridy = 7;
		//				add(new JLabel("               "),cLabe14);


	}


	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		//background Image
		g2d.drawImage(backgroundImg, 0, 0, this);
		
        //Header
		Font font = new Font("Tahoma", Font.BOLD, 24);
		g2d.setFont(font);
		g2d.setColor(Color.WHITE);
		g2d.drawString("Email", 580, 300);						
	}
	
	public void setBackgroundImg(Image newImg){
		backgroundImg = newImg;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == sendEmailButton){
			if(email!=null){
				sendEmail(email, message);
				MainWindow.removeEmailPanel();
			}
		}
		if (e.getSource() == cancelButton){
			System.out.println("Closing the emailView");
			MainWindow.removeEmailPanel();


		}


	}




	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub


	}


	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub


	}
	public void sendEmail(String mail, String msg){
		MainWindow.getRequestHandler().sendEmail(mail, msg);
	}


	//GETTERS to get info from the name, email and message
	public String getName(){
		return name;
	}
	public String getEmail(){
		return email;
	}
	public String getMessage(){
		return message;
	}






}