package model;

import gui.ParticipantsPanel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Invitation implements Serializable{
	private int invitationID;
	private Appointment appointment;
	private Employee employee;
	private InvitationStatus status;
	private boolean isDeleted;
	private boolean edited;
	private boolean hidden;
	private long alarmTime;
	private transient ParticipantsPanel participantsPanel;
	private transient PropertyChangeSupport pcs;
	
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
		participantsPanel = new ParticipantsPanel(person);
		participantsPanel.setInvitation(this);
	}
	
	@Override
	public boolean equals(Object obj){
		return ((Invitation) obj).getEmployee().getEmployeeID() == this.getEmployee().getEmployeeID();
	}
	
	private void sendNotification(){
	}
	
	//Listeners
	public void addPropertyChangedListener(PropertyChangeListener listener){
		if(pcs == null){ createPcs();}
		pcs.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangedListener(PropertyChangeListener listener){
		if(pcs == null){ createPcs();}
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
		if(participantsPanel == null){
			participantsPanel = new ParticipantsPanel(employee);
			participantsPanel.setInvitation(this);
		}
		participantsPanel.changeStatusField(status);
	}
	public void setEdited(boolean edited) {
		boolean old = this.edited;
		this.edited = edited;
		if(pcs == null){ createPcs();}
		pcs.firePropertyChange(EDITED_PROPERTY_NAME, old, this.edited);
	}

	public void setHidden(boolean hidden) {
		boolean old = this.hidden;
		this.hidden = hidden;
		if(pcs == null){ createPcs();}
		pcs.firePropertyChange(HIDDEN_PROPERTY_NAME, old, this.hidden);
	}
	public void createPcs(){
		pcs = new PropertyChangeSupport(this);
	}

	public void setInvitationID(int invitationID) {
		this.invitationID = invitationID;
	}
	//-------
	public void setParticipantsPanel(ParticipantsPanel newParticipantsView){
		participantsPanel = newParticipantsView;
	}
	public ParticipantsPanel getParticipantsPanel(){
		return participantsPanel;
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

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}
