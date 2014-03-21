package model;

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
	private boolean isNew;
	private InvitationAlarmTime alarmTime;
	private transient PropertyChangeSupport pcs;
	
	//PropertyNames
	public static String STATUS_PROPERTY_NAME = "status";
	
	
	public Invitation(Employee person, Appointment appointment) {
		this.employee = person;
		this.appointment = appointment;
		status = InvitationStatus.PENDING;
		edited = false;
		hidden = false;
		pcs = new PropertyChangeSupport(this);
	}
	
	@Override
	public boolean equals(Object obj){
		return ((Invitation) obj).getEmployee().getEmployeeID() == this.getEmployee().getEmployeeID();
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
	public InvitationAlarmTime getAlarmTime() {
		return alarmTime;
	}

	public void setAlarmTime(InvitationAlarmTime invitationAlarmTime) {
		this.alarmTime = invitationAlarmTime;
	}
	public void setAlarmTime(int alarmTime) {
		for (InvitationAlarmTime invAlTime : InvitationAlarmTime.values()){
			if (alarmTime == invAlTime.toInt()){
				this.alarmTime = invAlTime;
				break;
			}
		}
	}

	//Setters
	public void setStatus(InvitationStatus newStatus) {
		InvitationStatus oldStatus = status;
		status = newStatus;
		if(pcs == null){ createPcs();}
		pcs.firePropertyChange(Invitation.STATUS_PROPERTY_NAME, oldStatus, status);
	}
	public void setEdited(boolean edited) {
		this.edited = edited;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	public void createPcs(){
		pcs = new PropertyChangeSupport(this);
	}

	public void setInvitationID(int invitationID) {
		this.invitationID = invitationID;
	}
	
	//-------
	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	//Getters
	public Appointment getAppointment() 	{return appointment;}
	public boolean isEdited() 				{return edited;}
	public boolean isHidden() 				{return hidden;}
	public Employee getEmployee() 			{return employee;}
	public InvitationStatus getStatus() 	{return status;}
	public int getInvitationID()			{return invitationID;}
	//-------

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
}
