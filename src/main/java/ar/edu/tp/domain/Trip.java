package ar.edu.tp.domain;

public class Trip {

	private Bike bike;
	private Location origin;
	private Location destination;
	private String time;

	public Trip(Bike bike, Location origin, Location destination, String time) {
		this.bike = bike;
		this.origin = origin;
		this.destination = destination;
		this.time = time;
	}

	public Bike getBike() {
		return bike;
	}

	public Location getDestination() {
		return destination;
	}

	public Location getOrigin() {
		return origin;
	}

	public Double getTime() {
		return Double.parseDouble(time);
	}

	@Override
	public int hashCode() {
		return this.origin.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		Trip other = (Trip) obj;
		return ((this.origin.equals(other.origin)) && (this.destination.equals(other.destination)));
	}
}