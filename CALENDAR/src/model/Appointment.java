package model;

import gui.NewAppointmentView;
import gui.ParticipantsView;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class Appointment {
	private Creator creator;
	private String title;
	private TimeSlot timeSlot;
	private String location;
	private MeetingRoom room;
	private boolean internal;
	private String description;
	private ArrayList<Invitation> invitations;
	private static NewAppointmentView newAppointmentView;

	//PropertyNames
	public final static String INVITATIONS_PROPERTY_NAME = "invitations";
	
	//Lager en tom avtale
	public Appointment(Employee person){
		this(new Creator(person), "", null, "", null, false, "", new ArrayList<Invitation>());
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
		ArrayList<Invitation> oldList = invitations;
		invitations.add(new Invitation(employee));
		newAppointmentView.appointmentChanged("add", employee);
	}
	
	public void removeInvitation(Employee employee){
		ArrayList<Invitation> oldList = invitations;
		invitations.remove(new Invitation(employee));
		newAppointmentView.appointmentChanged("remove", employee);
	}
	
	public ArrayList<Invitation> getInvitations() {
		return invitations;
	}

	public void setInvitations(ArrayList<Invitation> invitations) {
		this.invitations = invitations;
	}
	//-----------

	//Get replies
	public ArrayList<Invitation> getUnanswered(){
		ArrayList<Invitation> unanswered = new ArrayList<Invitation>();
		
		for (int i = 0; i < invitations.size(); i++) {
			if (invitations.get(i).getStatus() == InvitationStatus.UNANSWERED){
				unanswered.add(invitations.get(i));
			}
		}
		return unanswered;
	}
	
	public ArrayList<Invitation> getDeclined(){
		ArrayList<Invitation> declined = new ArrayList<Invitation>();
		
		for (int i = 0; i < invitations.size(); i++) {
			if (invitations.get(i).getStatus() == InvitationStatus.DECLINED){
				declined.add(invitations.get(i));
			}
		}
		return declined;
	}
	
	public ArrayList<Invitation> getAccepted(){
		ArrayList<Invitation> accepted = new ArrayList<Invitation>();
		
		for (int i = 0; i < invitations.size(); i++) {
			if (invitations.get(i).getStatus() == InvitationStatus.ACCEPTED){
				accepted.add(invitations.get(i));
			}
		}
		return accepted;
	}
	//-------
	

	//Listeners
	public void setNewAppointmentView(NewAppointmentView appointmentView){
		newAppointmentView = appointmentView;
	}
	//---------
	
	//Setters
	public void setTitle(String title) 				{this.title = title;}
	public void setRoom(MeetingRoom room) 			{this.room = room;}
	public void setTimeSlot(TimeSlot timeSlot) 		{this.timeSlot = timeSlot;}
	public void setLocation(String location) 		{this.location = location;}
	public void setInternal(boolean internal) 		{this.internal = internal;}
	public void setDescription(String description) 	{this.description = description;}
	//-------
	
	//Getters
	public Creator getCreator() 	{return creator;}
	public String getTitle() 		{return title;}
	public TimeSlot getTimeSlot() 	{return timeSlot;}
	public String getLocation() 	{return location;}
	public MeetingRoom getRoom() 	{return room;}
	public boolean isInternal() 	{return internal;}
	public String getDescription() 	{return description;}
	//-------
}
