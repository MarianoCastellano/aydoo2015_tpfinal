package ar.edu.tp.domain;

public class Location {

	private String originStationId;
	private String originName;
	private String originDate;

	public Location(String originStationId, String originName, String originDate) {
		this.originStationId = originStationId;
		this.originName = originName;
		this.originDate = originDate;
	}

	public String getOriginDate() {
		return originDate;
	}

	public String getOriginName() {
		return originName;
	}

	public String getOriginStationId() {
		return originStationId;
	}
	
	@Override
	public int hashCode() {
		return this.originStationId.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		Location other = (Location) obj;
		return this.originStationId.equals(other.originStationId);
	}
}