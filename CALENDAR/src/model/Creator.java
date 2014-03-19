package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Creator implements Serializable{
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
