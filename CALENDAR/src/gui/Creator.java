package gui;

public class Creator {
	private final Person person;
	private boolean statusChanged;
	

	public Creator(Person person) {
		this.person = person;
		statusChanged = false;
	}
	
	public boolean isStatusChanged() {
		return statusChanged;
	}
	public void setStatusChanged(boolean statusChanged) {
		this.statusChanged = statusChanged;
	}
	
	public Person getPerson(){
		return person;
	}
	
}
