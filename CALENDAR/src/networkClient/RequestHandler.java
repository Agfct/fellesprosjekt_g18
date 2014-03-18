package networkClient;

import java.util.ArrayList;

import networkDiv.Packet;
import model.Appointment;
import model.Employee;
import model.Invitation;
import model.Notification;
import model.TimeSlot;

public class RequestHandler {
	
	private Client client;
	
	
	public RequestHandler (Client client){
		this.client = client;
	}
	
//	Login
	public Packet loginRequest (String username, String password){
		return client.request("LOGIN_REQUEST", username, password);
	}
//	Add
	public Boolean addEmployee (Employee employee){
		Packet response = client.request("ADD_EMPLOYEE", employee);
		if(response.getName().equals("EMPLOYEE_ADDED")){
			return true;			
		}return false;
	}
	public Boolean addNotification (Notification notification){
		Packet response = client.request("ADD_NOTIFICATION", notification);
		if(response.getName().equals("NOTIFICATION_ADDED")){
			return true;			
		}return false;
	}
	public Boolean addAppointment (Appointment appointment){
		Packet response = client.request("ADD_APPOINTMENT", appointment);
		if(response.getName().equals("APPOINTMENT_ADDED")){
			return true;			
		}return false;
	}
//	Edit
	public Boolean editAppointment (Appointment appointment){
		Packet response = client.request("EDIT_APPOINTMENT", appointment);
		if(response.getName().equals("APPOINTMENT_EDITED")){
			return true;			
		}return false;
	}
//	Get
	public ArrayList<Appointment> getAllEmployees (){
		Packet response = client.request("GET_ALL_EMPLOYEES");
		return ((ArrayList<Appointment>) response.getObject(0));
	}
	
	public ArrayList<Appointment> getAllApointments (Employee employee){
		Packet response = client.request("GET_ALL_APPOINTMENTS", employee.getParticipantID());
		return ((ArrayList<Appointment>) response.getObject(0));
	}
	public ArrayList<Appointment> getAllInvitations (Appointment appointment){
		Packet response = client.request("GET_ALL_INVITATIONS", appointment.getAppointmentID());
		return ((ArrayList<Appointment>) response.getObject(0));
	}
	public ArrayList<Appointment> getAllMeetingRooms (){
		Packet response = client.request("GET_ALL_MEETING_ROOMS");
		return ((ArrayList<Appointment>) response.getObject(0));
	}
	public Appointment getAppointmentByID (int appointmentID){
		Packet response = client.request("GET_APPOINTMENT_BY_ID", appointmentID);
		return ((Appointment) response.getObject(0));
	}
	public ArrayList<Appointment> getCreatedAppointments (Employee employee){
		Packet response = client.request("GET_CREATED_APPOINTMENTS", employee.getParticipantID());
		return ((ArrayList<Appointment>) response.getObject(0));
	}
	public Employee getEmployeeByParticipantID (int participantID){
		Packet response = client.request("GET_EMPLOYEE_BY_PARTICIPANT_ID", participantID);
		return ((Employee) response.getObject(0));
	}
	public Employee getEmployeeByUsername (String username){
		Packet response = client.request("GET_EMPLOYEE_BY_USERNAME", username);
		return ((Employee) response.getObject(0));
	}
	public ArrayList<Appointment> getGroupMembers (String groupname){
		Packet response = client.request("GET_GROUP_MEMBERS", groupname);
		return ((ArrayList<Appointment>) response.getObject(0));
	}
	public Invitation getInvitationByID (int invitationID){
		Packet response = client.request("GET_INVITATION_BY_ID", invitationID);
		return ((Invitation) response.getObject(0));
	}
	public ArrayList<Appointment> getInvitedAppointments (Employee employee){
		Packet response = client.request("GET_INVITED_APPOINTMENTS", employee.getParticipantID());
		return ((ArrayList<Appointment>) response.getObject(0));
	}
	public ArrayList<Notification> getNotifications (Employee employee){
		Packet response = client.request("GET_NOTIFICATIONS_BY_PARTICIPANT_ID", employee.getParticipantID());
		return ((ArrayList<Notification>) response.getObject(0));
	}
	public Boolean getSchedule (String meetingRoom){
		Packet response = client.request("GET_SCHEDULE", meetingRoom);
		return ((Boolean) response.getObject(0));
	}
//	Delete
	public Boolean setAppointmentAsDeleted (Appointment appointment){
		Packet response = client.request("SET_APPOINTMENT_AS_DELETED", appointment);
		if(response.getName().equals("APPOINTMENT_SET_AS_DELETED")){
			return true;			
		}return false;
	}
//	Remove
	public Boolean removeAppointment (Appointment appointment){
		Packet response = client.request("REMOVE_APPOINTMENT", appointment);
		if(response.getName().equals("APPOINTMENT_REMOVED")){
			return true;			
		}return false;
	}
}