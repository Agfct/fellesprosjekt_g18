package model;

import gui.ParticipantsPanel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public class Invitation implements Serializable{
	private int invitationID;
	private Appointment appointment;
	private Employee employee;
	private InvitationStatus status;
	private boolean edited;
	private boolean hidden;
	private long alarmTime;
	private ParticipantsPanel participantsView;
	private PropertyChangeSupport pcs;
	
	//PropertyNames
	public static String ANSWER_PROPERTY_NAME = "answer";
	public static String EDITED_PROPERTY_NAME = "edited";
	public static String HIDDEN_PROPERTY_NAME = "hidden";
	
	
	public Invitation(Employee person, Appointment appointment) {
		this.employee = person;
		this.appointment = appointment;
		status = InvitationStatus.PENDING;
		edited = false;
		hidden = false;
		pcs = new PropertyChangeSupport(this);
		participantsView = new ParticipantsPanel(person);
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
	public long getAlarmTime() {
		return alarmTime;
	}

	public void setAlarmTime(long alarmTime) {
		this.alarmTime = alarmTime;
	}

	//Setters
	public void setStatus(InvitationStatus is) {
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

	public void setInvitationID(int invitationID) {
		this.invitationID = invitationID;
	}
	//-------
	public void setParticipantsView(ParticipantsPanel newParticipantsView){
		participantsView = newParticipantsView;
	}
	public ParticipantsPanel getParticipantsView(){
		return participantsView;
	}
	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	//Getters
	public Appointment getAppointment() 		{return appointment;}
	public boolean isEdited() 			{return edited;}
	public boolean isHidden() 			{return hidden;}
	public Employee getEmployee() 		{return employee;}
	public InvitationStatus getStatus() {return status;}
	public int getInvitationID()		{return invitationID;}
	//-------
}
