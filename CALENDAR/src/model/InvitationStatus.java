package model;

public enum InvitationStatus {
	PENDING("<font color=\"BLACK\">Pending</font>"),
	DECLINED("<font color=\"RED\">Declined</font>"),
	ACCEPTED("<font color=\"GREEN\">Accepted</font>");
	
	private final String status;
	InvitationStatus (String status){
		this.status = status;
	}
	
	//Getter
	public String getStatus(){return status;}
	
	public String toString(){
		return "<html>"+status+"</html>";
	}
	public String getStatusOnlyText(){
		if(status.equals(ACCEPTED)){
			return "Accepted";
		}
		else if(status.equals(DECLINED)){
			return "Declined";
		}
		else{
			return "Pending";
		}
	}
}
