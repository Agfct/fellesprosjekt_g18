package gui;

public enum InvitationStatus {
	UNANSWERED(0),
	DECLINED(-1),
	ACCEPTED(1);
	
	private final int status;
	InvitationStatus (int status){
		this.status = status;
	}
	
	//Getter
	public int getStatus(){return status;}
}
