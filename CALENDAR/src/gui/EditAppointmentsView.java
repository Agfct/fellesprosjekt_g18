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
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import model.Appointment;
import model.Employee;

public class EditAppointmentsView extends JPanel implements ActionListener{
	
	private Image backgroundImg;
	
	private JScrollPane appointmentsScrollPane;
	private AppointmentsPanelList appointmentsPanelList;
	private ArrayList<Appointment> appointments;
	
	private JButton closeBtn;
	
	public EditAppointmentsView(){
		//Setting Layout (GridBagLayout)
		setLayout(new GridBagLayout());
				
		backgroundImg = MainWindow.getBackgroundImage();
		

		
		/** CREATING BUTTONS, LABELS AND TEXT FIELDS **/
		
		// appointmentsList
		//TEST
		Employee employer = new Employee("Anders");
		Appointment App1 = new Appointment(employer);
		App1.setTitle("Seriøst Møte som har sykt lang tittel som ikke går");
		Appointment App2 = new Appointment(employer);
		App2.setTitle("Møte");
		
		appointmentsPanelList = new AppointmentsPanelList();
		appointmentsScrollPane = new JScrollPane(appointmentsPanelList);
		appointmentsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		appointmentsScrollPane.setPreferredSize(new Dimension(80,300));
		appointmentsPanelList.setScrollPane(appointmentsScrollPane);
		appointmentsPanelList.setEditAppointmentsView(this);
		
		GridBagConstraints cLabel0 = new GridBagConstraints();
		cLabel0.fill = GridBagConstraints.HORIZONTAL;
//		cLabel0.insets = new Insets(0,0,0,0);
//		cLabel0.anchor = GridBagConstraints.LINE_START;
//		cLabel0.ipadx = 100;
//		cLabel0.ipady = 70;
//		cLabel0.weighty = 0.1;
//		cLabel0.weightx = 0.1;
		cLabel0.gridheight = 10;
		cLabel0.gridwidth = 11;
		cLabel0.gridx = 0;
		cLabel0.gridy = 0;
	

		//this.add(participantsScrollPane, BorderLayout.CENTER);
		add(appointmentsScrollPane, cLabel0);
		
		//closeBtn
		GridBagConstraints cLabel3 = new GridBagConstraints();
		cLabel3.insets = new Insets(10,0,0,0);
		cLabel3.gridx = 10;
		cLabel3.gridy = 11;
		cLabel3.anchor = GridBagConstraints.LINE_END;
		closeBtn = new JButton("Close");
		closeBtn.setName("closeBtn");
		closeBtn.addActionListener(this);
		// DESIGN FOR Field:
		closeBtn.setBackground(MainWindow.getBckColor());
		closeBtn.setForeground(MainWindow.getTxtColor());
		closeBtn.setFocusPainted(false);
		closeBtn.setFont(new Font(MainWindow.getMFont(),Font.BOLD, 14));
//		loginBtn.setPreferredSize(new Dimension(200, 400));
		add(closeBtn,cLabel3);
		
		//to move the appointmentsPane
		GridBagConstraints cLabel4 = new GridBagConstraints();
		cLabel4.gridx = 9;
		cLabel4.gridy = 11;
		add(new JLabel("                                                     "),cLabel4);
		
		GridBagConstraints cLabel5 = new GridBagConstraints();
		cLabel5.gridx = 8;
		cLabel5.gridy = 11;
		add(new JLabel("                                                       "),cLabel5);
		
		/** ADDING ALL APPOINTMENTS EVERYTIME CONSTRUCTOR RUNS**/
		addAllAppointments();
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
		g2d.drawString("Edit Appointments", 450, 150);						
    }
    public void addAllAppointments(){
    	
    	appointments = MainWindow.getRequestHandler().getCreatedAppointments(MainWindow.getUser());
    	for (int i = 0; i < appointments.size(); i++) {
    		if(!appointments.get(i).isDeleted()){
    			appointmentsPanelList.addAppointmentsPanel(new AppointmentsPanel(appointments.get(i)));
    		}
		}
    }
    
    // ActionListener for buttons (Close and so on)
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// If cancelAppointmentBtn is pressed
		if (e.getSource() == closeBtn){
			System.out.println("Closing AppointmentsView");
			MainWindow.removeEditAppointmentsView();
		}
	}
}
