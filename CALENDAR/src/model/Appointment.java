package model;

import gui.MainWindow;
import gui.NewAppointmentView;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class Appointment {
	private int appointmentID;
	private Creator creator;
	private String title;
	private TimeSlot timeSlot;
	private String location;
	private MeetingRoom room;
	private boolean internal;
	private String description;
	private ArrayList<Invitation> invitations;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	//PropertyNames
	public final static String INVITATIONS_PROPERTY_NAME = "invitations";
	
	//Lager en tom avtale
	public Appointment(Employee person){
		this(new Creator(person), "", new TimeSlot(0, 0), "", null, false, "", new ArrayList<Invitation>());
	}
	
	public Appointment(Creator creator, String title, TimeSlot timeSlot,
			String location, MeetingRoom room, boolean internal,
			String description, ArrayList<Invitation> invitations) {
		this.creator = creator;
		this.title = title;
		this.timeSlot = timeSlot;
		this.location = location;
		this.room = room;
		this.internal = internal;
		this.description = description;
		this.invitations = invitations;
		
		
	}

	public void save(){
		for (int i = 0; i < invitations.size(); i++) {
			invitations.get(i).setEdited(true);
		}
		//TODO: Lagre i database
	}
	
	//Invitations
	public void addInvitation(Employee employee){
		Invitation temp = new Invitation(employee);
		invitations.add(temp);
//		MainWindow.getNewAppoitnmentsView().appointmentChanged("add", temp);
		System.out.println("FIRE IN THE HOLE 1");
		pcs.firePropertyChange("add", null, temp);
		WTFisWithTheLIst();
	}
	
	public void removeInvitation(Employee employee){
		for (Invitation removedInvite : invitations) {
			if(removedInvite.getEmployee() == employee){
//				MainWindow.getNewAppoitnmentsView().appointmentChanged("remove", removedInvite);
				System.out.println("FIRE IN THE HOLE 1");
				pcs.firePropertyChange("remove", null, removedInvite);
				invitations.remove(removedInvite);
				WTFisWithTheLIst();
				break;
			}
		}
	}
	public void WTFisWithTheLIst(){
		for (int i = 0; i < invitations.size(); i++) {
			System.out.println(invitations.get(i).toString());
		}
	}
	
	public ArrayList<Invitation> getInvitations() {
		return invitations;
	}

	public void setInvitations(ArrayList<Invitation> invitations) {
		this.invitations = invitations;
	}
	//-----------
	//Listeners
	public void addPropertyChangedListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangedListener(PropertyChangeListener listener){
		pcs.removePropertyChangeListener(listener);
	}
	//Get replies
	public ArrayList<Invitation> getUnanswered(){
		ArrayList<Invitation> unanswered = new ArrayList<Invitation>();
		
		for (int i = 0; i < invitations.size(); i++) {
			if (invitations.get(i).getStatus() == InvitationStatus.PENDING){
				unanswered.add(invitations.get(i));
			}
		}
		return unanswered;
	}
	
	public ArrayList<Invitation> getDeclined(){
		ArrayList<Invitation> declined = new ArrayList<Invitation>();
		
		for (int i = 0; i < invitations.size(); i++) {
			if (invitations.get(i).getStatus() == InvitationStatus.ACCEPTED){
				declined.add(invitations.get(i));
			}
		}
		return declined;
	}
	
	public ArrayList<Invitation> getAccepted(){
		ArrayList<Invitation> accepted = new ArrayList<Invitation>();
		
		for (int i = 0; i < invitations.size(); i++) {
			if (invitations.get(i).getStatus() == InvitationStatus.DECLINED){
				accepted.add(invitations.get(i));
			}
		}
		return accepted;
	}
	//-------
	

	//Listeners
	
	//---------
	
	//Setters
	public void setTitle(String title) 				{this.title = title;}
	public void setRoom(MeetingRoom room) 			{this.room = room;}
	public void setTimeSlot(TimeSlot timeSlot) 		{this.timeSlot = timeSlot;}
	public void setLocation(String location) 		{this.location = location;}
	public void setInternal(boolean internal) 		{this.internal = internal;}
	public void setDescription(String description) 	{this.description = description;}
	public void setAppointmentID(int appointmentID) {this.appointmentID = appointmentID;}
	public void setDuration(int newDur)				{this.timeSlot.setDuration(newDur);}
	//-------
	
	//Getters
	public Creator getCreator() 	{return creator;}
	public String getTitle() 		{return title;}
	public TimeSlot getTimeSlot() 	{return timeSlot;}
	public String getLocation() 	{return location;}
	public MeetingRoom getRoom() 	{return room;}
	public boolean isInternal() 	{return internal;}
	public String getDescription() 	{return description;}
	public int getAppointmentID()	{return appointmentID;}
	public long getDuration() 		{return getTimeSlot().getDuration();}
	public long getStartTime()		{return getTimeSlot().getStart();}
	public long getEndTime()		{return getTimeSlot().getEnd();}
	//-------
}
