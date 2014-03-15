package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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
		App1.setTitle("Seri�st M�te som har sykt lang tittel som ikke g�r");
		Appointment App2 = new Appointment(employer);
		App2.setTitle("M�te");
//		appointmentsListModel.addElement(App1);
//		appointmentsListModel.addElement(App2);
//		appointmentRenderer = new AppointmentCellRenderer();
//		//TEST
//		appointmentsList = new JList<Appointment>(appointmentsListModel);
//		appointmentsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
////		appointmentsList.setVisibleRowCount();
//		appointmentsList.setName("appointmentsList");
//		appointmentsList.addListSelectionListener(this);
//		appointmentsList.setEnabled(true); //Disabled by default
//		appointmentsList.setCellRenderer(appointmentRenderer); // Lage en renderer
//		
//		appointmentsScrollPane = new JScrollPane(appointmentsList);
		appointmentsScrollPane = new JScrollPane();
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
		
		//closeBtn
		GridBagConstraints cLabel3 = new GridBagConstraints();
//		cLabel22.insets = new Insets(0,0,0,50);
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
