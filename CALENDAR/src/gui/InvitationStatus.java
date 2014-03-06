package gui;

public enum InvitationStatus {
	UNANSWERED(0),
	REJECTED(-1),
	ACCEPTED(1);
	
	private final int status;
	InvitationStatus (int status){
		this.status = status;
	}
	
	//Getter
	public int getStatus(){return status;}
}
