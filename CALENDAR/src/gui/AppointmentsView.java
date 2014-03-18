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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Appointment;
import model.Employee;
import model.Invitation;
import model.InvitationStatus;
import model.MeetingRoom;
import model.TimeSlot;
/** Contains all your invitations as appointments
 *  
 * @author Anders
 */
public class AppointmentsView extends JPanel implements ListSelectionListener , FocusListener, ItemListener, ActionListener{
	private JLabel filtersLabel;
	private JLabel acceptedLabel;
	private JLabel showHiddenLabel;
	private JLabel pendingLabel;
	private JLabel descriptionLabel;
	private JLabel acceptLabel;
	private JLabel declineLabel;
	private JLabel alarmLabel;
	private JLabel hiddenLabel;

	private JTextArea descriptionField;
	private JScrollPane descriptionScrollPane;
	
	private JCheckBox acceptedBox;
	private JCheckBox showHiddenBox;
	private JCheckBox pendingBox;
	private JCheckBox hideBox; // TEST
	
	private JComboBox<String> alarmBox; // TEST NEED TO FIX PROPPER ALARM
	
	private JRadioButton acceptRadioBtn;
	private JRadioButton declineRadioBtn;
	
	private ButtonGroup statusBtnGroup = new ButtonGroup();
	
	private JScrollPane appointmentsScrollPane;
	private JList<Appointment> appointmentsList;
	private DefaultListModel<Appointment> appointmentsListModel = new DefaultListModel<Appointment>();
	
	private JButton saveBtn;
	private JButton closeBtn;
	
	private Image backgroundImg;
	
	private AppointmentCellRenderer appointmentRenderer;
	
	private Invitation invitationModel;
	
	public AppointmentsView (){
		
		//Setting Layout (GridBagLayout)
		setLayout(new GridBagLayout());
//		setOpaque(false);
//		backgroundImg = new ImageIcon(this.getClass().getResource("/backgrounds/background1.png")).getImage();
		backgroundImg = MainWindow.getBackgroundImage();
		
		/** CREATING BUTTONS, LABELS AND TEXT FIELDS **/
		
		// appointmentsList
		appointmentRenderer = new AppointmentCellRenderer();
		
		appointmentsList = new JList<Appointment>(appointmentsListModel);
		
		appointmentsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		appointmentsList.setVisibleRowCount();
		appointmentsList.setName("appointmentsList");
		appointmentsList.addListSelectionListener(this);
		appointmentsList.setEnabled(true); //Disabled by default
		appointmentsList.setCellRenderer(appointmentRenderer); // Lage en renderer
		
		appointmentsScrollPane = new JScrollPane(appointmentsList);
		appointmentsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//		appointmentsScrollPane.setEnabled(false); //Disabled by default
		appointmentsScrollPane.setPreferredSize(new Dimension(80,300));
		
		GridBagConstraints cLabel0 = new GridBagConstraints();
		cLabel0.fill = GridBagConstraints.HORIZONTAL;
//		cLabel0.insets = new Insets(0,0,0,0);
//		cLabel0.anchor = GridBagConstraints.LINE_START;
		cLabel0.ipadx = 100;
//		cLabel0.ipady = 70;
//		cLabel0.weighty = 0.1;
//		cLabel0.weightx = 0.1;
		cLabel0.gridheight = 10;
		cLabel0.gridwidth = 11;
		cLabel0.gridx = 0;
		cLabel0.gridy = 0;
	
//		this.add(participantsScrollPane, BorderLayout.CENTER);
		add(appointmentsScrollPane, cLabel0);
		
		// filtersLabel
		GridBagConstraints cLabel1 = new GridBagConstraints();
//		cLabel0.insets = new Insets(0,50,0,0);
		cLabel1.gridx = 0;
		cLabel1.gridy = 11;
		cLabel1.anchor = GridBagConstraints.LINE_START;
		filtersLabel = new JLabel("Filters:");
		filtersLabel.setName("filtersLabel");
		//DESIGN for the Label text
//		filtersLabel.setForeground(MainWindow.getTxtColor());
		filtersLabel.setForeground(Color.BLACK);
		filtersLabel.setFont(new Font(MainWindow.getMFont(),Font.BOLD,16));
		add(filtersLabel,cLabel1);
		
		// acceptedLabel
		GridBagConstraints cLabel2 = new GridBagConstraints();
		cLabel2.insets = new Insets(0,10,0,0);
		cLabel2.gridx = 2;
		cLabel2.gridy = 12;
//		cLabel2.anchor = GridBagConstraints.LINE_START;
		acceptedLabel = new JLabel("Accepted");
		acceptedLabel.setName("acceptedLabel");
		//DESIGN for the Label text
//		acceptedLabel.setForeground(MainWindow.getTxtColor());
		acceptedLabel.setForeground(Color.BLACK);
		acceptedLabel.setFont(new Font(MainWindow.getMFont(),Font.BOLD,14));
		add(acceptedLabel,cLabel2);
		
		// acceptedBox
		GridBagConstraints cLabel3 = new GridBagConstraints();
//		cLabel0.insets = new Insets(0,50,0,0);
		cLabel3.gridx = 3;
		cLabel3.gridy = 12;
//		cLabel3.anchor = GridBagConstraints.LINE_START;
		acceptedBox = new JCheckBox();
		acceptedBox.setName("acceptedBox");
		acceptedBox.setOpaque(false);
		//DESIGN for the Label text
		acceptedBox.setForeground(MainWindow.getTxtColor());
		acceptedBox.setFont(new Font(MainWindow.getMFont(),Font.BOLD,14));
		add(acceptedBox,cLabel3);
		
		// showHiddenLabel
		GridBagConstraints cLabel4 = new GridBagConstraints();
//		cLabel0.insets = new Insets(0,50,0,0);
		cLabel4.gridx = 0;
		cLabel4.gridy = 12;
		cLabel4.anchor = GridBagConstraints.LINE_START;
		showHiddenLabel = new JLabel("ShowHidden");
		showHiddenLabel.setName("showHiddenLabel");
		//DESIGN for the Label text
//		showHiddenLabel.setForeground(MainWindow.getTxtColor());
		showHiddenLabel.setForeground(Color.BLACK);
		showHiddenLabel.setFont(new Font(MainWindow.getMFont(),Font.BOLD,14));
		add(showHiddenLabel,cLabel4);
		
		// showHiddenBox
		GridBagConstraints cLabel5 = new GridBagConstraints();
//		cLabel0.insets = new Insets(0,50,0,0);
		cLabel5.gridx = 1;
		cLabel5.gridy = 12;
//		cLabel3.anchor = GridBagConstraints.LINE_START;
		showHiddenBox = new JCheckBox();
		showHiddenBox.setName("showHiddenBox");
		showHiddenBox.setOpaque(false);
		//DESIGN for the Label text
		showHiddenBox.setForeground(MainWindow.getTxtColor());
		showHiddenBox.setFont(new Font(MainWindow.getMFont(),Font.BOLD,14));
		add(showHiddenBox,cLabel5);
		
		// pendingLabel
		GridBagConstraints cLabel6 = new GridBagConstraints();
		cLabel6.insets = new Insets(0,20,0,0);
		cLabel6.gridx = 4;
		cLabel6.gridy = 12;
//		cLabel6.anchor = GridBagConstraints.LINE_START;
		pendingLabel = new JLabel("Pending");
		pendingLabel.setName("pendingLabel");
		//DESIGN for the Label text
//		pendingLabel.setForeground(MainWindow.getTxtColor());
		pendingLabel.setForeground(Color.BLACK);
		pendingLabel.setFont(new Font(MainWindow.getMFont(),Font.BOLD,14));
		add(pendingLabel,cLabel6);
		
		// pendingBox
		GridBagConstraints cLabel7 = new GridBagConstraints();
//		cLabel0.insets = new Insets(0,50,0,0);
		cLabel7.gridx = 5;
		cLabel7.gridy = 12;
//		cLabel3.anchor = GridBagConstraints.LINE_START;
		pendingBox = new JCheckBox();
		pendingBox.setName("pendingBox");
		pendingBox.setOpaque(false);
		//DESIGN for the Label text
		pendingBox.setForeground(MainWindow.getTxtColor());
		pendingBox.setFont(new Font(MainWindow.getMFont(),Font.BOLD,14));
		add(pendingBox,cLabel7);
		
		// descriptionLabel
		GridBagConstraints cLabel8 = new GridBagConstraints();
		cLabel8.insets = new Insets(0,50,0,0);
		cLabel8.gridx = 11;
		cLabel8.gridy = 0;
		cLabel8.anchor = GridBagConstraints.LINE_START;
		descriptionLabel = new JLabel("Description:");
		descriptionLabel.setName("descriptionLabel");
		//DESIGN for the Label text
		descriptionLabel.setForeground(MainWindow.getTxtColor());
		descriptionLabel.setFont(new Font(MainWindow.getMFont(),Font.BOLD,18));
		add(descriptionLabel,cLabel8);
		
		// descriptionField
		GridBagConstraints cLabel9 = new GridBagConstraints();
		cLabel9.insets = new Insets(0,50,0,0);
		cLabel9.gridx = 11;
		cLabel9.gridy = 1;
		cLabel9.gridwidth = 4;
		cLabel9.gridheight = 4;
//		cLabel9.ipady = 70;
		descriptionField = new JTextArea(5,35);
		descriptionField.setPreferredSize(new Dimension(5,35));
		descriptionField.setLineWrap(true);
		cLabel9.fill = GridBagConstraints.HORIZONTAL;
//		cLabel9.anchor = GridBagConstraints.LINE_START;
		descriptionField.setName("descriptionField");
		descriptionField.addFocusListener(this);
		descriptionField.setEditable(false);
		// DESIGN FOR Field:
//		descriptionField.setBackground(MainWindow.getBckColor());
//		descriptionField.setForeground(MainWindow.getTxtColor());
		descriptionField.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 12));
		descriptionScrollPane = new JScrollPane(descriptionField); //NOT WORKING
		add(descriptionField,cLabel9);
		
		//between appointmentsList and descriptionLabel
		GridBagConstraints cLabel10 = new GridBagConstraints();
		cLabel10.gridx = 10;
		cLabel10.gridy = 0;
		add(new JLabel("                 "),cLabel10);
		
		//between descriptionField and acceptLabel
		GridBagConstraints cLabel11 = new GridBagConstraints();
		cLabel11.gridx = 7;
		cLabel11.gridy = 6;
		add(new JLabel("                 "),cLabel11);
		
		// acceptLabel
		GridBagConstraints cLabel12 = new GridBagConstraints();
		cLabel12.insets = new Insets(0,50,0,0);
		cLabel12.gridx = 11;
		cLabel12.gridy = 7;
		cLabel12.anchor = GridBagConstraints.LINE_START;
		acceptLabel = new JLabel("Accept");
		acceptLabel.setName("acceptLabel");
		//DESIGN for the Label text
		acceptLabel.setForeground(MainWindow.getTxtColor());
		acceptLabel.setFont(new Font(MainWindow.getMFont(),Font.BOLD,14));
		add(acceptLabel,cLabel12);
		
		// declineLabel
		GridBagConstraints cLabel13 = new GridBagConstraints();
		cLabel13.insets = new Insets(0,-40,0,0);
		cLabel13.gridx =12;
		cLabel13.gridy = 7;
		cLabel13.anchor = GridBagConstraints.LINE_START;
		declineLabel = new JLabel("Decline");
		declineLabel.setName("declineLabel");
		//DESIGN for the Label text
		declineLabel.setForeground(MainWindow.getTxtColor());
		declineLabel.setFont(new Font(MainWindow.getMFont(),Font.BOLD,14));
		add(declineLabel,cLabel13);
		
		// alarmLabel
		GridBagConstraints cLabel14 = new GridBagConstraints();
		cLabel14.insets = new Insets(0,50,0,-20);
		cLabel14.gridx = 13;
		cLabel14.gridy = 7;
		cLabel14.anchor = GridBagConstraints.LINE_END;
		alarmLabel = new JLabel("Alarm");
		alarmLabel.setName("alarmLabel");
		//DESIGN for the Label text
		alarmLabel.setForeground(MainWindow.getTxtColor());
		alarmLabel.setFont(new Font(MainWindow.getMFont(),Font.BOLD,14));
		add(alarmLabel,cLabel14);
		
		// hiddenLabel
		GridBagConstraints cLabel15 = new GridBagConstraints();
//		cLabel15.insets = new Insets(0,0,0,0);
		cLabel15.gridx = 14;
		cLabel15.gridy = 7;
//		cLabel15.anchor = GridBagConstraints.LINE_START;
		hiddenLabel = new JLabel("Hide");
		hiddenLabel.setName("hiddenLabel");
		//DESIGN for the Label text
		hiddenLabel.setForeground(MainWindow.getTxtColor());
		hiddenLabel.setFont(new Font(MainWindow.getMFont(),Font.BOLD,14));
		add(hiddenLabel,cLabel15);
		
		// acceptRadioBtn
		GridBagConstraints cLabel16 = new GridBagConstraints();
		cLabel16.insets = new Insets(0,65,0,0);
		cLabel16.gridx = 11;
		cLabel16.gridy = 8;
		cLabel16.anchor = GridBagConstraints.LINE_START;
		acceptRadioBtn = new JRadioButton();
		acceptRadioBtn.setOpaque(false);
		acceptRadioBtn.setName("acceptRadioBtn");
		acceptRadioBtn.setEnabled(false);
		acceptRadioBtn.addItemListener(this);
		statusBtnGroup.add(acceptRadioBtn);
		// DESIGN FOR CheckBox:
		acceptRadioBtn.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 12));
		add(acceptRadioBtn,cLabel16);
		
		// declineRadioBtn
		GridBagConstraints cLabel17 = new GridBagConstraints();
		cLabel17.insets = new Insets(0,-25,0,0);
		cLabel17.gridx = 12;
		cLabel17.gridy = 8;
		cLabel17.anchor = GridBagConstraints.LINE_START;
		declineRadioBtn = new JRadioButton();
		declineRadioBtn.setOpaque(false);
		declineRadioBtn.setName("declineRadioBtn");
		declineRadioBtn.setEnabled(false);
		declineRadioBtn.addItemListener(this);
		statusBtnGroup.add(declineRadioBtn);
		// DESIGN FOR CheckBox:
		declineRadioBtn.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 12));
		add(declineRadioBtn,cLabel17);
		
		// alarmBox
		GridBagConstraints cLabel18 = new GridBagConstraints();
		cLabel18.insets = new Insets(0,0,0,-70);
		cLabel18.gridx = 13;
		cLabel18.gridy = 8;
//		cLabel5.gridwidth = 2;
		//TEST ADDING THE TIMES TO THE COMBO BOX
		DefaultComboBoxModel<String> alarmFieldModel = new DefaultComboBoxModel<String>();
		alarmFieldModel.addElement("None");
		alarmFieldModel.addElement("On Time");
		alarmFieldModel.addElement("1 min before");	
		alarmFieldModel.addElement("2 min before");	
		alarmFieldModel.addElement("5 min before");	
		
		alarmBox = new JComboBox<String>(alarmFieldModel);
		alarmBox.setEditable(false);
//		alarmBox.setEnabled(false);
//		cLabel17.fill = GridBagConstraints.HORIZONTAL;
		alarmBox.setName("alarmBox");
//		alarmBox.addActionListener(this);
//		alarmBox.add(MainWindow.getTimeArray());
		
		// DESIGN FOR ComboBox:
//		alarmBox.setBackground(MainWindow.getBckColor());
//		alarmBox.setForeground(MainWindow.getTxtColor());
		alarmBox.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 12));
		add(alarmBox,cLabel18);
		
		// hideBox
		GridBagConstraints cLabel19 = new GridBagConstraints();
		cLabel19.insets = new Insets(0,0,0,0);
		cLabel19.gridx = 14;
		cLabel19.gridy = 8;
//		cLabel3.anchor = GridBagConstraints.LINE_START;
		hideBox = new JCheckBox();
		hideBox.setName("hideBox");
		hideBox.setOpaque(false);
		hideBox.setEnabled(false);
		//DESIGN for the Label text
		hideBox.setForeground(MainWindow.getTxtColor());
		hideBox.setFont(new Font(MainWindow.getMFont(),Font.BOLD,16));
		add(hideBox,cLabel19);
		
		
		//between appointmentsList and filtersLabel
		GridBagConstraints cLabel20 = new GridBagConstraints();
		cLabel20.gridx = 0;
		cLabel20.gridy = 10;
		add(new JLabel("                 "),cLabel20);
		
		//saveBtn
		GridBagConstraints cLabel21 = new GridBagConstraints();
//		cLabel21.insets = new Insets(0,0,0,50);
		cLabel21.gridx = 11;
		cLabel21.gridy = 9;
//		cLabel15.anchor = GridBagConstraints.LINE_END;
		saveBtn = new JButton("Save");
		saveBtn.setName("saveBtn");
		saveBtn.addActionListener(this);
		// DESIGN FOR Field:
		saveBtn.setBackground(MainWindow.getBckColor());
		saveBtn.setForeground(MainWindow.getTxtColor());
		saveBtn.setFocusPainted(false);
		saveBtn.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 12));
//		loginBtn.setPreferredSize(new Dimension(200, 400));
		add(saveBtn,cLabel21);
		
		//closeBtn
		GridBagConstraints cLabel22 = new GridBagConstraints();
//		cLabel22.insets = new Insets(0,0,0,50);
		cLabel22.gridx = 14;
		cLabel22.gridy = 12;
		cLabel22.anchor = GridBagConstraints.LINE_END;
		closeBtn = new JButton("Close");
		closeBtn.setName("closeBtn");
		closeBtn.addActionListener(this);
		// DESIGN FOR Field:
		closeBtn.setBackground(MainWindow.getBckColor());
		closeBtn.setForeground(MainWindow.getTxtColor());
		closeBtn.setFocusPainted(false);
		closeBtn.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 14));
//		loginBtn.setPreferredSize(new Dimension(200, 400));
		add(closeBtn,cLabel22);
		
		//between Filters and Save Btn
//		GridBagConstraints cLabel23 = new GridBagConstraints();
//		cLabel23.gridx = 9;
//		cLabel23.gridy = 10;
//		add(new JLabel("                       "),cLabel23);
//		 
//		//between Filters and Save Btn
//		GridBagConstraints cLabel24= new GridBagConstraints();
//		cLabel24.gridx = 10;
//		cLabel24.gridy = 10;
//		add(new JLabel("                       "),cLabel24);
		
		/** ADDING APPOINTMENTS **/
		addAppointments();
		
		
		
	}
	// Overriding the paintComponent to get background and Header
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        //background Image
        g2d.drawImage(backgroundImg, 0, 0, this);
        
        //TEST SQUARE
//        g2d.setColor(Color.RED);
//        g2d.fillRect(530, 270, 400, 300);
        
        //Header
		Font font = new Font("Tahoma", Font.BOLD, 24);
		g2d.setFont(font);
		g2d.setColor(Color.WHITE);
		g2d.drawString("Appointments", 450, 150);						
    }
    
    public void addAppointments(){
    	for (int i = 0; i < CalendarView.getInvitedAppointments().size(); i++) {
    		appointmentsListModel.addElement(CalendarView.getInvitedAppointments().get(i));
		}
    }
    
    public void setInvitationModel(Invitation invitation){
    	invitationModel = invitation;
    }
    public void loadInvitationValues(){
    	acceptRadioBtn.setEnabled(true);
    	declineRadioBtn.setEnabled(true);
    	hideBox.setEnabled(true);
    	if(invitationModel.getStatus() == InvitationStatus.ACCEPTED){
    		acceptRadioBtn.setSelected(true);
    	}else if (invitationModel.getStatus() == InvitationStatus.DECLINED){
    		declineRadioBtn.setSelected(true);
    	}
    	if(invitationModel.isHidden()){
    		hideBox.setSelected(true);
    	}else{
    		hideBox.setSelected(false);
    	}
    	
    }
	//ListSelection Listener for the List of Appointments
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO FIXE SLIK AT SERVER FÅR VITE AT APPOINTMENT STATUS FORANDRER SEG ?
		// TODO ADD HIDDEN VARIABLE SOMWHERE TO INDICATE PREFRENCE 
		System.out.println("du tryker på en appointment");
		System.out.println("fra model: "+ appointmentsList.getSelectedValue().getDescription());
		
		statusBtnGroup.clearSelection();
		//Setting the invitation Model
		setInvitationModel(appointmentsList.getSelectedValue().getInvitation(MainWindow.getUser()));
		loadInvitationValues();
		// Setting description field
		descriptionField.setText(appointmentsList.getSelectedValue().getDescription());
		
	}
	
	//Focus Listener for Description Field
	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	//Item Listener for Radio Buttons
	@Override
	public void itemStateChanged(ItemEvent e) {
		System.out.println("("+this.getClass()+"):"+ "Pressing a Radio Button");
		if (e.getSource() == acceptRadioBtn){
			// ONLY FOR REALTIME CHANGES DISCARD IF USING SAVE
//			invitationModel.setStatus(InvitationStatus.ACCEPTED);
//			appointmentsList.repaint();
		}
		else if(e.getSource() == declineRadioBtn){
			// ONLY FOR REALTIME CHANGES DISCARD IF USING SAVE
//			invitationModel.setStatus(InvitationStatus.DECLINED);
//			appointmentsList.repaint();
		}
		
	}
	
	// Action Listener for Buttons (OkBtn and CloseBtn)
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("("+this.getClass()+"):"+ "Pressing a button Or Modyfing a comboBox");
		
		// If cancelAppointmentBtn is pressed
		if (e.getSource() == saveBtn){
			System.out.println("Pressed Save, Changed appointment Info");
			if(acceptRadioBtn.isSelected()){
				invitationModel.setStatus(InvitationStatus.ACCEPTED);
			}else if(declineRadioBtn.isSelected()){
				invitationModel.setStatus(InvitationStatus.DECLINED);
			}
			// Hide
			if(hideBox.isSelected()){
				invitationModel.setHidden(true);
			}else if (!hideBox.isSelected()){
				invitationModel.setHidden(false);
			}
			appointmentsList.repaint();
			//TODO: SEND REQUEST TO SERVER ??
		}
		// If cancelAppointmentBtn is pressed
		else if (e.getSource() == closeBtn){
			System.out.println("Closing AppointmentsView");
			MainWindow.removeAppointmentsView();
		}
		
	}
}
