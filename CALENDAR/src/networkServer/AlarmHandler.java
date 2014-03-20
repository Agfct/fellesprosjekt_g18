package networkServer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import model.Appointment;
import model.Employee;
import model.Invitation;

public class AlarmHandler {
	
	private Timer timer;
	HashMap<Invitation, Timer> timers;
	private EmailHandler emailHandler;

	
	public AlarmHandler () {
		emailHandler = new EmailHandler();
		timers = new HashMap<Invitation, Timer>();
	}
	
	public void setAlarm (Invitation invitation){
		cancelOldTimer(invitation);
		
		timer = new Timer();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(invitation.getAlarmTime());
		Date alarmTime = calendar.getTime();
		timer.schedule(new AlarmTask(invitation.getAppointment(), invitation.getEmployee()), alarmTime);
		
		timers.put(invitation, timer);
		
		System.out.println("AlarmHandler: Alarm set!");
	}
	
	private void cancelOldTimer(Invitation invitation) {
		if (timers.containsKey(invitation)){
			timers.get(invitation).cancel();
			timers.remove(invitation);
			System.out.println("AlarmHandler: Old alarm removed!");
		}
	}

	class AlarmTask extends TimerTask{
		private Appointment appointment;
		private Employee employee;
		
		public AlarmTask(Appointment appointment, Employee employee) {
			this.appointment = appointment;
			this.employee = employee;
		}
		
		public void executeAlarm() {
			String msg = String.format("Reminder for %s. The meeting starts at %d", appointment.getDescription(), appointment.getStartTime());
			emailHandler.sendEmail(employee.getEmail(), msg);
		}
		public void run () {
			executeAlarm();
			cancel();
		}
		
		
	}
}
