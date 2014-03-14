package model;

import gui.ParticipantsView;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Invitation {
	private int invitationID;
	private Employee employee;
	private InvitationStatus status;
	private boolean edited;
	private boolean hidden;
	private Alarm alarm;
	private ParticipantsView participantsView;
	private PropertyChangeSupport pcs;
	
	//PropertyNames
	public static String ANSWER_PROPERTY_NAME = "answer";
	public static String EDITED_PROPERTY_NAME = "edited";
	public static String HIDDEN_PROPERTY_NAME = "hidden";
	
	
	public Invitation(Employee person) {
		this.employee = person;
		status = InvitationStatus.PENDING;
		edited = false;
		hidden = false;
		pcs = new PropertyChangeSupport(this);
		participantsView = new ParticipantsView(person);
		participantsView.setInvitation(this);
	}
	
	@Override
	public boolean equals(Object obj){
		return ((Invitation) obj).getEmployee() == this.getEmployee();
	}
	
	private void sendNotification(){
		//TODO: Send a notification to the creator
	}
	
	//Listeners
	public void addPropertyChangedListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangedListener(PropertyChangeListener listener){
		pcs.removePropertyChangeListener(listener);
	}
	//---------
	
	public String toString(){
		return "ID: "+invitationID + "Employee Name: "+ employee.getName() + "Status: " + status;
	}
	//Setters
	public void setStatus(InvitationStatus is) {
		System.out.println("ER DET RETT STATUS: " + is );
		this.status = is;
		participantsView.changeStatusField(status);
	}
	public void setEdited(boolean edited) {
		boolean old = this.edited;
		this.edited = edited;
		pcs.firePropertyChange(EDITED_PROPERTY_NAME, old, this.edited);
	}

	public void setHidden(boolean hidden) {
		boolean old = this.hidden;
		this.hidden = hidden;
		pcs.firePropertyChange(HIDDEN_PROPERTY_NAME, old, this.hidden);
	}

	public void setAlarm(Alarm alarm) {
		this.alarm = alarm;
	}
	public void setInvitationID(int invitationID) {
		this.invitationID = invitationID;
	}
	//-------
	public void setParticipantsView(ParticipantsView newParticipantsView){
		participantsView = newParticipantsView;
	}
	public ParticipantsView getParticipantsView(){
		return participantsView;
	}
	//Getters
	public boolean isEdited() 			{return edited;}
	public boolean isHidden() 			{return hidden;}
	public Employee getEmployee() 		{return employee;}
	public Alarm getAlarm() 			{return alarm;}
	public InvitationStatus getStatus() {return status;}
	public int getInvitationID()		{return invitationID;}
	//-------
}
