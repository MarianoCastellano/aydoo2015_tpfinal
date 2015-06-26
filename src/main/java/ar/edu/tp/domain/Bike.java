package ar.edu.tp.domain;

public class Bike {

	private String bikeId;
	private User user;

	public Bike(String bikeId) {
		this.bikeId = bikeId;
	}

	public String getBikeId() {
		return bikeId;
	}

	public void use(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
	
	@Override
	public int hashCode() {
		return this.bikeId.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		Bike other = (Bike) obj;
		return this.bikeId.equals(other.bikeId);
	}
}