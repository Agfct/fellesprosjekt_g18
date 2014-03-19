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

import javax.net.ssl.SSLEngineResult.Status;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import model.Employee;
import model.Invitation;
import model.InvitationStatus;

// Using this because a ListModel was not advanced enough for the application
// Creates a Panel with buttons and labels for a specific employee object
public class ParticipantsPanel extends JPanel implements ActionListener {
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JButton emailBtn;
	private JComboBox<InvitationStatus> statusField;
	private JButton removeBtn;
	private ParticipantsPanelList participantsPanelList;
	private Invitation invitation;
	
	private ImageIcon removeIcon;
	
	private Employee employee;
	GridBagLayout layout;
	
	//TODO: DENNE ER DET SOM GJ�R AT LOGIN ER TREIG
	public ParticipantsPanel(Employee newEmployee){ 
		
		employee = newEmployee;
		//setting layout
		layout = new GridBagLayout();
		setLayout(layout);
		setOpaque(false);
		removeIcon = new ImageIcon(this.getClass().getResource("/buttonImages/deleteBtnImg.png"));

		//firstNameLabel
		GridBagConstraints cLabel0 = new GridBagConstraints();
		cLabel0.insets = new Insets(0,2,0,0);
		cLabel0.gridx = 0;
		cLabel0.gridy = 0;
		cLabel0.anchor = GridBagConstraints.LINE_START;
		firstNameLabel = new JLabel(); 
		firstNameLabel.setText(employee.getName());
		firstNameLabel.setName("firstNameLabel");
		firstNameLabel.setPreferredSize(new Dimension(80,24));
		//DESIGN for the Label text
		firstNameLabel.setForeground(Color.BLACK);
		firstNameLabel.setFont(new Font(MainWindow.getMFont(),Font.BOLD,12));
		add(firstNameLabel,cLabel0);
		
		//lastNameLabel
		//TODO: REMOVE LAST NAME GIVE NAME MORE SPACE
		GridBagConstraints cLabel1 = new GridBagConstraints();
		cLabel1.insets = new Insets(0,5,0,4);
		cLabel1.gridx = 1;
		cLabel1.gridy = 0;
		lastNameLabel = new JLabel(""); //replace with actual name
		lastNameLabel.setName("lastNameLabel");
		lastNameLabel.setPreferredSize(new Dimension(80,20));
		//DESIGN for the Label text
		lastNameLabel.setForeground(Color.BLACK);
		lastNameLabel.setFont(new Font(MainWindow.getMFont(),Font.BOLD,12));
		add(lastNameLabel,cLabel1);
		
		//emailBtn
		GridBagConstraints cLabel2 = new GridBagConstraints();
//		cLabel2.insets = new Insets(10,20,0,0);
		cLabel2.gridx = 2;
		cLabel2.gridy = 0;
		emailBtn = new JButton("eMail");
		emailBtn.setName("emailBtn");
		emailBtn.addActionListener(this);
		emailBtn.setPreferredSize(new Dimension(65,20));
		emailBtn.setFocusPainted(false);
		// DESIGN FOR Field:
		emailBtn.setBackground(MainWindow.getBckColor());
		emailBtn.setForeground(MainWindow.getTxtColor());
		emailBtn.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 12));
		add(emailBtn, cLabel2);
		
		// statusField
		GridBagConstraints cLabel3 = new GridBagConstraints();
		cLabel3.insets = new Insets(0,6,0,6);
		cLabel3.gridx = 3;
		cLabel3.gridy = 0;
		//TEST ADDING THE TIMES TO THE COMBO BOX
		DefaultComboBoxModel<InvitationStatus> statusFieldModel = new DefaultComboBoxModel<InvitationStatus>();
		statusField = new JComboBox<InvitationStatus>(statusFieldModel);
		statusField.setPreferredSize(new Dimension(86,24));
		statusField.setEditable(false);
		//adding to the dropdownBox
		statusFieldModel.addElement(InvitationStatus.PENDING);
		statusFieldModel.addElement(InvitationStatus.DECLINED);
		statusFieldModel.addElement(InvitationStatus.ACCEPTED);
//		statusFieldModel.addElement("<html><font color=\"BLACK\">Pending</font></html>");	
//		statusFieldModel.addElement("<html><font color=\"GREEN\">Accepted</font></html>");			
//		statusFieldModel.addElement("<html><font color=\"RED\">Declined</font></html>");		
		
		statusField.setName("statusField");
		statusField.addActionListener(this);
		// DESIGN FOR ComboBox:
//		statusField.setBackground(MainWindow.getBckColor());
//		statusField.setForeground(MainWindow.getTxtColor());
		statusField.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 12));
		add(statusField, cLabel3);
		
		//removeBtn
		GridBagConstraints cLabel4 = new GridBagConstraints();
		cLabel4.insets = new Insets(0,0,0,3);
		cLabel4.gridx = 4;
		cLabel4.gridy = 0;
		cLabel4.anchor = GridBagConstraints.LINE_END;
		removeBtn = new JButton();
		removeBtn.setIcon(removeIcon);
		removeBtn.setName("removeBtn");
		removeBtn.addActionListener(this);
		removeBtn.setPreferredSize(new Dimension(30,20));
		// DESIGN FOR Field:
		removeBtn.setBackground(Color.RED);
		removeBtn.setForeground(MainWindow.getTxtColor());
		removeBtn.setFocusPainted(false);
		removeBtn.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 12));
		add(removeBtn,cLabel4);

	}
	
	public Employee getEmployee(){
		return employee;
	}
	public String getFirstName() {
		return firstNameLabel.getText();
	}


	public void setFirstName(String firstNameLabel) {
		this.firstNameLabel.setText(firstNameLabel);
	}


	public String getLastName() {
		return lastNameLabel.getText();
	}


	public void setLastName(String lastNameLabel) {
		this.lastNameLabel.setText(lastNameLabel);
	}

	public void setParticipantsPanelList(ParticipantsPanelList newParticipantsListPanel){
		//Choosing what list this view is in
		participantsPanelList = newParticipantsListPanel;
		
	}
	public void setInvitation(Invitation invite){
		invitation = invite;
	}
	
	public String toString(){
		return "employee: "+ employee;
	}
	
	// Action Listener for Buttons
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("("+this.getClass()+"):"+ "Pressing a button Or Modyfing a comboBox");
		
		// If cancelAppointmentBtn is pressed
		if (e.getSource() == removeBtn){
			System.out.println("remove button is pressed");
			//Removing the check
			employee.setSelected(! employee.isSelected());
			participantsPanelList.getNewAppointmentView().getEmployeeList().repaint();
			// running the appointment remove invitation
			participantsPanelList.getNewAppointmentView().getAppointmentModel().removeInvitation(employee);
			
		}	
		else if (e.getSource() == emailBtn){
			System.out.println("Pressing eMail Btn");
			MainWindow.newEmailPanel(invitation);
		}
		else if(e.getSource() == statusField){
			invitation.setStatus((InvitationStatus)statusField.getSelectedItem());
		}
	}
	public void removeThisView(){
		participantsPanelList.removeParticipantPanel(this);
	}
	
	public void changeStatusField(InvitationStatus invStatus){
		statusField.setSelectedItem(invStatus);
	}

}
