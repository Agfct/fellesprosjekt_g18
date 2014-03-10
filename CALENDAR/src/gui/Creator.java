package gui;

public class Creator {
	private final Employee person;
	private boolean statusChanged;
	

	public Creator(Employee person) {
		this.person = person;
		statusChanged = false;
	}
	
	public boolean isStatusChanged() {
		return statusChanged;
	}
	public void setStatusChanged(boolean statusChanged) {
		this.statusChanged = statusChanged;
	}
	
	public Employee getPerson(){
		return person;
	}
	
}
