package model;

public enum InvitationStatus {
	PENDING("<html><font color=\"BLACK\">Pending</font></html>"),
	DECLINED("<html><font color=\"RED\">Declined</font></html>"),
	ACCEPTED("<html><font color=\"GREEN\">Accepted</font></html>");
	
	private final String status;
	InvitationStatus (String status){
		this.status = status;
	}
	
	//Getter
	public String getStatus(){return status;}
	
	public String toString(){
		return status;
	}
}
