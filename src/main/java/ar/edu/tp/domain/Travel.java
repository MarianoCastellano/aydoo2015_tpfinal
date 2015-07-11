package ar.edu.tp.domain;

public class Travel {


	private Location origin;
	private Location destination;


	public Travel( Location origin, Location destination) {
	
		this.origin = origin;
		this.destination = destination;
		
	}

	

	public Location getDestination() {
		return destination;
	}

	public Location getOrigin() {
		return origin;
	}

	
	
	@Override
	public int hashCode() {
		return this.origin.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		Travel other = (Travel) obj;
		return ((this.origin.equals(other.origin))&&(this.destination.equals(other.destination)));
	}
}