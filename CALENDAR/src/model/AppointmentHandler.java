package model;

public class AppointmentHandler {
	public static Appointment createAppointment(Employee creator){
		return new Appointment(creator);
	}
	
	public static void editAppointment(Appointment appointment){
		sendNotification(appointment);
		//TODO: Hva skal denne egentlig gjøre? DB-kall?
	}
	
	public static void deleteAppointment(Appointment appointment){
		sendNotification(appointment);
		//TODO: Hva skal denne gjøre? DB-kall?
	}
	
	private static void sendNotification(Appointment appointment){
		//TODO: send notifications
	}
}
