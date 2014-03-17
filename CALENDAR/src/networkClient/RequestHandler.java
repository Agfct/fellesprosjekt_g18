package networkClient;

import networkDiv.Packet;
import model.Appointment;
import model.Employee;
import model.Notification;

public class RequestHandler {
	
	private Client client;
	
	
	public RequestHandler (Client client){
		this.client = client;
	}
	
	
	public Packet loginRequest (String username, String password){
		return client.request("LOGIN_REQUEST", username, password);
	}
	public Packet getAllEmployees (){
		return client.request("GET_ALL_EMPLOYEES");
	}
	public Packet createEmployee (Employee employee){
		return client.request("CREATE_EMPLOYEE", employee);
	}
	public Packet createNotification (Notification notification){
		return client.request("CREATE_NOTIFICATION", notification);
	}
	public Packet editAppointment (Appointment appointment){
		return client.request("EDIT_APPOINTMENT", appointment);
	}
	public Packet getAlarmByID (int id){
		return client.request("GET_ALARM_BY_ID", id);
	}
	public Packet GetAllApointments (int participantID){
		return client.request("GET_ALL_APPOINTMENTS", participantID);
	}
	public Packet getAllInvitations (int appointmentID){
		return client.request("GET_ALL_INVITATIONS", appointmentID);
	}
	public Packet getAllMeetingRooms (){
		return client.request("GET_ALL_MEETING_ROOMS");
	}
	public Packet getAppointmentByID (int appointmentID){
		return client.request("GET_APPOINTMENT_BY_ID", appointmentID);
	}
	public Packet getCreatedAppointments (int participantID){
		return client.request("GET_CREATED_APPOINTMENTS", participantID);
	}
	public Packet getEmployeeByParticipantID (int participantID){
		return client.request("GET_EMPLOYEE_BY_PARTICIPANT_ID", participantID);
	}
	public Packet getEmployeeByUsername (String username){
		return client.request("GET_EMPLOYEE_BY_USERNAME", username);
	}
	public Packet getGroupMembers (String groupname){
		return client.request("GET_GROUP_MEMBERS", groupname);
	}
	public Packet getInvitationByID (int invitationID){
		return client.request("GET_INVITATION_BY_ID", invitationID);
	}
	public Packet getInvitedAppointments (int participantID){
		return client.request("GET_INVITED_APPOINTMENTS", participantID);
	}
	public Packet getNotificationsByParticipantID (int participantID){
		return client.request("GET_NOTIFICATIONS_BY_PARTICIPANT_ID", participantID);
	}
	public Packet getSchedule (String meetingRoom){
		return client.request("GET_SCHEDULE", meetingRoom);
	}
}
