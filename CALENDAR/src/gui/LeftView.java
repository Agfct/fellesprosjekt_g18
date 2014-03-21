package gui;

import java.awt.Color;
import java.awt.Component;
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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.swing.Action;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
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
import javax.swing.text.html.HTMLDocument.HTMLReader.HiddenAction;

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
@SuppressWarnings("serial")
public class LeftView extends JPanel implements ListSelectionListener, ActionListener, KeyListener{
	private JLabel weekLabel;
	private JLabel addExAppointmentsLabel;
	private JLabel searchLabel;
	private JComboBox<Integer> weekBox;
//	private JCalendar monthCal; EXTRA ??
	private JButton newAppointmentBtn;
	private JButton editAppointmentBtn;
	private JButton appointmentsBtn;
	private JTextField searchField;
	private JList <Employee> internalEmployeesList;
	private DefaultListModel<Employee> internallistModel = new DefaultListModel<Employee>();
	private JScrollPane internalEmployeeslistScrollPane;
	private JLabel showHiddenLabel;
	private JCheckBox showHiddenBox;
	private Image redCircleImg;
	private Integer nrOfNewAppointmentsNotification;
	private Integer nrOfeditAppointmentNotification;
	
	private boolean statusFlagChangeingWeekModel;

	public LeftView(){
		
		// background and paint components
		redCircleImg = new ImageIcon(this.getClass().getResource("/buttonImages/circleImg.png")).getImage();
		
		// Using a GridBagLayout for the Grid
		setLayout(new GridBagLayout());
		setOpaque(false);
		
		statusFlagChangeingWeekModel = false;
		nrOfNewAppointmentsNotification = 0;
		nrOfeditAppointmentNotification = 0;
		
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
//		ArrayList<Integer> weeks = new ArrayList<Integer>();
		DefaultComboBoxModel<Integer>  model = new DefaultComboBoxModel<Integer>();
		for (int i = 1; i < 53; i++) {
//			weeks.add(i);
			model.addElement(i);
		}
//		for (int i = 0; i < weeks.size(); i++) {
//		}
		weekBox = new JComboBox<Integer>(model);
		weekBox.setName("weekBox");
		weekBox.addActionListener(this);
//		weekBox.addItemListener(this);
		//DESIGN for the Label text
//		weekBox.setBackground(MainWindow.getBckColor());
//		weekBox.setForeground(MainWindow.getTxtColor());
		weekBox.setFont(new Font(MainWindow.getMFont(),Font.BOLD,14));
//		loginBtn.setPreferredSize(new Dimension(200, 400));
		add(weekBox,cLabel1);

		
		//JCalendar
//		GridBagConstraints cLabel2 = new GridBagConstraints();
//		cLabel2.insets = new Insets(0,50,0,0);
//		cLabel2.gridx = 0;
//		cLabel2.gridy = 4;
//		cLabel2.gridwidth = 2;
//		datePickerLabel = new JLabel("Select a date"); //replace with actual name
//		datePickerLabel.setName("monthCal");
//		//DESIGN for the Label text
//		datePickerLabel.setForeground(Color.RED);
//		datePickerLabel.setFont(new Font(MainWindow.getMFont(),Font.BOLD,18));
//		add(datePickerLabel,cLabel0);
		
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
		
		//editAppointmentBtn
		GridBagConstraints cLabel4 = new GridBagConstraints();
//		cLabel1.insets = new Insets(20,50,0,0);
		cLabel4.gridx = 0;
		cLabel4.gridy = 10;
		editAppointmentBtn = new JButton("Edit Appointment");
		editAppointmentBtn.setName("editAppointmentBtn");
		editAppointmentBtn.addActionListener(this);
		// DESIGN FOR BUTTON:
		editAppointmentBtn.setBackground(MainWindow.getBckColor());
		editAppointmentBtn.setForeground(MainWindow.getTxtColor());
		editAppointmentBtn.setFocusPainted(false);
		editAppointmentBtn.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 12));
//		loginBtn.setPreferredSize(new Dimension(200, 400));
		add(editAppointmentBtn,cLabel4);
		
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
		addExAppointmentsLabel = new JLabel("<html>Add other Calendars</html>");
		addExAppointmentsLabel.setName("addExAppointmentsLabel");
		//DESIGN for the Label text
		addExAppointmentsLabel.setForeground(Color.BLACK);
		addExAppointmentsLabel.setFont(new Font(MainWindow.getMFont(),Font.BOLD,14));
//		loginBtn.setPreferredSize(new Dimension(200, 400));
		add(addExAppointmentsLabel,cLabel6);
		
		//searchLabel
//		GridBagConstraints cLabel8 = new GridBagConstraints();
////		cLabel8.insets = new Insets(0,50,0,0);
//		cLabel8.gridx = 0;
//		cLabel8.gridy = 18;
//		cLabel8.gridwidth = GridBagConstraints.RELATIVE;
//		cLabel8.anchor = GridBagConstraints.LINE_START;
//		searchLabel = new JLabel("Search: "); 
//		searchLabel.setName("searchLabel");
//		//DESIGN for the Label text
//		searchLabel.setForeground(Color.BLACK);
//		searchLabel.setFont(new Font(MainWindow.getMFont(),Font.BOLD,12));
////		loginBtn.setPreferredSize(new Dimension(200, 400));
//		add(searchLabel,cLabel8);
		
		//searchField
//		GridBagConstraints cLabel9 = new GridBagConstraints();
//		cLabel9.insets = new Insets(0,50,0,0);
//		cLabel9.gridx = 0;
//		cLabel9.gridy = 18;
//		cLabel9.gridwidth = GridBagConstraints.REMAINDER;
//		cLabel9.fill = GridBagConstraints.HORIZONTAL;
//		searchField = new JTextField(20);
//		searchField.setName("searchField");
//		searchField.addKeyListener(this);
////		//DESIGN for the Label text
////		searchField.setForeground(Color.BLACK);
////		searchField.setFont(new Font(MainWindow.getMFont(),Font.BOLD,12));
////		loginBtn.setPreferredSize(new Dimension(200, 400));
//		add(searchField,cLabel9);
		
		// selectedEmployees
		internalEmployeesList = new JList<Employee>(internallistModel);
		internalEmployeesList.setSelectionModel(new DefaultListSelectionModel() {
		    @Override
		    public void setSelectionInterval(int index0, int index1) {
		        if(super.isSelectedIndex(index0)) {
		            super.removeSelectionInterval(index0, index1);
		        }
		        else {
		            super.addSelectionInterval(index0, index1);
		        }
		    }
		});
		internalEmployeesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		internalEmployeesList.setVisibleRowCount(5);
		internalEmployeesList.setName("selectedPersonList");
		internalEmployeesList.addListSelectionListener(this);
//		internPersonList.setCellRenderer(renderer);
		
		internalEmployeeslistScrollPane = new JScrollPane(internalEmployeesList);
		
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
		add(internalEmployeeslistScrollPane, cLabel10);
		
	
		//showHiddenLabel
		GridBagConstraints cLabel11 = new GridBagConstraints();
		cLabel11.insets = new Insets(0,0,0,0);
		cLabel11.gridx = 0;
		cLabel11.gridy =26;
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
		showHiddenBox.addActionListener(this);
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
		
		addEmployees();

	}
	
	public void setLeftViewWeek(){
		Calendar c = Calendar.getInstance();
		c.setTime(MainWindow.getDate());
		statusFlagChangeingWeekModel = true;
		weekBox.getModel().setSelectedItem(c.get(Calendar.WEEK_OF_YEAR));
	}
	// Painting the numbers
	   @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        Graphics2D g2d = (Graphics2D) g;
	        
	        
			nrOfeditAppointmentNotification = 5; //TODO: TEST
			
			if(nrOfeditAppointmentNotification > 0){
				//editAppointments Notification Symbol
				//Drawing the red circle
		        g2d.drawImage(redCircleImg, 173, 247, this);
		        
				//Drawing the number of new notifications
				//setting Font and color
		        Font font = new Font("Tahoma", Font.BOLD, 14);
				g2d.setFont(font);
				g2d.setColor(Color.WHITE);
				
				if (nrOfeditAppointmentNotification > 9){
					g2d.drawString(nrOfeditAppointmentNotification.toString(), 177, 265);}
				else{ g2d.drawString(nrOfeditAppointmentNotification.toString(), 182, 265);}
			}
			
			
			nrOfNewAppointmentsNotification = 28; //TODO: TEST
			if(nrOfNewAppointmentsNotification > 0){
			    // Appointments Notification Symbol:
				//Drawing the red circle
		        g2d.drawImage(redCircleImg, 173, 287, this);
		        
				//Drawing the number of new notifications
				//setting Font and color
				Font font = new Font("Tahoma", Font.BOLD, 14);
				g2d.setFont(font);
				g2d.setColor(Color.WHITE);
				
				if (nrOfNewAppointmentsNotification > 9){
					g2d.drawString(nrOfNewAppointmentsNotification.toString(), 177, 305);}
				else{ g2d.drawString(nrOfNewAppointmentsNotification.toString(), 182, 305);}
			}
			
	

			
	    }
	   
	   public boolean getShowHidden(){
		   return showHiddenBox.isSelected();
	   }
	   
    public void setNrOfNewAppointmentsNotification(
			Integer nrOfNewAppointmentsNotification) {
		this.nrOfNewAppointmentsNotification = nrOfNewAppointmentsNotification;
	}

	public void setNrOfeditAppointmentNotification(
			Integer nrOfeditAppointmentNotification) {
		this.nrOfeditAppointmentNotification = nrOfeditAppointmentNotification;
	}

	/** LISTENERS FOR THE ENTIRE JPANEL **/
    /** WHEN FIELDS ARE MODIFIED CHANGES ARE REGISTERED HERE **/
	   // action listner for checkbox and buttons
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("("+this.getClass()+"):"+ "Pressing a button");
		
		// If New Appointment is pressed
		if (e.getSource() == newAppointmentBtn){
			System.out.println("Opening newAppointment Window");
			Appointment app = new Appointment(MainWindow.getUser());
			MainWindow.removeAppointmentAppWindow(); // Removing the AppWindow if its up
			MainWindow.newAppointmentView(app,true,""); 	
		}
		// If Appointment is pressed
		else if (e.getSource() == appointmentsBtn){
			System.out.println("Opening Appointments Window");
			MainWindow.removeAppointmentAppWindow(); // Removing the AppWindow if its up
			MainWindow.appointmentsView();
		}
		// If Edit Appointments is pressed
		else if (e.getSource() == editAppointmentBtn){
			System.out.println("Opening EditAppointments Window");
			MainWindow.removeAppointmentAppWindow(); // Removing the AppWindow if its up
			MainWindow.editAppointmentsView();
		}
		// If weekBox is modified
		if (e.getSource() == weekBox){
			if(!statusFlagChangeingWeekModel){ // If your not changeing model
				System.out.println("Changeing week");
				Calendar c = Calendar.getInstance();
				c.setTime(MainWindow.getDate());
				c.set(Calendar.WEEK_OF_YEAR, (int) weekBox.getSelectedItem());
				MainWindow.setDate(c.getTime());
			}
			statusFlagChangeingWeekModel = false;
		}
		// If showhiddenBox
		else if (e.getSource() == showHiddenBox){
			System.out.println("Showing hidden");
			MainWindow.getCalendarPanel().addAllAppointments();
		}
		
	}
	public void addEmployees(){
		internallistModel.clear();
		for (Employee employer : MainWindow.getEmployeeList()) {
			internallistModel.addElement(employer);
		}
	}
	
	// List selection Listener for the internPersonList
	@Override
	public void valueChanged(ListSelectionEvent selected) {
		//TODO: WARNING SPAM ? 
		MainWindow.getCalendarPanel().addAllAppointments();
		
	}
	public ArrayList<Employee> getSelectedEmployees(){
		return new ArrayList<Employee>(internalEmployeesList.getSelectedValuesList());
	}
	
	// Key listener for textField Search
	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
