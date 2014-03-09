package gui;

public class Invitation {
	private Person person;
	private InvitationStatus status;
	private boolean edited;
	private boolean hidden;
	private Alarm alarm;
	
	public Invitation(Person person) {
		this.person = person;
		status = InvitationStatus.UNANSWERED;
		edited = false;
		hidden = false;
	}
	
	private void sendNotification(){
		//TODO: Send a notification to the creator
	}
	
	public void reject(){
		status = InvitationStatus.DECLINED;
		sendNotification();
	}
	public void accept(){
		status = InvitationStatus.ACCEPTED;
		sendNotification();
	}
	
	//Setters
	public void setEdited(boolean isEdited) {
		this.edited = isEdited;
	}

	public void setHidden(boolean isHidden) {
		this.hidden = isHidden;
	}

	public void setAlarm(Alarm alarm) {
		this.alarm = alarm;
	}
	//-------
	
	//Getters
	public boolean isEdited() 			{return edited;}
	public boolean isHidden() 			{return hidden;}
	public Person getPerson() 			{return person;}
	public Alarm getAlarm() 			{return alarm;}
	public InvitationStatus getStatus() {return status;}
	//-------
}
