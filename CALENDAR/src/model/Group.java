package model;

import java.util.ArrayList;

public class Group {
	private String name;
	private ArrayList<Employee> members;
	
	public Group(String name) {
		this.name = name;
		members = new ArrayList<Employee>();
	}
	
	//Members
	public void addMember(Employee person){
		if (!members.contains(person)){
			members.add(person);
		}
	}
	
	public void removeMember(Employee person){
		members.remove(person);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	//-------

	//Getters
	public String getName() {return name;}
	public ArrayList<Employee> getMembers() {return members;}
	
	
}
