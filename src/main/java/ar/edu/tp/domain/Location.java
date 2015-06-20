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
}