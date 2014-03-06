package gui;

public class TimeSlot {
	private long start;
	private long end;
	
	public TimeSlot(long start, long end) {
		this.start = start;
		this.end = end;
	}
	
	@Override
	public boolean equals(Object timeSlot){
		TimeSlot other = (TimeSlot) timeSlot;
		return (this.start > other.start && this.start < other.end) || 
				(this.end > other.start && this.end < other.end); 
	}
	
	//Getters
	public long getStart()	{return start;}
	public long getEnd() 	{return end;}
}
