package networkClient;

import java.util.ArrayList;

import networkDiv.Packet;
import model.Appointment;
import model.Employee;
import model.Invitation;
import model.MeetingRoom;
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
	public Boolean editInvitation (Invitation invitation){
		Packet response = client.request("EDIT_INVITATION", invitation);
		if(response.getName().equals("INVITATION_EDITED")){
			return true;			
		}return false;
	}
//	Get
	public ArrayList<Employee> getAllEmployees (){
		Packet response = client.request("GET_ALL_EMPLOYEES");
		return ((ArrayList<Employee>) response.getObject(0));
	}
	public ArrayList<Appointment> getAllApointments (Employee employee){
		Packet response = client.request("GET_ALL_APPOINTMENTS", employee.getParticipantID());
		return ((ArrayList<Appointment>) response.getObject(0));
	}
	public ArrayList<Appointment> getAllApointmentsByWeek (Long start, Long end, Employee employee){
		Packet response = client.request("GET_ALL_APPOINTMENTS_BY_WEEK", start, end, employee);
		return ((ArrayList<Appointment>) response.getObject(0));
	}
	public ArrayList<Invitation> getAllInvitations (Appointment appointment){
		Packet response = client.request("GET_ALL_INVITATIONS", appointment.getAppointmentID());
		return ((ArrayList<Invitation>) response.getObject(0));
	}
	public ArrayList<MeetingRoom> getAllMeetingRooms (){
		Packet response = client.request("GET_ALL_MEETING_ROOMS");
		return ((ArrayList<MeetingRoom>) response.getObject(0));
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
	public ArrayList<Employee> getGroupMembers (String groupname){
		Packet response = client.request("GET_GROUP_MEMBERS", groupname);
		return ((ArrayList<Employee>) response.getObject(0));
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
		Packet response = client.request("SET_APPOINTMENT_AS_DELETED", appointment.getAppointmentID());
		if(response.getName().equals("APPOINTMENT_SET_AS_DELETED")){
			return true;			
		}return false;
	}
	public Boolean setInvitationAsDeleted (Invitation invitation){
		Packet response = client.request("SET_INVITATION_AS_DELETED", invitation.getInvitationID());
		if(response.getName().equals("INVITATION_SET_AS_DELETED")){
			return true;			
		}return false;
	}
//	Remove
	public Boolean removeAppointmentByID (Appointment appointment){
		Packet response = client.request("REMOVE_APPOINTMENT", appointment.getAppointmentID());
		if(response.getName().equals("APPOINTMENT_REMOVED")){
			return true;			
		}return false;
	}
	public Boolean removeInvitationByID (Invitation invitation){
		Packet response = client.request("REMOVE_INVITATION_ID", invitation.getInvitationID());
		if(response.getName().equals("INVITATION_ID_REMOVED")){
			return true;			
		}return false;
	}
	public Boolean removeInvitation (Appointment appointment, Employee employee){
			Packet response = client.request("REMOVE_INVITATION", appointment, employee);
			if(response.getName().equals("INVITATION_REMOVED")){
				return true;			
			}return false;
		}
//	Email
	public Boolean sendEmail (String emailAdress, String message){
		Packet response = client.request("SEND_EMAIL", emailAdress, message);
		if(response.getName().equals("EMAIL_SENT")){
			return true;			
		}return false;
	}	
}
