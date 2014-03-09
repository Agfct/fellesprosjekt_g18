package gui;

import java.util.ArrayList;

public class Group {
	private String name;
	private ArrayList<Person> members;
	
	public Group(String name) {
		this.name = name;
		members = new ArrayList<Person>();
	}
	
	//Members
	public void addMember(Person person){
		if (!members.contains(person)){
			members.add(person);
		}
	}
	
	public void removeMember(Person person){
		members.remove(person);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	//-------

	//Getters
	public String getName() {return name;}
	public ArrayList<Person> getMembers() {return members;}
	
	
}
