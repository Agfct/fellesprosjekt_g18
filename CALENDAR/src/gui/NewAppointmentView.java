package gui;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class NewAppointmentView extends JPanel {
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
	private JTextField startField;
	private JTextField endField;
	private JTextField durationField;
	private JTextField dateField;
	private JTextField searchField;
	private JTextField exParticipantsField;
	private JTextField locationField;
	private JTextField nrParticipantsField;
	private JTextField descriptionField;
	
	private JScrollPane personListScrollPane;
	private JScrollPane roomsScrollPane;
	private JScrollPane descriptionScrollPane;
	private JScrollPane participantsScrollPane;
	
	private JList<Person> personList;
//	private JList<Room> roomList;
	private JList<Person> participantsList;
	private DefaultListModel<Person> personListModel = new DefaultListModel<Person>(); // USE THESE ??
//	private DefaultListModel<Room> roomListModel = new DefaultListModel<Person>(); // USE THESE ??
	private DefaultListModel<Person> participantsListModel = new DefaultListModel<Person>(); // USE THESE ??
	
	private JButton addExParticipantsBtn;
	private JButton bookBtn;
	private JButton deleteParticipantsBtn;
	private JButton saveAppointmentBtn;
	private JButton cancelAppointmentBtn;
	
	private JCheckBox internalCheckBox;
	private JCheckBox externalCheckBox;
	
	

}
