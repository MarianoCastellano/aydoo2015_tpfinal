package aydoo.edu.tp.domain;

public class Bike {

	private int bikeId;

	public Bike(int bikeId) {
		this.bikeId = bikeId;
	}

	public int getBikeId() {
		return bikeId;
	}

	@Override
	public int hashCode() {
		return Integer.valueOf(this.bikeId).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		Bike other = (Bike) obj;
		return this.bikeId == other.bikeId;
	}
}