package database;

import database.DBAccess;
import model.*;
import java.util.ArrayList;

public class DBQueries {
	
	private static String query = "select * from person";
	private static String query2 = "select * from appointment where appointmentID = 2";
	private static String getInv = "select username from person where participantID = (select participantID from invitation where invitationID = 1);";
	
	  public static void main(String[] args) throws Exception {
	    DBAccess dba = new DBAccess();
	    
	    ArrayList<Appointment> a = dba.getAllAppointments(11);
	    for (Appointment b : a) {
	    	System.out.println(b);
	    }
	    ArrayList<Employee> e = dba.getAllEmployees();
	    for (Employee f : e) {
	    	System.out.println(f);
	    }
	    ArrayList<Employee> c = dba.getGroupMembers("Gruppe1");
	    for (Employee d : c) {
	    	System.out.println(d);
	    }
	    System.out.println(dba.getEmployeeByUsername("Siv"));
	    
	    ArrayList<MeetingRoom> g = dba.getAllMeetingRooms();
	    for (MeetingRoom h : g) {
	    	System.out.println(h);
	    }
	  }

	
	} 

