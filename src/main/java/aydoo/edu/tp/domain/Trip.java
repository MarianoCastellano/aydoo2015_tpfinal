package aydoo.edu.tp.domain;

public class Trip {

	private int bike;
	private Integer origin;
	private Integer destination;
	private double time;

	public Trip(int bike, Integer origin, Integer destination, double time) {
		this.bike = bike;
		this.origin = origin;
		this.destination = destination;
		this.time = time;
	}

	public int getBike() {
		return bike;
	}

	public int getDestination() {
		return destination;
	}

	public int getOrigin() {
		return origin;
	}

	public double getTime() {
		return time;
	}

	@Override
	public int hashCode() {
		return this.origin.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		Trip other = (Trip) obj;
		return ((this.origin.equals((other.origin))) && (this.destination.equals((other.destination))));
	}
}