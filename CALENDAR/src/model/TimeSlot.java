package model;

public class TimeSlot {
	private long start;
	private long end;
	private long duration;
	
	public TimeSlot(long start, long end) {
		this.start = start;
		this.end = end;
	}
	
	@Override
	public boolean equals(Object timeSlot){
		TimeSlot other = (TimeSlot) timeSlot;
		//Returnerer true hvis timeSlot overlapper this
		return (this.start > other.start && this.start < other.end) || 
				(this.end > other.start && this.end < other.end); 
	}
	
	public void setDuration(int newDur){
		this.duration = newDur;
	}
	//TODO: MATTE STUFF. beregne start-tid. og duration, og veksle mellom disse.
	//Getters
	public long getEnd() 		{return end;}
	public long getStart()		{return start;}
	public long getDuration() 	{return duration;}
}
