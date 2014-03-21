package model;


public enum InvitationAlarmTime {
	NONE (0),
	MINUTES_1 (1),
	MINUTES_2 (2),
	MINUTES_5 (5),
	MINUTES_10 (10),
	MINUTES_15 (15),
	MINUTES_30 (30);
	
	private final int alarmTime;
	InvitationAlarmTime (int alarmTime){
		this.alarmTime = alarmTime;
	}

	
	public int toInt () {
		return (alarmTime * 60000);
	}
	public String toString () {
		if (alarmTime == 0){
			return ("No alarm");
		}
		else return (alarmTime + " min before");
	}
}
