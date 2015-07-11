package ar.edu.tp.domain.parser;

public class TimeAndQuantityBike {
	private float timeUsed;
	private int quantityBike;

	public TimeAndQuantityBike(){
		this.timeUsed=0;
		this.quantityBike=1;
		
	}
	public float getTimeUsed() {
		return timeUsed;
	}

	public void setTimeUsed(float timeUsed) {
		this.timeUsed += timeUsed;
	}

	public int getQuantityBike() {
		return quantityBike;
	}

	public void setQuantityBikeOneMore() {
		this.quantityBike ++;
	}

}
