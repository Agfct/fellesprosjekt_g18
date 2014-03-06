package gui;

public class Invitation {
	private Person person;
	private InvitationStatus status;
	private boolean isEdited;
	private boolean isHidden;
	private Alarm alarm;
	
	public Invitation(Person person) {
		this.person = person;
		status = InvitationStatus.UNANSWERED;
		isEdited = false;
		isHidden = false;
	}
	
	private void sendNotification(){
		//TODO: Send a notification to the creator
	}
	
	public void reject(){
		status = InvitationStatus.REJECTED;
		sendNotification();
	}
	public void accept(){
		status = InvitationStatus.ACCEPTED;
		sendNotification();
	}
	
	//Setters
	public void setEdited(boolean isEdited) {
		this.isEdited = isEdited;
	}

	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

	public void setAlarm(Alarm alarm) {
		this.alarm = alarm;
	}
	
	//Getters
	public boolean isEdited() 			{return isEdited;}
	public boolean isHidden() 			{return isHidden;}
	public Person getPerson() 			{return person;}
	public Alarm getAlarm() 			{return alarm;}
	public InvitationStatus getStatus() {return status;}
}
