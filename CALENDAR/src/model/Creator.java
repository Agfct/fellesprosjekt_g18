package model;

public class Creator {
	private final Employee employee;
	private boolean statusChanged;
	

	public Creator(Employee person) {
		this.employee = person;
		statusChanged = false;
	}
	
	public boolean isStatusChanged() {
		return statusChanged;
	}
	public void setStatusChanged(boolean statusChanged) {
		this.statusChanged = statusChanged;
	}
	
	public Employee getEmployee(){
		return employee;
	}
	
}
