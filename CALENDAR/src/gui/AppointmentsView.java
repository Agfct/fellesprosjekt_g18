package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.ArrayList;

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
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Appointment;
import model.Employee;
import model.MeetingRoom;
import model.TimeSlot;

public class AppointmentsView extends JPanel implements ListSelectionListener{
	private JLabel filtersLabel;
	private JLabel acceptedLabel;
	private JLabel pendingLabel;
	private JLabel descriptionLabel;
	private JLabel acceptLabel;
	private JLabel declineLabel;
	private JLabel alarmLabel;

	private JTextField descriptionField;
	
	private JCheckBox acceptedBox;
	private JCheckBox showHiddenBox;
	private JCheckBox pendingBox;
	private JCheckBox hideBox; // TEST
	
	private JRadioButton acceptRadioBtn;
	private JRadioButton declineRadioBtn;
	
	private JScrollPane appointmentsScrollPane;
	private JList<Appointment> appointmentsList;
	private DefaultListModel<Appointment> appointmentsListModel = new DefaultListModel<Appointment>();
	
//	private JComboBox<E> alarmBox;
	
	private JButton okBtn;
	private JButton closeBtn;
	
	private Image backgroundImg;
	
	public AppointmentsView (){
		
		//Setting Layout (GridBagLayout)
		setLayout(new GridBagLayout());
//		setOpaque(false);
		backgroundImg = new ImageIcon(this.getClass().getResource("/backgrounds/background1.png")).getImage();
		
		/** CREATING BUTTONS, LABELS AND TEXT FIELDS **/
		
		// Appointments
		//TEST
		Employee employer = new Employee("Anders");
		Appointment App1 = new Appointment(employer);
		appointmentsListModel.addElement(App1);
		//TEST
		appointmentsList = new JList<Appointment>(appointmentsListModel);
		appointmentsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		appointmentsList.setVisibleRowCount(11);
		appointmentsList.setName("appointmentsList");
		appointmentsList.addListSelectionListener(this);
		appointmentsList.setEnabled(false); //Disabled by default
//		participantsList.setCellRenderer(renderer);
		
		appointmentsScrollPane = new JScrollPane(appointmentsList);
		appointmentsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		appointmentsScrollPane.setEnabled(false); //Disabled by default
//		appointmentsScrollPane.setPreferredSize(new Dimension(20,80));
		
		GridBagConstraints cLabel0 = new GridBagConstraints();
		cLabel0.fill = GridBagConstraints.HORIZONTAL;
		cLabel0.insets = new Insets(0,0,0,0);
//		cLabel10.anchor = GridBagConstraints.LINE_START;
//		cLabel10.ipadx = 70;
//		cLabel10.ipady = 70;
//		cLabel10.weighty = 0.1;
//		cLabel10.weightx = 0.4;
		cLabel0.gridheight = 10;
		cLabel0.gridwidth = 3;
		cLabel0.gridx = 8;
		cLabel0.gridy = 10;
//		participantsScrollPane.setPreferredSize(getPreferredSize());
	
//		this.add(participantsScrollPane, BorderLayout.CENTER);
		add(appointmentsScrollPane, cLabel0);
		
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
		g2d.drawString("Appointments", 450, 150);						
    }
    
	//ListSelection Listener for the List of Appointments
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
