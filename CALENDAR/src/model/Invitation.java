package model;

import gui.NewAppointmentView;
import gui.ParticipantsView;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Invitation {
	private Employee person;
	private InvitationStatus status;
	private boolean edited;
	private boolean hidden;
	private Alarm alarm;
	private static ParticipantsView participantsView;
	private PropertyChangeSupport pcs;
	
	//PropertyNames
	public static String ANSWER_PROPERTY_NAME = "answer";
	public static String EDITED_PROPERTY_NAME = "edited";
	public static String HIDDEN_PROPERTY_NAME = "hidden";
	
	
	public Invitation(Employee person) {
		this.person = person;
		status = InvitationStatus.UNANSWERED;
		edited = false;
		hidden = false;
		pcs = new PropertyChangeSupport(this);
	}
	
	@Override
	public boolean equals(Object obj){
		return ((Invitation) obj).getPerson() == this.getPerson();
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
	
	
	//Answers
	public void reject(){
		InvitationStatus oldStatus = status;
		status = InvitationStatus.DECLINED;
		sendNotification();
		pcs.firePropertyChange(ANSWER_PROPERTY_NAME, oldStatus, status);
	}
	public void accept(){
		InvitationStatus oldStatus = status;
		status = InvitationStatus.ACCEPTED;
		sendNotification();
		pcs.firePropertyChange(ANSWER_PROPERTY_NAME, oldStatus, status);
	}
	//-------
	
	//Setters
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
	public Employee getPerson() 		{return person;}
	public Alarm getAlarm() 			{return alarm;}
	public InvitationStatus getStatus() {return status;}
	
	//-------
}
