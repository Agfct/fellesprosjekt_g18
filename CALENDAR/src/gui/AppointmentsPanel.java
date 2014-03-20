package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.net.ssl.SSLEngineResult.Status;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Appointment;
import model.Employee;
import model.Invitation;
import model.InvitationStatus;

// Using this because a ListModel was not advanced enough for the application
// Creates a Panel with buttons and labels for a specific employee object
public class AppointmentsPanel extends JPanel implements ActionListener {
	private JLabel startEndTime;
	private JLabel title;
	private JLabel date;
	private JLabel room;
	
	private JButton editBtn;
	private JButton removeBtn;
	
	private AppointmentsPanelList appointmentsPanelList;
	
	private Invitation invitation;
	
	private ImageIcon removeIcon;
	
	private Employee employee;
	private Appointment appointment;
	GridBagLayout layout;
	
	public AppointmentsPanel(Appointment newAppointment){ 
		
		appointment = newAppointment;
		//setting layout
		layout = new GridBagLayout();
		setLayout(layout);
		setOpaque(false);
		removeIcon = new ImageIcon(this.getClass().getResource("/buttonImages/deleteBtnImgL.png"));

		//startEndTime
		GridBagConstraints cLabel0 = new GridBagConstraints();
		cLabel0.insets = new Insets(5,0,0,0);
		cLabel0.gridx = 0;
		cLabel0.gridy = 0;
		cLabel0.anchor = GridBagConstraints.LINE_START;
		startEndTime = new JLabel();
		String time = appointment.getStartTime()+"-"+appointment.getEndTime();
//		String time = "01:00"+"-"+"12:30";
		startEndTime.setText(time);
		startEndTime.setName("startEndTime");
		startEndTime.setPreferredSize(new Dimension(80,24));
		//DESIGN for the Label text
		startEndTime.setForeground(Color.BLACK);
		startEndTime.setFont(new Font(MainWindow.getMFont(),Font.BOLD,12));
		add(startEndTime,cLabel0);
		
		//title
		GridBagConstraints cLabel1 = new GridBagConstraints();
		cLabel1.insets = new Insets(5,5,0,0);			
		cLabel1.gridx = 2;
		cLabel1.gridy = 0;
//		cLabel1.anchor = GridBagConstraints.CENTER;
		title = new JLabel(appointment.getTitle());
		title.setName("title");
		if(appointment.getTitle().length() < 20){
			title.setPreferredSize(new Dimension(80,20));
			cLabel1.insets = new Insets(5,63,0,42);
		}
		else{
//			cLabel1.insets = new Insets(5,5,0,0);
			title.setPreferredSize(new Dimension(180,20));
		}
		//DESIGN for the Label text
		title.setForeground(Color.BLACK);
		title.setFont(new Font(MainWindow.getMFont(),Font.BOLD,12));
		add(title,cLabel1);
		
		//date
		GridBagConstraints cLabel2 = new GridBagConstraints();
//		cLabel2.insets = new Insets(-30,0,0,0);
		cLabel2.gridx = 0;
		cLabel2.gridy = 1;
		date = new JLabel(appointment.getDateString());
		date.setName("date");
		date.setPreferredSize(new Dimension(80,20));
		//DESIGN for the Label text
		date.setForeground(Color.BLACK);
		date.setFont(new Font(MainWindow.getMFont(),Font.BOLD,12));
		add(date,cLabel2);
		
		//room
		GridBagConstraints cLabel3 = new GridBagConstraints();
//		cLabel3.insets =  new Insets(-30,0,0,0);
		cLabel3.gridx = 2;
		cLabel3.gridy = 1;
//		room = new JLabel(appointment.getRoom().toString());
		room = new JLabel("Room nr 5");
		room.setName("room");
		room.setPreferredSize(new Dimension(80,20));
		//DESIGN for the Label text
		room.setForeground(Color.BLACK);
		room.setFont(new Font(MainWindow.getMFont(),Font.BOLD,12));
		add(room,cLabel3);
		
		//editBtn
		GridBagConstraints cLabel4 = new GridBagConstraints();
		cLabel4.insets = new Insets(0,0,-30,0);
		cLabel4.gridx = 4;
		cLabel4.gridy = 0;
		editBtn = new JButton("Edit");
		editBtn.setName("editBtn");
		editBtn.addActionListener(this);
		editBtn.setPreferredSize(new Dimension(65,30));
		editBtn.setFocusPainted(false);
		// DESIGN FOR Field:
		editBtn.setBackground(MainWindow.getBckColor());
		editBtn.setForeground(MainWindow.getTxtColor());
		editBtn.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 14));
		add(editBtn, cLabel4);
		
		//removeBtn
		GridBagConstraints cLabel5 = new GridBagConstraints();
		cLabel5.insets = new Insets(0,10,-30,0);
		cLabel5.gridx = 6;
		cLabel5.gridy = 0;
		cLabel5.anchor = GridBagConstraints.LINE_END;
		removeBtn = new JButton();
		removeBtn.setIcon(removeIcon);
		removeBtn.setName("removeBtn");
		removeBtn.addActionListener(this);
		removeBtn.setPreferredSize(new Dimension(30,30));
		// DESIGN FOR Field:
		removeBtn.setBackground(Color.RED);
		removeBtn.setForeground(MainWindow.getTxtColor());
		removeBtn.setFocusPainted(false);
		removeBtn.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 14));
		add(removeBtn,cLabel5);

	}
	
	public Employee getEmployee(){
		return employee;
	}
	public Appointment getAppointment(){
		return appointment;
	}
	public String getStartEndTime() {
		return startEndTime.getText();
	}


	public void setStarEndTime(String startEndTimeLabel) {
		this.startEndTime.setText(startEndTimeLabel);
	}


	public String getTitle() {
		return title.getText();
	}


	public void setTitle(String titleLabel) {
		this.title.setText(titleLabel);
	}

	public void setAppointmentPanelList(AppointmentsPanelList newAppointmentsPanelList){
		//Choosing what list this Panel is in
		appointmentsPanelList = newAppointmentsPanelList;
		
	}
	public void setInvitation(Invitation invite){
		invitation = invite;
	}
	
	public String toString(){
		return "Appointment: "+ appointment;
	}
	
	// Action Listener for Buttons
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("("+this.getClass()+"):"+ "Pressing a button Or Modyfing a comboBox");
		
		// If removeBtn is pressed
		if (e.getSource() == removeBtn){
			if(appointment.getInvitations().isEmpty()){
				if(MainWindow.getRequestHandler().removeAppointmentByID(appointment)){
					appointmentsPanelList.updateView();
				}else{
					JOptionPane.showMessageDialog(MainWindow.getMainWindow(),"Could not delete Appointment. Cloud not Available");
				}
			}else{
				appointment.setDeleted(true);
				if(MainWindow.getRequestHandler().editAppointment(appointment)){
					appointmentsPanelList.updateView();
				}else{
					JOptionPane.showMessageDialog(MainWindow.getMainWindow(),"Could not delete Appointment. Cloud not Available");
					appointment.setDeleted(false);
				}
			}
		}	
		else if (e.getSource() == editBtn){
			System.out.println("Pressing edit Btn");
			// Open newAppointmentView for editing
			MainWindow.newAppointmentView(appointment, false,"editApp");
			MainWindow.removeEditAppointmentsView();
		}
	}
	public void removeThisPanel(){
		appointmentsPanelList.removeAppointmentsPanel(this);
	}
	
}
