package model;

public class AppointmentHandler {
	public static Appointment createAppointment(Employee creator){
		return new Appointment(creator);
	}
	
	public static void editAppointment(Appointment appointment){
		sendNotification(appointment);
	}
	
	public static void deleteAppointment(Appointment appointment){
		sendNotification(appointment);
	}
	
	private static void sendNotification(Appointment appointment){
	}
}
