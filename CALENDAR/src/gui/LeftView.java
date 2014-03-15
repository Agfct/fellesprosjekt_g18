package gui;

import java.awt.Color;
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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Appointment;
import model.Creator;
import model.Employee;
import model.Invitation;
import model.InvitationStatus;
import model.TimeSlot;

/** LeftView (JPanel)
 *  It contains the buttons for Appointments (new, edit, appointments).
 *	It Creates the newAppointmentView, editAppointmentsView and appointmentsView.
 *	Keeps the list on what other internal appointment lists the user wants to track. //Not implemented yet
 *  
 * @author Anders
 */
public class LeftView extends JPanel implements ListSelectionListener, ActionListener, KeyListener{
	private JLabel weekLabel;
	private JLabel addExAppointmentsLabel;
	private JLabel searchLabel;
	private JComboBox weekBox;
//	private JCalendar monthCal; EXTRA ??
	private JButton newAppointmentBtn;
	private JButton editAppointmentsBtn;
	private JButton appointmentsBtn;
	private JTextField searchField;
	private JList <Employee> internPersonList;
	private DefaultListModel<Employee> listModel = new DefaultListModel<Employee>();
	private JScrollPane internPersonlistScrollPane;
	private JLabel showHiddenLabel;
	private JCheckBox showHiddenBox;
	private Image redCircleImg;

	public LeftView(){
		
		// background and paint components
		redCircleImg = new ImageIcon(this.getClass().getResource("/buttonImages/circleImg.png")).getImage();
		
		// Using a GridBagLayout for the Grid
		setLayout(new GridBagLayout());
		setOpaque(false);
		/** CREATING BUTTONS, LABELS AND TEXT FIELDS **/
		
		//weekLabel
		GridBagConstraints cLabel0 = new GridBagConstraints();
		cLabel0.insets = new Insets(10,20,0,0);
		cLabel0.gridx = 0;
		cLabel0.gridy = 0;
		cLabel0.anchor = GridBagConstraints.LINE_START;
//		cLabel0.gridwidth = 2;
		weekLabel = new JLabel("Week: "); //replace with actual name
		weekLabel.setName("weekLabel");
		//DESIGN for the Label text
		weekLabel.setForeground(Color.BLACK);
		weekLabel.setFont(new Font(MainWindow.getMFont(),Font.BOLD,14));
//		loginBtn.setPreferredSize(new Dimension(200, 400));
		add(weekLabel,cLabel0);
		
		//weekBox (dropdown of all the weeks)
		GridBagConstraints cLabel1 = new GridBagConstraints();
		cLabel1.insets = new Insets(10,40,0,0);
		cLabel1.gridx = 0;
		cLabel1.gridy = 0;
		
		//TEST WEEKS:
		ArrayList<Integer> weeks = new ArrayList<Integer>();
		for (int i = 0; i < 53; i++) {
			weeks.add(i);
		}
		DefaultComboBoxModel  model = new DefaultComboBoxModel();
		for (int i = 0; i < weeks.size(); i++) {
			model.addElement(i);
		}
		//TEST WEEKSs
		
		weekBox = new JComboBox(model);
		weekBox.setName("weekBox");
		weekBox.addActionListener(this);
		//DESIGN for the Label text
//		weekBox.setBackground(MainWindow.getBckColor());
//		weekBox.setForeground(MainWindow.getTxtColor());
		weekBox.setFont(new Font(MainWindow.getMFont(),Font.BOLD,14));
//		loginBtn.setPreferredSize(new Dimension(200, 400));
		add(weekBox,cLabel1);

		
		//JCalendar
		GridBagConstraints cLabel2 = new GridBagConstraints();
//		cLabel0.insets = new Insets(0,50,0,0);
		cLabel2.gridx = 0;
		cLabel2.gridy = 4;
//		cLabel2.gridwidth = 2;
//		monthCal = new JLabel("CALENDAR HERE"); //replace with actual name
//		monthCal.setName("monthCal");
//		//DESIGN for the Label text
//		monthCal.setForeground(Color.RED);
//		monthCal.setFont(new Font(MainWindow.getMFont(),Font.BOLD,18));
////		loginBtn.setPreferredSize(new Dimension(200, 400));
//		add(monthCal,cLabel0);
		add(new JLabel("CALENDAR INN HERE"),cLabel2);
		
		//newAppointmentBtn
		GridBagConstraints cLabel3 = new GridBagConstraints();
//		cLabel1.insets = new Insets(20,50,0,0);
		cLabel3.gridx = 0;
		cLabel3.gridy = 8;
		newAppointmentBtn = new JButton("New Appointment");
		newAppointmentBtn.setName("newAppointmentBtn");
		newAppointmentBtn.addActionListener(this);
		// DESIGN FOR BUTTON:
		newAppointmentBtn.setBackground(MainWindow.getBckColor());
		newAppointmentBtn.setForeground(MainWindow.getTxtColor());
		newAppointmentBtn.setFocusPainted(false);
		newAppointmentBtn.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 12));
//		loginBtn.setPreferredSize(new Dimension(200, 400));
		add(newAppointmentBtn,cLabel3);
		
		//editAppointmentsBtn
		GridBagConstraints cLabel4 = new GridBagConstraints();
//		cLabel1.insets = new Insets(20,50,0,0);
		cLabel4.gridx = 0;
		cLabel4.gridy = 10;
		editAppointmentsBtn = new JButton("Edit Appointments");
		editAppointmentsBtn.setName("editAppointmentsBtn");
		editAppointmentsBtn.addActionListener(this);
		// DESIGN FOR BUTTON:
		editAppointmentsBtn.setBackground(MainWindow.getBckColor());
		editAppointmentsBtn.setForeground(MainWindow.getTxtColor());
		editAppointmentsBtn.setFocusPainted(false);
		editAppointmentsBtn.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 12));
//		loginBtn.setPreferredSize(new Dimension(200, 400));
		add(editAppointmentsBtn,cLabel4);
		
		//appointmentsBtn
		GridBagConstraints cLabel5 = new GridBagConstraints();
//		cLabel1.insets = new Insets(20,50,0,0);
		cLabel5.gridx = 0;
		cLabel5.gridy = 12;
		appointmentsBtn = new JButton("  Appointments   ");
		appointmentsBtn.setName("appointmentsBtn");
		appointmentsBtn.addActionListener(this);
		// DESIGN FOR BUTTON:
		appointmentsBtn.setBackground(MainWindow.getBckColor());
		appointmentsBtn.setForeground(MainWindow.getTxtColor());
		appointmentsBtn.setFocusPainted(false);
		appointmentsBtn.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 12));
//		loginBtn.setPreferredSize(new Dimension(200, 400));
		add(appointmentsBtn,cLabel5);
		
		//addExAppointmentsLabel
		GridBagConstraints cLabel6 = new GridBagConstraints();
//		cLabel0.insets = new Insets(0,50,0,0);
		cLabel6.gridx = 0;
		cLabel6.gridy = 15;
		cLabel6.gridheight = 2;
		addExAppointmentsLabel = new JLabel("<html>Add external Appointments</html>");
		addExAppointmentsLabel.setName("addExAppointmentsLabel");
		//DESIGN for the Label text
		addExAppointmentsLabel.setForeground(Color.BLACK);
		addExAppointmentsLabel.setFont(new Font(MainWindow.getMFont(),Font.BOLD,14));
//		loginBtn.setPreferredSize(new Dimension(200, 400));
		add(addExAppointmentsLabel,cLabel6);
		
		//searchLabel
		GridBagConstraints cLabel8 = new GridBagConstraints();
//		cLabel8.insets = new Insets(0,50,0,0);
		cLabel8.gridx = 0;
		cLabel8.gridy = 18;
		cLabel8.gridwidth = GridBagConstraints.RELATIVE;
		cLabel8.anchor = GridBagConstraints.LINE_START;
		searchLabel = new JLabel("Search: "); 
		searchLabel.setName("searchLabel");
		//DESIGN for the Label text
		searchLabel.setForeground(Color.BLACK);
		searchLabel.setFont(new Font(MainWindow.getMFont(),Font.BOLD,12));
//		loginBtn.setPreferredSize(new Dimension(200, 400));
		add(searchLabel,cLabel8);
		
		//searchField
		GridBagConstraints cLabel9 = new GridBagConstraints();
		cLabel9.insets = new Insets(0,50,0,0);
		cLabel9.gridx = 0;
		cLabel9.gridy = 18;
		cLabel9.gridwidth = GridBagConstraints.REMAINDER;
		cLabel9.fill = GridBagConstraints.HORIZONTAL;
		searchField = new JTextField(20);
		searchField.setName("searchField");
		searchField.addKeyListener(this);
//		//DESIGN for the Label text
//		searchField.setForeground(Color.BLACK);
//		searchField.setFont(new Font(MainWindow.getMFont(),Font.BOLD,12));
//		loginBtn.setPreferredSize(new Dimension(200, 400));
		add(searchField,cLabel9);
		
		
		//List<Employee> DETAILS MISSING, TEST ONLY
		
		//TEST
		Employee anders = new Employee("Anders");
		Employee silje = new Employee("Silje");
		Employee katrine = new Employee("Katrine");
		Employee are = new Employee("Are");
		Employee birger = new Employee("Birger");
		Employee stian = new Employee("Stian");
		listModel.addElement(anders);
		listModel.addElement(silje);
		listModel.addElement(katrine);
		listModel.addElement(are);
		listModel.addElement(birger);
		listModel.addElement(stian);
		//TEST
		internPersonList = new JList<Employee>(listModel);
		internPersonList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		internPersonList.setVisibleRowCount(5);
		internPersonList.setName("internPersonList");
		internPersonList.addListSelectionListener(this);
//		internPersonList.setCellRenderer(renderer);
		
		internPersonlistScrollPane = new JScrollPane(internPersonList);
		
		GridBagConstraints cLabel10 = new GridBagConstraints();
		cLabel10.fill = GridBagConstraints.HORIZONTAL;
//		cLabel10.insets = new Insets(0,0,0,0);
		cLabel10.anchor = GridBagConstraints.LINE_START;
//		cLabel10.ipadx = 70;
		cLabel10.ipady = 70;
//		cLabel10.weighty = 0.1;
//		cLabel10.weightx = 0.4;
		cLabel10.gridheight = 4;
		cLabel10.gridwidth = 2;
		cLabel10.gridx = 0;
		cLabel10.gridy = 20;
//		listScrollPane.setPreferredSize(getPreferredSize());
	
//		this.add(listScrollPane, BorderLayout.CENTER);
		add(internPersonlistScrollPane, cLabel10);
		
	
		//showHiddenLabel
		GridBagConstraints cLabel11 = new GridBagConstraints();
		cLabel11.insets = new Insets(0,0,0,0);
		cLabel11.gridx = 0;
		cLabel11.gridy = 26;
//		cLabel11.weighty = 1;
		cLabel11.gridwidth = GridBagConstraints.RELATIVE;
		cLabel11.anchor = GridBagConstraints.LINE_START;
		showHiddenLabel = new JLabel("Show Hidden"); 
		showHiddenLabel.setName("showHiddenLabel");
		//DESIGN for the Label text
		showHiddenLabel.setForeground(Color.BLACK);
		showHiddenLabel.setFont(new Font(MainWindow.getMFont(),Font.BOLD,12));
//		loginBtn.setPreferredSize(new Dimension(200, 400));
		add(showHiddenLabel,cLabel11);
		
		//showHiddenBox
		GridBagConstraints cLabel12 = new GridBagConstraints();
		cLabel12.insets = new Insets(0,80,0,0);
		cLabel12.gridx = 0;
		cLabel12.gridy = 26;
//		cLabel11.weighty = 0.1;
		cLabel12.gridwidth = GridBagConstraints.REMAINDER;
		showHiddenBox = new JCheckBox();
		showHiddenBox.setName("showHiddenBox");
		showHiddenBox.setOpaque(false);
		//DESIGN for the Label text
		showHiddenBox.setForeground(Color.BLACK);
		showHiddenBox.setFont(new Font(MainWindow.getMFont(),Font.BOLD,12));
//		loginBtn.setPreferredSize(new Dimension(200, 400));
		add(showHiddenBox,cLabel12);
	
		//Filling boxes (for empty spaces)
		// Between week and Calendar
		GridBagConstraints cLabel13 = new GridBagConstraints();
		cLabel13.gridx = 0;
		cLabel13.gridy = 1;
		add(new JLabel("                  " ),cLabel13);
		
		// Between Calendar and new appointment
		GridBagConstraints cLabel14 = new GridBagConstraints();
		cLabel14.gridx = 0;
		cLabel14.gridy = 5;
		add(new JLabel("<html> ____________                " +
				"                 </html>"),cLabel14);
		
		// Between Calendar and new appointment
		GridBagConstraints cLabel15 = new GridBagConstraints();
		cLabel15.gridx = 0;
		cLabel15.gridy = 6;
		add(new JLabel("                 "),cLabel15);
		
		//between new appointment and edit appointment
		GridBagConstraints cLabel16 = new GridBagConstraints();
		cLabel16.gridx = 0;
		cLabel16.gridy = 9;
		add(new JLabel("                 "),cLabel16);
		
		//between edit appointment and appointments
		GridBagConstraints cLabel17 = new GridBagConstraints();
		cLabel17.gridx = 0;
		cLabel17.gridy = 11;
		add(new JLabel("                 "),cLabel17);
		
		//between appointments and addExternal text
		GridBagConstraints cLabel18 = new GridBagConstraints();
		cLabel18.gridx = 0;
		cLabel18.gridy = 13;
		add(new JLabel("<html> ____________                " +
				"                 </html>"),cLabel18);
		
		//between appointments and addExternal text
		GridBagConstraints cLabel19 = new GridBagConstraints();
		cLabel19.gridx = 0;
		cLabel19.gridy = 14;
		add(new JLabel("                 "),cLabel19);
		
		//between addExternal text and search
		GridBagConstraints cLabel20 = new GridBagConstraints();
		cLabel20.gridx = 0;
		cLabel20.gridy = 17;
		add(new JLabel("                 "),cLabel20);
		
		//between addExternal text and search
		GridBagConstraints cLabel21 = new GridBagConstraints();
		cLabel21.gridx = 0;
		cLabel21.gridy = 25;
		add(new JLabel("                 "),cLabel21);

	}
	// Painting the numbers
	   @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        Graphics2D g2d = (Graphics2D) g;
	        
	        
	        // Appointments Notification Symbol:
			//Drawing the red circle
	        g2d.drawImage(redCircleImg, 173, 312, this);
	        
			//Drawing the number of new notifications
			//setting Font and color
			Font font = new Font("Tahoma", Font.BOLD, 14);
			g2d.setFont(font);
			g2d.setColor(Color.WHITE);
			Integer nrOfNewAppointmentsNotification = 28; //TEST
			
			if(nrOfNewAppointmentsNotification > 0){
				if (nrOfNewAppointmentsNotification > 9){
					g2d.drawString(nrOfNewAppointmentsNotification.toString(), 177, 330);}
				else{ g2d.drawString(nrOfNewAppointmentsNotification.toString(), 182, 330);}
			}
			
			//editAppointments Notification Symbol
			//Drawing the red circle
	        g2d.drawImage(redCircleImg, 173, 272, this);
	        
			//Drawing the number of new notifications
			//setting Font and color
			font = new Font("Tahoma", Font.BOLD, 14);
			g2d.setFont(font);
			g2d.setColor(Color.WHITE);
			Integer nrOfeditAppointmentNotification = 5; //TEST
			
			if(nrOfeditAppointmentNotification > 0){
				if (nrOfeditAppointmentNotification > 9){
					g2d.drawString(nrOfeditAppointmentNotification.toString(), 177, 290);}
				else{ g2d.drawString(nrOfeditAppointmentNotification.toString(), 182, 290);}
			}
			
	    }
	   
    /** LISTENERS FOR THE ENTIRE JPANEL **/
    /** WHEN FIELDS ARE MODIFIED CHANGES ARE REGISTERED HERE **/
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("("+this.getClass()+"):"+ "Pressing a button");
		
		// If New Appointment is pressed
		// WARNING TEST TEST TEST
		if (e.getSource() == newAppointmentBtn){
			System.out.println("Opening newAppointment Window");
			ArrayList<Invitation> array = new ArrayList<Invitation>();
			for (Employee employer : MainWindow.getEmployeeList()) {
				Invitation inv = new Invitation(employer);
				inv.setStatus(InvitationStatus.ACCEPTED);
				array.add(inv);				
			}
			Appointment app = new Appointment(new Creator(MainWindow.getUser()),"Gammelt Møte",new TimeSlot(0,4),"Borte Vekk",null,false,"Vi skal ha møte, kom", array);
			MainWindow.newAppointmentView(app); 	
		}
		// If New Appointment is pressed
		else if (e.getSource() == appointmentsBtn){
			System.out.println("Opening Appointments Window");
			MainWindow.appointmentsView();
		}
		
	}
	
	// List selection Listener for the internPersonList
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	// Key listener for textField Search
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("("+this.getClass()+"):"+ "Pressing a button: KeyTyped");
		
	}

}
