package homework8;

public class Jobs {

	private final double startTime;
	private final double endTime;
	private final double weight;
	
	public Jobs(double startTime,double endTime, double weight){
		this.startTime=startTime;
		this.endTime=endTime;
		this.weight=weight;
	}
	public Jobs(double startTime, double endTime)
	{
		this.startTime=startTime;
		this.endTime=endTime;
		this.weight=0;
	}
	public double getStartTime(){
		return startTime;
	}
	public double getEndTime() {
		return endTime;
	}
	public double getWeight() {
		return weight;
	}
}
