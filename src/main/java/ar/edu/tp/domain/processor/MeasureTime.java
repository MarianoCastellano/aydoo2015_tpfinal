package ar.edu.tp.domain.processor;

public class MeasureTime {
	
	private float start;
	private  float end ;
	 
	public void startTime(){
		this.start = System.currentTimeMillis();
	}
	
	public void endTime(){
		
		this.end= System.currentTimeMillis();
	}  
	 
	public float totalTime(){
	return  end - start;
	}
}
