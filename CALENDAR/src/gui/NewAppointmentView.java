package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import model.Appointment;
import model.Employee;
import model.Invitation;
import model.InvitationStatus;
import model.MeetingRoom;
import model.RoomBooker;
import model.TimeSlot;

public class NewAppointmentView extends JPanel implements MouseListener, KeyListener, ListSelectionListener, ActionListener , ItemListener, FocusListener, PropertyChangeListener{
	private JLabel titleLabel;
	private JLabel startLabel;
	private JLabel endLabel;
	private JLabel durationLabel;
	private JLabel dateLabel;
	private JLabel searchLabel;
	private JLabel exParticipantsLabel;
	private JLabel locationLabel1;
	private JLabel locationLabel2;
	private JLabel internalLabel;
	private JLabel externalLabel;
	private JLabel nrParticipantsLabel;
	private JLabel roomLabel;
	private JLabel descriptionLabel;
	private JLabel participantsLabel;
	
	private JTextField titleField;
	private JComboBox<String> startField;
	private JComboBox<String> endField;
	private JComboBox<String> acceptedBox;
	private JTextField durationField;
//	private JTextField dateField;
	private JComboBox<Integer> dateDayField;
	private JComboBox<Integer> dateMonthField;	
	private JComboBox<Integer> dateYearField;
	private JTextField searchField;
	private JTextField exParticipantsField;
	private JTextField locationField;
	private JTextField nrParticipantsField;
	
	private JTextArea descriptionField;
	
	private JScrollPane employeeListScrollPane;
	private JScrollPane roomsScrollPane;
	private JScrollPane descriptionScrollPane;
	private JScrollPane participantsScrollPane;
	
	// The Lists
	private JList<Employee> employeeList;
	private JList<MeetingRoom> roomList;
	private DefaultListModel<Employee> employeeListModel; // USE THESE ??
	private DefaultListModel<MeetingRoom> roomListModel = new DefaultListModel<MeetingRoom>(); // USE THESE ??
	private EmployeeCellRenderer employeeRenderer;
	
	private ParticipantsPanelList participantsPanelList;
	
	private JButton addExParticipantsBtn;
	private JButton bookBtn;
	private JButton saveAppointmentBtn;
	private JButton deleteAppointmentBtn;
	private JButton cancelAppointmentBtn;
	
	private JRadioButton internalRadioButton;
	private JRadioButton externalRadioButton;
	
	private ButtonGroup locationBtnGroup = new ButtonGroup();
	
	private Image backgroundImg;
	
	private boolean isNewAppointmentView;
	
	// THE MODEL AND EMPLOYEES
	
	private Appointment appointmentModel;
	private RoomBooker roomBooker;
	private ArrayList<Employee> allEmployees;
	
	private String from;
	
//	private JTable employeeListTable;
//	private JTable participantsListTable;
//	private TableColumn acceptedCol;
//	private EmployeeTable employeeTable;
	




	
	public NewAppointmentView(Appointment newAppointmentModel, boolean isNewAppointmentView, String from){
		// Using a GridBagLayout for the Grid
		setLayout(new GridBagLayout());
//		setOpaque(false);
//		backgroundImg = new ImageIcon(this.getClass().getResource("/backgrounds/background1.png")).getImage();
		backgroundImg = MainWindow.getBackgroundImage();
		setBackground(Color.GREEN);
		this.isNewAppointmentView = isNewAppointmentView;
		
		this.from = from;

		/** ADDING EMPLOYEE LIST **/
		allEmployees = MainWindow.getEmployeeList();
		
		/** CREATING MODEL **/
		employeeListModel = new DefaultListModel<Employee>();
		appointmentModel = newAppointmentModel;
		appointmentModel.addPropertyChangedListener(this);
		
		
		
		/** CREATING BUTTONS, LABELS AND TEXT FIELDS **/
		
		// titleLabel
		GridBagConstraints cLabel0 = new GridBagConstraints();
//		cLabel0.insets = new Insets(0,50,0,0);
		cLabel0.gridx = 0;
		cLabel0.gridy = 0;
		cLabel0.anchor = GridBagConstraints.LINE_START;
		titleLabel = new JLabel("Title");
		titleLabel.setName("titleLabel");
		//DESIGN for the Label text
		titleLabel.setForeground(MainWindow.getTxtColor());
		titleLabel.setFont(new Font(MainWindow.getMFont(),Font.BOLD,18));
		add(titleLabel,cLabel0);
		
		// titleField
		GridBagConstraints cLabel1 = new GridBagConstraints();
		cLabel1.insets = new Insets(0,20,0,50);
		cLabel1.gridx = 1;
		cLabel1.gridy = 0;
		titleField = new JTextField(10);
		cLabel1.fill = GridBagConstraints.HORIZONTAL;
		titleField.setName("titleField");
		titleField.addFocusListener(this);
		// DESIGN FOR Field:
//		titleField.setBackground(MainWindow.getBckColor());
//		titleField.setForeground(MainWindow.getTxtColor());
		titleField.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 12));
//		loginBtn.setPreferredSize(new Dimension(200, 400));
		add(titleField,cLabel1);
		
		// startLabel
		GridBagConstraints cLabel2 = new GridBagConstraints();
//		cLabel0.insets = new Insets(0,50,0,0);
		cLabel2.gridx = 0;
		cLabel2.gridy = 2;
		cLabel2.anchor = GridBagConstraints.LINE_START;
		startLabel = new JLabel("Start Time");
		startLabel.setName("startLabel");
		//DESIGN for the Label text
		startLabel.setForeground(MainWindow.getTxtColor());
		startLabel.setFont(new Font(MainWindow.getMFont(),Font.BOLD,18));
		add(startLabel,cLabel2);
		
		// startField
		GridBagConstraints cLabel3 = new GridBagConstraints();
		cLabel3.insets = new Insets(0,20,0,50);
		cLabel3.gridx = 1;
		cLabel3.gridy = 2;
//		cLabel3.gridwidth = 2;
		//TEST ADDING THE TIMES TO THE COMBO BOX
		DefaultComboBoxModel<String> startFieldModel = new DefaultComboBoxModel<String>();
		startField = new JComboBox<String>(startFieldModel);
		for (int i = 0; i < MainWindow.getTimeArray().size(); i++) {
			startFieldModel.addElement(MainWindow.getTimeArray().get(i));			
		}
		startField.setEditable(true);
		cLabel3.fill = GridBagConstraints.HORIZONTAL;
		startField.setName("startField");
		
//		startField.add(MainWindow.getTimeArray());
		
		// DESIGN FOR ComboBox:
//		startField.setBackground(MainWindow.getBckColor());
//		startField.setForeground(MainWindow.getTxtColor());
		startField.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 12));
		add(startField,cLabel3);
		
		// endLabel
		GridBagConstraints cLabel4 = new GridBagConstraints();
//		cLabel0.insets = new Insets(0,50,0,0);
		cLabel4.gridx = 0;
		cLabel4.gridy = 4;
		cLabel4.anchor = GridBagConstraints.LINE_START;
		endLabel = new JLabel("End Time");
		endLabel.setName("endLabel");
		//DESIGN for the Label text
		endLabel.setForeground(MainWindow.getTxtColor());
		endLabel.setFont(new Font(MainWindow.getMFont(),Font.BOLD,18));
		add(endLabel,cLabel4);
		
		// endField
		GridBagConstraints cLabel5 = new GridBagConstraints();
		cLabel5.insets = new Insets(0,20,0,50);
		cLabel5.gridx = 1;
		cLabel5.gridy = 4;
//		cLabel5.gridwidth = 2;
		//TEST ADDING THE TIMES TO THE COMBO BOX
		DefaultComboBoxModel<String> endFieldModel = new DefaultComboBoxModel<String>();
		endField = new JComboBox<String>(endFieldModel);
		for (int i = 0; i < MainWindow.getTimeArray().size(); i++) {
			endFieldModel.addElement(MainWindow.getTimeArray().get(i));			
		}
		endField.setEditable(true);
		cLabel5.fill = GridBagConstraints.HORIZONTAL;
		endField.setName("endField");
		
//		endField.add(MainWindow.getTimeArray());
		
		// DESIGN FOR ComboBox:
//		endField.setBackground(MainWindow.getBckColor());
//		endField.setForeground(MainWindow.getTxtColor());
		endField.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 12));
		add(endField,cLabel5);
		
		// durationLabel
		GridBagConstraints cLabel6 = new GridBagConstraints();
//		cLabel0.insets = new Insets(0,50,0,0);
		cLabel6.gridx = 0;
		cLabel6.gridy = 6;
		cLabel6.anchor = GridBagConstraints.LINE_START;
		durationLabel = new JLabel("Duration");
		durationLabel.setName("durationLabel");
		//DESIGN for the Label text
		durationLabel.setForeground(MainWindow.getTxtColor());
		durationLabel.setFont(new Font(MainWindow.getMFont(),Font.BOLD,18));
		add(durationLabel,cLabel6);
		
		// durationField
		GridBagConstraints cLabel7 = new GridBagConstraints();
		cLabel7.insets = new Insets(0,20,0,50);
		cLabel7.gridx = 1;
		cLabel7.gridy = 6;
		durationField = new JTextField(10);
		cLabel7.fill = GridBagConstraints.HORIZONTAL;
		durationField.setName("durationField");
		durationField.addFocusListener(this);
		// DESIGN FOR Field:
//		durationField.setBackground(MainWindow.getBckColor());
//		durationField.setForeground(MainWindow.getTxtColor());
		durationField.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 12));
		add(durationField,cLabel7);
		
		// dateLabel
		GridBagConstraints cLabel8 = new GridBagConstraints();
//		cLabel8.insets = new Insets(0,50,0,0);
		cLabel8.gridx = 0;
		cLabel8.gridy = 7;
		cLabel8.anchor = GridBagConstraints.LINE_START;
		dateLabel = new JLabel("Date (dd:mm:yy)");
		dateLabel.setName("dateLabel");
		//DESIGN for the Label text
		dateLabel.setForeground(MainWindow.getTxtColor());
		dateLabel.setFont(new Font(MainWindow.getMFont(),Font.BOLD,18));
		add(dateLabel,cLabel8);
		
		// dateFields (day, month, year)
		//dateDayField
		GridBagConstraints cLabel9_day = new GridBagConstraints();
		cLabel9_day.insets = new Insets(0,20,0,50);
		cLabel9_day.gridx = 1;
		cLabel9_day.gridy = 7;
		cLabel9_day.anchor = GridBagConstraints.LINE_START;
		DefaultComboBoxModel<Integer> dateDayFieldModel = new DefaultComboBoxModel<Integer>();
		
		//TEST: ADD CODE TO FILL BOX WITH NUMBERS
		
		dateDayField = new JComboBox<Integer>(dateDayFieldModel);
//		cLabel9_day.fill = GridBagConstraints.HORIZONTAL;
		dateDayField.setName("dateDayField");
		
		// DESIGN FOR Field:
//		dateField.setBackground(MainWindow.getBckColor());
//		dateField.setForeground(MainWindow.getTxtColor());
		dateDayField.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 12));
		add(dateDayField,cLabel9_day);
		
		//dateMonthField
		GridBagConstraints cLabel9_month = new GridBagConstraints();
		cLabel9_month.insets = new Insets(0,0,0,30);
		cLabel9_month.gridx = 1;
		cLabel9_month.gridy = 7;
		DefaultComboBoxModel<Integer> dateMonthFieldModel = new DefaultComboBoxModel<Integer>();
		
		//TEST: ADD CODE TO FILL BOX WITH NUMBERS
		
		dateMonthField = new JComboBox<Integer>(dateMonthFieldModel);
//		cLabel9_day.fill = GridBagConstraints.HORIZONTAL;
		dateMonthField.setName("dateMonthField");
		
		// DESIGN FOR Field:
//		dateField.setBackground(MainWindow.getBckColor());
//		dateField.setForeground(MainWindow.getTxtColor());
		dateMonthField.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 12));
		add(dateMonthField,cLabel9_month);
		
		//dateYearField
		GridBagConstraints cLabel9_year = new GridBagConstraints();
		cLabel9_year.insets = new Insets(0,67,0,0);
		cLabel9_year.gridx = 1;
		cLabel9_year.gridy = 7;
		DefaultComboBoxModel<Integer> dateYearFieldModel = new DefaultComboBoxModel<Integer>();

		
		//TEST: ADD CODE TO FILL BOX WITH NUMBERS
		
		
		dateYearField = new JComboBox<Integer>(dateYearFieldModel);
//		cLabel9_day.fill = GridBagConstraints.HORIZONTAL;
		dateYearField.setName("dateYearField");
		
		// DESIGN FOR Field:
//		dateField.setBackground(MainWindow.getBckColor());
//		dateField.setForeground(MainWindow.getTxtColor());
		dateYearField.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 12));
		add(dateYearField,cLabel9_year);
		
		fillInDate();
		

		
		// searchLabel
		GridBagConstraints cLabel10 = new GridBagConstraints();
//		cLabel8.insets = new Insets(0,50,0,0);
		cLabel10.gridx = 0;
		cLabel10.gridy = 11;
		cLabel10.anchor = GridBagConstraints.LINE_START;
		searchLabel = new JLabel("Search");
		searchLabel.setName("searchLabel");
		//DESIGN for the Label text
		searchLabel.setForeground(MainWindow.getTxtColor());
		searchLabel.setFont(new Font(MainWindow.getMFont(),Font.BOLD,18));
		add(searchLabel,cLabel10);
		
		// searchField
		GridBagConstraints cLabel11 = new GridBagConstraints();
		cLabel11.insets = new Insets(0,20,0,50);
		cLabel11.gridx = 1;
		cLabel11.gridy = 11;
		searchField = new JTextField(10);
		cLabel11.fill = GridBagConstraints.HORIZONTAL;
		searchField.setName("searchField");
		searchField.addKeyListener(this);
		// DESIGN FOR Field:
//		searchField.setBackground(MainWindow.getBckColor());
//		searchField.setForeground(MainWindow.getTxtColor());
		searchField.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 12));
		add(searchField,cLabel11);
		
		employeeList = new JList<Employee>(employeeListModel);
		employeeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		employeeList.setVisibleRowCount(10);
		employeeList.setName("employeeList");
//		employeeList.addListSelectionListener(this);
		
		employeeList.addMouseListener(this);
		employeeRenderer = new EmployeeCellRenderer();
		employeeList.setCellRenderer(employeeRenderer);
//		internPersonList.setCellRenderer(renderer);
		
		employeeListScrollPane = new JScrollPane(employeeList);
		employeeListScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		employeeListScrollPane.setPreferredSize(new Dimension(40,183));
		
		GridBagConstraints cLabel12 = new GridBagConstraints();
		cLabel12.fill = GridBagConstraints.HORIZONTAL;
		cLabel12.insets = new Insets(0,0,0,50);
		cLabel12.anchor = GridBagConstraints.LINE_START;
//		cLabel12.ipadx = 70;
//		cLabel12.ipady = 70;
//		cLabel12.weighty = 0.1;
//		cLabel12.weightx = 0.4;
		cLabel12.gridheight = 5;
		cLabel12.gridwidth = 3;
		cLabel12.gridx = 0;
		cLabel12.gridy = 13;
//		listScrollPane.setPreferredSize(getPreferredSize());
	
//		this.add(listScrollPane, BorderLayout.CENTER);
		add(employeeListScrollPane, cLabel12);
		
		
		// exParticipantsLabel
		GridBagConstraints cLabel13 = new GridBagConstraints();
//		cLabel8.insets = new Insets(0,50,0,0);
		cLabel13.gridx = 0;
		cLabel13.gridy = 19;
//		cLabel13.gridwidth = 2;
		cLabel13.anchor = GridBagConstraints.LINE_START;
		exParticipantsLabel = new JLabel("External Participants:");
		exParticipantsLabel.setName("exParticipantsLabel");
		//DESIGN for the Label text
//		exParticipantsLabel.setForeground(MainWindow.getTxtColor());
		exParticipantsLabel.setForeground(Color.BLACK);
		exParticipantsLabel.setFont(new Font(MainWindow.getMFont(),Font.BOLD,16));
		add(exParticipantsLabel,cLabel13);
		
		// exParticipantsField
		GridBagConstraints cLabel14 = new GridBagConstraints();
//		cLabel14.insets = new Insets(0,20,0,0);
		cLabel14.gridx = 0;
		cLabel14.gridy = 21;
//		cLabel13.gridwidth = 2;
		exParticipantsField = new JTextField(10);
		cLabel14.fill = GridBagConstraints.HORIZONTAL;
		cLabel14.anchor = GridBagConstraints.LINE_START;
		exParticipantsField.setName("exParticipantsField");
		exParticipantsField.addKeyListener(this);
		// DESIGN FOR Field:
//		exParticipantsField.setBackground(MainWindow.getBckColor());
//		exParticipantsField.setForeground(MainWindow.getTxtColor());
		exParticipantsField.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 12));
		add(exParticipantsField,cLabel14);
		
		//addExParticipantsBtn
		GridBagConstraints cLabel15 = new GridBagConstraints();
		cLabel15.insets = new Insets(0,0,0,50);
		cLabel15.gridx = 1;
		cLabel15.gridy = 21;
//		cLabel15.anchor = GridBagConstraints.LINE_END;
		addExParticipantsBtn = new JButton("Add");
		addExParticipantsBtn.setName("addExParticipantsBtn");
		addExParticipantsBtn.addActionListener(this);
		// DESIGN FOR Field:
		addExParticipantsBtn.setBackground(MainWindow.getBckColor());
		addExParticipantsBtn.setForeground(MainWindow.getTxtColor());
		addExParticipantsBtn.setFocusPainted(false);
		addExParticipantsBtn.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 12));
//		loginBtn.setPreferredSize(new Dimension(200, 400));
		add(addExParticipantsBtn,cLabel15);
		
		// descriptionLabel
		GridBagConstraints cLabel16 = new GridBagConstraints();
//		cLabel8.insets = new Insets(0,50,0,0);
		cLabel16.gridx = 4;
		cLabel16.gridy = 0;
//		cLabel13.gridwidth = 2;
		cLabel16.anchor = GridBagConstraints.LINE_START;
		descriptionLabel = new JLabel("Description:");
		descriptionLabel.setName("descriptionLabel");
		//DESIGN for the Label text
		descriptionLabel.setForeground(MainWindow.getTxtColor());
		descriptionLabel.setFont(new Font(MainWindow.getMFont(),Font.BOLD,18));
		add(descriptionLabel,cLabel16);
		
		// descriptionField
		GridBagConstraints cLabel17 = new GridBagConstraints();
//		cLabel17.insets = new Insets(0,20,0,0);
		cLabel17.gridx = 4;
		cLabel17.gridy = 2;
//		cLabel17.gridwidth = 2;
		cLabel17.gridheight = 5;
//		cLabel17.ipady = 70;
		descriptionField = new JTextArea(5,35);
		descriptionField.setPreferredSize(new Dimension(5,35));
		descriptionField.setLineWrap(true);
		cLabel17.fill = GridBagConstraints.HORIZONTAL;
		cLabel17.anchor = GridBagConstraints.LINE_START;
		descriptionField.setName("descriptionField");
		descriptionField.addFocusListener(this);
		// DESIGN FOR Field:
//		descriptionField.setBackground(MainWindow.getBckColor());
//		descriptionField.setForeground(MainWindow.getTxtColor());
		descriptionField.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 12));
		descriptionScrollPane = new JScrollPane(descriptionField); //NOT WORKING
		add(descriptionField,cLabel17);
		
		// participantsLabel
		GridBagConstraints cLabel18 = new GridBagConstraints();
		cLabel18.insets = new Insets(0,0,0,0);
		cLabel18.gridx = 4;
		cLabel18.gridy = 8;
//		cLabel13.gridwidth = 2;
		cLabel18.anchor = GridBagConstraints.LINE_START;
		participantsLabel = new JLabel("Participants:");
		participantsLabel.setName("participantsLabel");
		//DESIGN for the Label text
		participantsLabel.setForeground(MainWindow.getTxtColor());
		participantsLabel.setFont(new Font(MainWindow.getMFont(),Font.BOLD,18));
		add(participantsLabel,cLabel18);
		
		//participantsPanelList, a ScrollPane with a participantsPanelList in it
		//The participantsPanelList contains several ParticipantsPanel, that eatch contains one employee
		participantsPanelList = new ParticipantsPanelList();
		participantsScrollPane = new JScrollPane(participantsPanelList);
		participantsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		participantsScrollPane.setPreferredSize(new Dimension(40,200));
		participantsPanelList.setScrollPane(participantsScrollPane);
		participantsPanelList.setNewAppointmentView(this);
		
		GridBagConstraints cLabel19 = new GridBagConstraints();
		cLabel19.fill = GridBagConstraints.HORIZONTAL;
		cLabel19.insets = new Insets(5,0,0,0);
//		cLabel19.anchor = GridBagConstraints.LINE_START;
//		cLabel19.ipadx = 70;
//		cLabel19.ipady = 70;
//		cLabel19.weighty = 0.1;
//		cLabel19.weightx = 0.4;
		cLabel19.gridheight = 10;
		cLabel19.gridwidth = 3;
		cLabel19.gridx = 4;
		cLabel19.gridy = 9;
		add(participantsScrollPane, cLabel19);
		
		
		// locationLabel
		GridBagConstraints cLabel20 = new GridBagConstraints();
		cLabel20.insets = new Insets(0,50,0,0);
		cLabel20.gridx = 8;
		cLabel20.gridy = 0;
//		cLabel13.gridwidth = 2;
		cLabel20.anchor = GridBagConstraints.LINE_START;
		locationLabel1 = new JLabel("Location:");
		locationLabel1.setName("locationLabel1");
		//DESIGN for the Label text
		locationLabel1.setForeground(MainWindow.getTxtColor());
		locationLabel1.setFont(new Font(MainWindow.getMFont(),Font.BOLD,18));
		add(locationLabel1,cLabel20);
		
		// internalLabel
		GridBagConstraints cLabel21 = new GridBagConstraints();
		cLabel21.insets = new Insets(0,50,0,0);
		cLabel21.gridx = 8;
		cLabel21.gridy = 2;
//		cLabel13.gridwidth = 2;
		cLabel21.gridwidth = GridBagConstraints.RELATIVE;
		cLabel21.anchor = GridBagConstraints.LINE_START;
		internalLabel = new JLabel("Internal");
		internalLabel.setName("internalLabel");
		//DESIGN for the Label text
		internalLabel.setForeground(MainWindow.getTxtColor());
		internalLabel.setFont(new Font(MainWindow.getMFont(),Font.BOLD,18));
		add(internalLabel,cLabel21);
		
		// internalRadioButton
		GridBagConstraints cLabel22 = new GridBagConstraints();
		cLabel22.insets = new Insets(3,0,0,0);
		cLabel22.gridx = 8;
		cLabel22.gridy = 2;
		cLabel22.gridwidth = GridBagConstraints.REMAINDER;
		internalRadioButton = new JRadioButton();
		internalRadioButton.setOpaque(false);
		internalRadioButton.setName("internalRadioButton");
		internalRadioButton.addItemListener(this);
		locationBtnGroup.add(internalRadioButton);
		// DESIGN FOR CheckBox:
		internalRadioButton.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 12));
		add(internalRadioButton,cLabel22);
		
		//Fill between Inernal and External
		GridBagConstraints cLabel23 = new GridBagConstraints();
		cLabel23.gridx = 9;
		cLabel23.gridy = 2;
		add(new JLabel("                 "),cLabel23);
		
		// externalLabel
		GridBagConstraints cLabel24 = new GridBagConstraints();
//		cLabel23.insets = new Insets(0,50,0,0);
		cLabel24.gridx = 10;
		cLabel24.gridy = 2;
//		cLabel13.gridwidth = 2;
		cLabel24.anchor = GridBagConstraints.LINE_START;
		externalLabel = new JLabel("External");
		externalLabel.setName("externalLabel");
		//DESIGN for the Label text
		externalLabel.setForeground(MainWindow.getTxtColor());
		externalLabel.setFont(new Font(MainWindow.getMFont(),Font.BOLD,18));
		add(externalLabel,cLabel24);
		
		// externalRadioButton
		GridBagConstraints cLabel25 = new GridBagConstraints();
		cLabel25.insets = new Insets(3,80,0,0);
		cLabel25.gridx = 10;
		cLabel25.gridy = 2;
		cLabel25.gridwidth = GridBagConstraints.REMAINDER;
		externalRadioButton = new JRadioButton();
		externalRadioButton.setOpaque(false);
		externalRadioButton.setName("externalRadioButton");
		externalRadioButton.addItemListener(this);
		locationBtnGroup.add(externalRadioButton);
		// DESIGN FOR CheckBox:
		externalRadioButton.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 12));
		add(externalRadioButton,cLabel25);
		
		// locationLabel2
		GridBagConstraints cLabel26 = new GridBagConstraints();
		cLabel26.insets = new Insets(0,50,0,0);
		cLabel26.gridx = 8;
		cLabel26.gridy = 4;
//		cLabel13.gridwidth = 2;
		cLabel26.anchor = GridBagConstraints.LINE_START;
		locationLabel2 = new JLabel("Location");
		locationLabel2.setName("locationLabel2");
		//DESIGN for the Label text
		locationLabel2.setForeground(MainWindow.getTxtColor());
		locationLabel2.setFont(new Font(MainWindow.getMFont(),Font.BOLD,18));
		add(locationLabel2,cLabel26);
		
		// locationField
		GridBagConstraints cLabel27 = new GridBagConstraints();
//		cLabel27.insets = new Insets(0,0,50,0);
		cLabel27.gridx = 9;
		cLabel27.gridy = 4;
		cLabel27.gridwidth =2;
		cLabel27.fill = GridBagConstraints.HORIZONTAL;
		locationField = new JTextField(10);
		locationField.setName("locationField");
		locationField.addFocusListener(this);
		locationField.setEnabled(false); // Disabled by default
		// DESIGN FOR Field:
//		locationField.setBackground(MainWindow.getBckColor());
//		locationField.setForeground(MainWindow.getTxtColor());
		locationField.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 12));
		add(locationField,cLabel27);
		
		// nrParticipantsLabel
		GridBagConstraints cLabel28 = new GridBagConstraints();
		cLabel28.insets = new Insets(0,50,0,0);
		cLabel28.gridx = 8;
		cLabel28.gridy = 6;
		cLabel28.gridwidth = 2;
		cLabel28.anchor = GridBagConstraints.LINE_START;
		nrParticipantsLabel = new JLabel("Nr of Participants");
		nrParticipantsLabel.setName("nrParticipantsLabel");
		//DESIGN for the Label text
		nrParticipantsLabel.setForeground(MainWindow.getTxtColor());
		nrParticipantsLabel.setFont(new Font(MainWindow.getMFont(),Font.BOLD,14));
		add(nrParticipantsLabel,cLabel28);
		
		// nrParticipantsField
		GridBagConstraints cLabel29 = new GridBagConstraints();
		cLabel29.insets = new Insets(0,50,0,0);
		cLabel29.gridx = 9;
		cLabel29.gridy = 6;
//		cLabel29.gridwidth =2;
		cLabel29.fill = GridBagConstraints.HORIZONTAL;
		nrParticipantsField = new JTextField(2);
		nrParticipantsField.setName("nrParticipantsField");
		nrParticipantsField.addKeyListener(this);
		nrParticipantsField.setEditable(false); //Disabled by default
		// DESIGN FOR Field:
//		nrParticipantsField.setBackground(MainWindow.getBckColor());
//		nrParticipantsField.setForeground(MainWindow.getTxtColor());
		nrParticipantsField.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 12));
		add(nrParticipantsField,cLabel29);
		
		//bookBtn
		GridBagConstraints cLabel30 = new GridBagConstraints();
		cLabel30.insets = new Insets(0,15,0,0);
		cLabel30.gridx = 10;
		cLabel30.gridy = 6;
		bookBtn = new JButton("Auto Book");
		bookBtn.setName("bookBtn");
		bookBtn.addActionListener(this);
		bookBtn.setEnabled(false); //Disacled by default
		// DESIGN FOR Button:
		bookBtn.setBackground(MainWindow.getBckColor());
		bookBtn.setForeground(MainWindow.getTxtColor());
		bookBtn.setFocusPainted(false);
		bookBtn.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 10));
//		loginBtn.setPreferredSize(new Dimension(200, 400));
		add(bookBtn,cLabel30);
		
		// roomLabel
		GridBagConstraints cLabel31 = new GridBagConstraints();
		cLabel31.insets = new Insets(0,50,0,0);
		cLabel31.gridx = 8;
		cLabel31.gridy = 8;
		cLabel31.gridwidth = 2;
		cLabel31.anchor = GridBagConstraints.LINE_START;
		roomLabel = new JLabel("Room:");
		roomLabel.setName("roomLabel");
		//DESIGN for the Label text
		roomLabel.setForeground(MainWindow.getTxtColor());
		roomLabel.setFont(new Font(MainWindow.getMFont(),Font.BOLD,18));
		add(roomLabel,cLabel31);
		
		//List<MeetingRoom> DETAILS MISSING, TEST ONLY
		
		//TEST
		MeetingRoom H3 = new MeetingRoom("H3",(short)2,new ArrayList<TimeSlot>());
		MeetingRoom R7 = new MeetingRoom("R7",(short)5,new ArrayList<TimeSlot>());
		MeetingRoom H1 = new MeetingRoom("H1",(short)6,new ArrayList<TimeSlot>());
		MeetingRoom K2 = new MeetingRoom("K2",(short)8,new ArrayList<TimeSlot>());
		MeetingRoom R77 = new MeetingRoom("R77",(short)15,new ArrayList<TimeSlot>());
		MeetingRoom S2 = new MeetingRoom("S2",(short)5,new ArrayList<TimeSlot>());
		roomListModel.addElement(H3);
		roomListModel.addElement(R7);
		roomListModel.addElement(H1);
		roomListModel.addElement(K2);
		roomListModel.addElement(R77);
		roomListModel.addElement(S2);
		ArrayList<MeetingRoom> roomBookerTest = new ArrayList<MeetingRoom>();
		roomBookerTest.add(H3);
		roomBookerTest.add(R7);
		roomBookerTest.add(H1);
		roomBookerTest.add(K2);
		roomBookerTest.add(R77);
		roomBookerTest.add(S2);
		roomBooker = new RoomBooker(roomBookerTest);
		
		//TEST
		roomList = new JList<MeetingRoom>(roomListModel);
		roomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		roomList.setVisibleRowCount(11);
		roomList.setName("roomList");
		roomList.addListSelectionListener(this);
		roomList.setEnabled(false); //Disabled by default
//		participantsList.setCellRenderer(renderer);
		
		roomsScrollPane = new JScrollPane(roomList);
		roomsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		roomsScrollPane.setEnabled(false); //Disabled by default
//		participantsScrollPane.setPreferredSize(new Dimension(20,80));
		
		GridBagConstraints cLabel32 = new GridBagConstraints();
		cLabel32.fill = GridBagConstraints.HORIZONTAL;
		cLabel32.insets = new Insets(-20,50,0,0);
//		cLabel19.anchor = GridBagConstraints.LINE_START;
//		cLabel18.ipadx = 70;
//		cLabel19.ipady = 70;
//		cLabel19.weighty = 0.1;
//		cLabel19.weightx = 0.4;
		cLabel32.gridheight = 10;
		cLabel32.gridwidth = 3;
		cLabel32.gridx = 8;
		cLabel32.gridy = 10;
//		participantsScrollPane.setPreferredSize(getPreferredSize());
	
//		this.add(participantsScrollPane, BorderLayout.CENTER);
		add(roomsScrollPane, cLabel32);
		
		//saveAppointmentBtn
		GridBagConstraints cLabel33 = new GridBagConstraints();
		cLabel33.insets = new Insets(0,0,0,-50);
		cLabel33.gridx = 8;
		cLabel33.gridy = 21;
		cLabel33.anchor = GridBagConstraints.LINE_END;
		saveAppointmentBtn = new JButton("Save");
		saveAppointmentBtn.setName("saveAppointmentBtn");
		saveAppointmentBtn.addActionListener(this);
		// DESIGN FOR Field:
		saveAppointmentBtn.setBackground(MainWindow.getBckColor());
		saveAppointmentBtn.setForeground(MainWindow.getTxtColor());
		saveAppointmentBtn.setFocusPainted(false);
		saveAppointmentBtn.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 18));
//		loginBtn.setPreferredSize(new Dimension(200, 400));
		add(saveAppointmentBtn,cLabel33);
		
		// if editAppointment is enabled
		if(!isNewAppointmentView){
			//deleteAppointmentBtn
			GridBagConstraints cLabel34 = new GridBagConstraints();
			cLabel34.insets = new Insets(0,63,0,10);
			cLabel34.gridx = 9;
			cLabel34.gridy = 21;
//			cLabel34.anchor = GridBagConstraints.LINE_END;
			deleteAppointmentBtn = new JButton("Delete");
			deleteAppointmentBtn.setName("deleteAppointmentBtn");
			deleteAppointmentBtn.addActionListener(this);
			// DESIGN FOR Field:
			deleteAppointmentBtn.setBackground(MainWindow.getBckColor());
//			deleteAppointmentBtn.setForeground(MainWindow.getTxtColor());
			deleteAppointmentBtn.setForeground(Color.BLACK);
			deleteAppointmentBtn.setFocusPainted(false);
			deleteAppointmentBtn.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 18));
	//		loginBtn.setPreferredSize(new Dimension(200, 400));
			add(deleteAppointmentBtn,cLabel34);
		}
		
		//cancelAppointmentBtn
		GridBagConstraints cLabel35 = new GridBagConstraints();
		cLabel35.insets = new Insets(0,0,0,0);
		cLabel35.gridx = 10;
		cLabel35.gridy = 21;
//		cLabel15.anchor = GridBagConstraints.LINE_START;
		cancelAppointmentBtn = new JButton("Cancel");
		cancelAppointmentBtn.setName("cancelAppointmentBtn");
		cancelAppointmentBtn.addActionListener(this);
		// DESIGN FOR Field:
		cancelAppointmentBtn.setBackground(MainWindow.getBckColor());
		cancelAppointmentBtn.setForeground(MainWindow.getTxtColor());
		cancelAppointmentBtn.setFocusPainted(false);
		cancelAppointmentBtn.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 18));
//		loginBtn.setPreferredSize(new Dimension(200, 400));
		add(cancelAppointmentBtn,cLabel35);
		
		initializeAppointment();
		startField.addActionListener(this);
		endField.addActionListener(this);
		dateMonthField.addActionListener(this);
		dateYearField.addActionListener(this);
		dateDayField.addActionListener(this);
		//ADDING ALL THE EMPLOYEES TO THE EMPLOYEELISTMODEL -- HELT NEDERST I METODEN showCorrectNames(String searchName)
		showCorrectNames("");
		
	}
	
	// Overriding the paintComponent to get background and Header
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        //background Image
        g2d.drawImage(backgroundImg, 0, 0, this);
        
        //Header
		Font font = new Font("Tahoma", Font.BOLD, 24);
		g2d.setFont(font);
		g2d.setColor(Color.WHITE);
		if (!isNewAppointmentView){
			g2d.drawString("Edit Appointment", 450, 150);						
		}
		else{
			g2d.drawString("New Appointment", 450, 150);						
		}
    }
    public Appointment getAppointmentModel(){
    	return appointmentModel;
    }
    private void initializeAppointment(){
    	for (Employee employer : allEmployees) {
			employer.setSelected(false);
		}
    	//Title
    	titleField.setText(appointmentModel.getTitle());
    	//Start time
    	setStartField(appointmentModel.getStartTime());
    	//End time
    	setEndField(appointmentModel.getEndTime());
    	// Duration
    	setDurationField(appointmentModel.getDuration());
    	// Date
    	setDateFields(appointmentModel.getDate());
    	// ParticipantsList
		for (Invitation invitation : appointmentModel.getInvitations()) {
			participantsPanelList.addParticipantPanel(invitation.getParticipantsView());
		}
		
    	for (int i = 0; i < allEmployees.size(); i++) {
    		for (Invitation invitation : appointmentModel.getInvitations()) {
    			if(allEmployees.get(i).equals(invitation.getEmployee())){
    				allEmployees.get(i).setSelected(true);
    				System.out.println("Employee " + invitation.getEmployee() + " selectedPerson " + invitation.getEmployee().isSelected());
    			}
    		}
    	}
		employeeList.repaint();  
    	// Description
    	descriptionField.setText(appointmentModel.getDescription());
    	// Location
    	locationField.setText(appointmentModel.getLocation());
    	// Booked Room
    }
    
	public Boolean isLeapYear(int year){
		if((2000+year)%4 == 0){
			return true;
		}
		return false;
	}
	public void isValidDate(int date, int month, int year){
		System.out.println(date + " " + month + " " + year);
		if(isLeapYear(year) && month == 2 && date>29){
			dateDayField.setSelectedItem(29);			
		}
		else if((!isLeapYear(year)) && month == 2 && date >28 ){
			dateDayField.setSelectedItem(28);
		}
		else if(month == 4 || month == 6 || month ==9 || month == 11){
			if(date==31){
				dateDayField.setSelectedItem(30);
			}
		}
	}
	
	public void fillInDate(){
		for(int i = 1; i<=31; i++){
			dateDayField.addItem((i));
		}
		for(int i = 1; i <= 12; i++){
			dateMonthField.addItem(i);
		}
		for(int i=14; i<24; i++){
			dateYearField.addItem(i);
		}
	}
	
	public JList<Employee> getEmployeeList(){
		return employeeList;
	}
	
	public void createExternalEmployee(){
		if (!exParticipantsField.getText().equals("")){
//			exParticipantsField.getText() // SOMETHING ELSE WITH NAME ??
			Employee newEmployee = new Employee(exParticipantsField.getText());
			appointmentModel.addInvitation(newEmployee);
		}
	}
	
    /** LISTENERS FOR THE ENTIRE JPANEL **/
    /** WHEN FIELDS ARE MODIFIED CHANGES ARE REGISTERED HERE **/
    
	// Key Listener for JTextFields
	@Override
	public void keyPressed(KeyEvent arg0) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getSource() instanceof JTextField){
			JTextField source = (JTextField) e.getSource();
			if(source == nrParticipantsField){
				String text = source.getText();
				System.out.println(text);
				ArrayList<MeetingRoom> availableRooms = roomBooker.availableRooms(text);
				System.out.println(availableRooms);
				roomListModel.clear();
				for(int i = 0; i<availableRooms.size(); i++){
					roomListModel.addElement(availableRooms.get(i));
				}
			}
			if(source == searchField){
				String searchName = searchField.getText();
				System.out.println("You just searched for: " + searchName);
				showCorrectNames(searchName);
				//ArrayList<Employee> employeeListCopy = new ArrayList<Employee>();
				
				
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		System.out.println("("+this.getClass()+"):"+ "Pressing a button: KeyTyped");
	}
	
	// ActionListener for Buttons and ComboBoxes: 
	// Buttons: Add, Book, Save and Cancel
	// ComboBoxes: startTime, EndTime, date(day, month, year)
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("("+this.getClass()+"):"+ "Pressing a button Or Modyfing a comboBox");
		
		// If cancelAppointmentBtn is pressed
		if (e.getSource() == cancelAppointmentBtn){
			System.out.println("Canceled the newAppointment");
			if(from.equals("editApp")){
				MainWindow.removeNewAppointmentView();
				MainWindow.editAppointmentsView();
			}
			else{
				MainWindow.removeNewAppointmentView();	
			}
		}
		// If addExParticipantsBtn is pressed
		else if (e.getSource() == addExParticipantsBtn){
			System.out.println("Adding external Participant");
			if (!exParticipantsField.getText().equals("")){
				createExternalEmployee();
			}
			
		}
		// If addExParticipantsBtn is pressed
		else if (e.getSource() == deleteAppointmentBtn){
			System.out.println("Deleting Appointment");
			if(appointmentModel.getInvitations().isEmpty()){
				if(MainWindow.getRequestHandler().removeAppointment(appointmentModel)){
					MainWindow.removeNewAppointmentView();
					MainWindow.editAppointmentsView();
				}else{
					JOptionPane.showMessageDialog(MainWindow.getMainWindow(),"Could not delete Appointment. Cloud not Available");
				}
			}else{
				if(MainWindow.getRequestHandler().setAppointmentAsDeleted(appointmentModel)){
					MainWindow.removeNewAppointmentView();
					MainWindow.editAppointmentsView();
				}else{
					JOptionPane.showMessageDialog(MainWindow.getMainWindow(),"Could not delete Appointment. Cloud not Available");
				}
			}
			
		}
		// If bookBtn is pressed
		else if (e.getSource() == bookBtn){
			System.out.println("Auto Booking a Room");
		}
		// If saveAppointmentBtn is pressed
		else if (e.getSource() == saveAppointmentBtn){
			if(from.equals("editApp")){
				if(MainWindow.getRequestHandler().editAppointment(appointmentModel)){
					MainWindow.removeNewAppointmentView();
					MainWindow.editAppointmentsView();
				}else{
					JOptionPane.showMessageDialog(MainWindow.getMainWindow(),"Could not Save Appointment. Cloud not Available");
				}
			}
			else{
				System.out.println("Saving the newAppointment");
				if(MainWindow.getRequestHandler().addAppointment(appointmentModel)){
					MainWindow.removeNewAppointmentView();	
				}else{
					JOptionPane.showMessageDialog(MainWindow.getMainWindow(),"Could not Save Appointment. Cloud not Available");
				}
			}
		}
		
		else if(e.getSource() == dateDayField){
			isValidDate((Integer) dateDayField.getSelectedItem(), 
					(Integer) dateMonthField.getSelectedItem(), 
					(Integer) dateYearField.getSelectedItem());
			long date = getSelectedDateAsLong((String)startField.getSelectedItem());
			if (date != -1) appointmentModel.setDate(new Date(date));
		}
		else if(e.getSource() == dateMonthField){
			isValidDate((Integer) dateDayField.getSelectedItem(), 
					(Integer) dateMonthField.getSelectedItem(), 
					(Integer) dateYearField.getSelectedItem());
			long date = getSelectedDateAsLong((String)startField.getSelectedItem());
			if (date != -1) appointmentModel.setDate(new Date(date));
		}
		else if(e.getSource() == dateYearField){
			isValidDate((Integer) dateDayField.getSelectedItem(), 
					(Integer) dateMonthField.getSelectedItem(), 
					(Integer) dateYearField.getSelectedItem());
			long date = getSelectedDateAsLong((String)startField.getSelectedItem());
			if (date != -1) appointmentModel.setDate(new Date(date));
		}
		else if(e.getSource() == startField){
			if (isValidTime((String) startField.getSelectedItem())){
				long newStart = getSelectedDateAsLong((String)startField.getSelectedItem());
				if (newStart != -1) appointmentModel.setStartTime(newStart);
			}
		}
		else if(e.getSource() == endField){
			if (isValidTime((String) endField.getSelectedItem())){
				long newEnd = getSelectedDateAsLong((String)endField.getSelectedItem());
				if (newEnd != -1) appointmentModel.setEndTime(newEnd);
			}
		}
	}
	
	private boolean isValidDuration() {
		String dur = durationField.getText();
		for (int i = 0; i < dur.length(); i++) {
			if (!Character.isDigit(dur.charAt(i))){
				return false;
			}
		}
		return true;
	}

	public boolean isValidTime(String time){
		if (time.length() == 4 && time.charAt(1) == ':' && Character.isDigit(time.charAt(0)) && Character.isDigit(time.charAt(2)) && Character.isDigit(time.charAt(3))){
			return true;
		}
		else if (time.length() == 5 && time.charAt(2) == ':' && Character.isDigit(time.charAt(0)) && Character.isDigit(time.charAt(1)) && Character.isDigit(time.charAt(3)) && Character.isDigit(time.charAt(4))){
			return true;
		}
		return false;
	}
	
	private long getSelectedDateAsLong(String timeOfDay){
		SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmssZ");
		timeOfDay = timeOfDay.replaceFirst(":", "");
		String dateText = "";
		dateText += dateYearField.getSelectedItem().toString().length() == 2 ? dateYearField.getSelectedItem() : "0" + dateYearField.getSelectedItem();
		dateText += dateMonthField.getSelectedItem().toString().length() == 2 ? dateMonthField.getSelectedItem() : "0" + dateMonthField.getSelectedItem();
		dateText += dateDayField.getSelectedItem().toString().length() == 2 ? dateDayField.getSelectedItem() : "0" + dateDayField.getSelectedItem();
		dateText += timeOfDay.length() == 4 ? timeOfDay : "0" + timeOfDay;
		dateText += "00";
		dateText += "+0100";
		try {
			Calendar c = Calendar.getInstance();
			TimeZone tz = TimeZone.getTimeZone("Europe/Oslo");
			TimeZone.setDefault(tz);
			Date date = format.parse(dateText);
			c.setTime(date);
			System.out.println(tz.inDaylightTime(date));
			if (tz.inDaylightTime(date)){
				c.add(Calendar.HOUR_OF_DAY, -1);
			}
			return c.getTimeInMillis();
		} catch (ParseException e) {
			System.out.println("Wrong date format");
		}
		return -1;
	}
	
	// ItemListener for the RadioButtons
	@Override
	public void itemStateChanged(ItemEvent e) {
		System.out.println("("+this.getClass()+"):"+ "Pressing a Radiobutton");
		if(e.getSource() == internalRadioButton){
			setBookingGreyedOut(false);
		}
		else if(e.getSource() == externalRadioButton){
			setBookingGreyedOut(true);
		}
	}
	
	// making the external / internal selection greyed out
	public void setBookingGreyedOut(Boolean bol){
		if(bol){
			nrParticipantsField.setEditable(false);
			bookBtn.setEnabled(false);
			roomsScrollPane.setEnabled(false);
			roomList.setEnabled(false);
			locationField.setEnabled(true);
		}
		else{
			nrParticipantsField.setEditable(true);
			bookBtn.setEnabled(true);
			roomsScrollPane.setEnabled(true);
			roomList.setEnabled(true);
			locationField.setEnabled(false);
		}
	}
	
	// LisListSelectionListener for the JLists: employeeList, roomList and participantsList
	@Override
	public void valueChanged(ListSelectionEvent e) {

	}
	
	//Mouse Listner TEST for Lists
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("("+this.getClass()+"):"+ "Clicking a List Model");
		if( employeeList.isEnabled()){
			int index = employeeList.locationToIndex(e.getPoint());  
			Employee selectedPerson = (Employee)employeeList.getModel().getElementAt(index);  
			selectedPerson.setSelected(! selectedPerson.isSelected());  
			Rectangle rect = employeeList.getCellBounds(index, index);  
			employeeList.repaint(rect);  
			if (selectedPerson.isSelected()){  
				//ADDING PERSON TO THE PARTICIPANTS LIST
				appointmentModel.addInvitation(selectedPerson);
//				emailList.addElement(selectedPerson.toString());  
			}  
			if (!selectedPerson.isSelected()){  
				// REMOVING PERSON FROM THE PARTICIPANTS LIST
				appointmentModel.removeInvitation(selectedPerson);
//				emailList.removeElement(selectedPerson.toString());  
			}  
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
	
	// Focus listner for JTextFields
	@Override
	public void focusGained(FocusEvent f) {
		
	}
	@Override
	public void focusLost(FocusEvent f) {
		System.out.println("("+this.getClass()+"):"+ "Focus lost TextField");
		// Updates Model
		if(f.getSource() == titleField){
			appointmentModel.setTitle(titleField.getText());
		}
		
		else if(f.getSource() == durationField){
			if (isValidDuration()){
				appointmentModel.setDuration(Long.parseLong(durationField.getText())*60000);
			}
		}
		else if(f.getSource() == descriptionField){
			appointmentModel.setDescription(descriptionField.getText());
		}
		else if(f.getSource() == locationField){
			appointmentModel.setLocation(locationField.getText());
		}
		
		
		
		// TEST
	}
	//TableModelListener
//	@Override
//	public void tableChanged(TableModelEvent e) {
//		int row = e.getFirstRow();
//		int col = e.getColumn();
//		TableModel model = (TableModel)e.getSource();
//		String columnName = model.getColumnName(col);
//		Object data = model.getValueAt(row, col);
//		System.out.println("data: "+data);
//		System.out.println("colname: "+columnName);
//		System.out.println("personName: "+model.getValueAt(row, 0));
//	}
	public void showCorrectNames(String name){
		if(name.equals("")){
			//Initialize the list, and is supposed to show all names when you remove all text from searchField
			employeeListModel.clear();
			for(int i = 0; i < allEmployees.size(); i++){
				employeeListModel.addElement(allEmployees.get(i));	
			}
		}
		else{
			ArrayList<Employee> matching = new ArrayList<Employee>();
			employeeListModel.clear();
			for(int i = 0; i < allEmployees.size(); i++){
				if(allEmployees.get(i).getName().toLowerCase().contains(name.toLowerCase())){
					matching.add(allEmployees.get(i));
				}
			}
			for(int i = 0; i < matching.size(); i++){
				employeeListModel.addElement(matching.get(i));
			}
		}
	}

	// Property change for Appointment Model
//	public void appointmentChanged(String change, Invitation newInvitation) {
//		System.out.println("("+this.getClass()+"):"+ "Property changed on Appointment Model");
//		if (change.equals("add")){
//			participantsPanelList.addParticipantView(newInvitation.getParticipantsView());
//		}
//		else if (change.equals("remove")){
//			newInvitation.getParticipantsView().removeThisView();
//
//		}
//	}
	// Property changed for appointments
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("add")){
			participantsPanelList.addParticipantPanel(((Invitation)evt.getNewValue()).getParticipantsView());
		}
		else if(evt.getPropertyName().equals("remove")){
			((Invitation)evt.getNewValue()).getParticipantsView().removeThisView();
		}
		else if(evt.getPropertyName().equals(Appointment.TIMESLOT_PROPERTY_NAME)){
			TimeSlot timeSlot = (TimeSlot) evt.getNewValue();
			
			//Fixing startTimeFormat
			if (timeSlot.getStart() != 0) {
				setStartField(appointmentModel.getStartTime());
			}
			
			//Fixing endTimeFormat
			if (timeSlot.getEnd() != 0){
				setEndField(appointmentModel.getEndTime());
			}
			
			//Fixing durationFormat
			if (timeSlot.getDuration() != 0){
				setDurationField(appointmentModel.getDuration());
			}
			setDateFields(appointmentModel.getDate());
		}
	}
	
	private void setStartField(String startTime){
		if (startField.getSelectedItem() != startTime){
			startField.setSelectedItem(startTime);
		}
	}
	
	private void setEndField(String endTime){
		if (startField.getSelectedItem() != endTime){
			endField.setSelectedItem(endTime);
		}
	}
	
	private void setDurationField(long dur){
		if (!durationField.getText().equals(Long.toString(dur))){
			durationField.setText(Long.toString(dur));
		}
	}
	
	private void setDateFields(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		if ((int) dateDayField.getSelectedItem() != c.get(Calendar.DAY_OF_MONTH)){
			dateDayField.setSelectedItem(c.get(Calendar.DAY_OF_MONTH));
		}
		if ((int) dateMonthField.getSelectedItem() != c.get(Calendar.MONTH)+1){
			dateMonthField.setSelectedItem(c.get(Calendar.MONTH)+1);
		}
		if ((int) dateYearField.getSelectedItem() != c.get(Calendar.YEAR)){
			dateYearField.setSelectedItem(c.get(Calendar.YEAR));
		}
		System.out.println("Date changed");
	}
}