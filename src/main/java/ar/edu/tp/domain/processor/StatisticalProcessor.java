package ar.edu.tp.domain.processor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ar.edu.tp.domain.Bike;
import ar.edu.tp.domain.Travel;
import ar.edu.tp.domain.parser.TimeAndQuantityBike;
import ar.edu.tp.exception.TravelNotFoundException;

public class StatisticalProcessor {

	private HashMap<Bike, TimeAndQuantityBike> mapBike;
	private HashMap<Travel, Integer> mapTravel;
	private int valueMaxBike;
	private int valueMinBike;
	private float valueMaxTimeUsedBike;
	private float totalTimeAverage;

	public StatisticalProcessor(HashMap<Bike, TimeAndQuantityBike> mapBike,
			HashMap<Travel, Integer> mapTravel) throws TravelNotFoundException {
		this.mapBike = mapBike;
		this.mapTravel = mapTravel;
		this.calculateMaxAndMinBikeAndTime();
	}

	public float getAverageUseTime() {
		return totalTimeAverage;
	}

	public List<Bike> getBikesUsedMoreTimes() {
		List<Bike> listBike = new LinkedList<Bike>();
		for (Map.Entry<Bike, TimeAndQuantityBike> mapa : this.mapBike
				.entrySet()) {
			TimeAndQuantityBike timeAndQuantityBike = mapa.getValue();
			if (timeAndQuantityBike.getQuantityBike() == this.valueMaxBike)
				listBike.add(mapa.getKey());
		}

		return listBike;
	}

	public List<Bike> getBikesUsedLessTimes() {
		List<Bike> listBike = new LinkedList<Bike>();
		for (Map.Entry<Bike, TimeAndQuantityBike> mapa : this.mapBike
				.entrySet()) {
			TimeAndQuantityBike timeAndQuantityBike = mapa.getValue();
			if (timeAndQuantityBike.getQuantityBike() == this.valueMinBike)
				listBike.add(mapa.getKey());
		}

		return listBike;
	}

	public List<Bike> getBikeLongerUsed() {
		List<Bike> listBike = new LinkedList<Bike>();
		for (Map.Entry<Bike, TimeAndQuantityBike> mapa : this.mapBike
				.entrySet()) {
			TimeAndQuantityBike timeAndQuantityBike = mapa.getValue();
			if (timeAndQuantityBike.getTimeUsed() == this.valueMaxTimeUsedBike)
				listBike.add(mapa.getKey());
		}

		return listBike;
	}

	public float getValueMaxTimeUsedBike() {
		return valueMaxTimeUsedBike;
	}

	public List<Travel> getTravelMoreDone() {
		List<Travel> listTravel = new LinkedList<Travel>();
		int countMaxTravel = this.travelMoreDone();
		for (Map.Entry<Travel, Integer> e : this.mapTravel.entrySet()) {
			Travel travel = e.getKey();
			if (countMaxTravel == e.getValue()) {
				listTravel.add(travel);
			}
		}
		return listTravel;
	}

	private int travelMoreDone() {
		int countMaxTravel = 0;

		for (Map.Entry<Travel, Integer> e : this.mapTravel.entrySet()) {

			if (countMaxTravel < e.getValue()) {
				countMaxTravel = e.getValue();
			}
		}

		return countMaxTravel;
	}

	private void calculateMaxAndMinBikeAndTime() {// ///////ver bien nombre
		float timeTotal = 0;
		int quantity = 0;
		boolean isHeader = false;

		for (Map.Entry<Bike, TimeAndQuantityBike> mapa : this.mapBike
				.entrySet()) {
			TimeAndQuantityBike timeAndQuantityBike = mapa.getValue();
			timeTotal += timeAndQuantityBike.getTimeUsed();
			quantity += timeAndQuantityBike.getQuantityBike();

			isHeader=this.allocateVariables(isHeader, timeAndQuantityBike);

		}
		this.totalTimeAverage = timeTotal / quantity;
	}

	private boolean allocateVariables(boolean isHeader,
			TimeAndQuantityBike timeAndQuantityBike) {
		if (!isHeader) {

			this.valueMaxBike = this.valueMinBike = timeAndQuantityBike
					.getQuantityBike();
			this.valueMaxTimeUsedBike = timeAndQuantityBike.getTimeUsed();
			isHeader = true;

		} else {

			this.allocateValueMaxBike(timeAndQuantityBike);
			this.allocateValueMaxTimeUsedBike(timeAndQuantityBike);
			this.allocateValueMinBike(timeAndQuantityBike);

		}
		return isHeader;

	}

	private void allocateValueMaxBike(TimeAndQuantityBike timeAndQuantityBike) {
		if (this.valueMaxBike < timeAndQuantityBike.getQuantityBike())
			this.valueMaxBike = timeAndQuantityBike.getQuantityBike();

	}

	private void allocateValueMinBike(TimeAndQuantityBike timeAndQuantityBike) {
		if (this.valueMinBike > timeAndQuantityBike.getQuantityBike())
			this.valueMinBike = timeAndQuantityBike.getQuantityBike();

	}

	private void allocateValueMaxTimeUsedBike(
			TimeAndQuantityBike timeAndQuantityBike) {
		if (this.valueMaxTimeUsedBike < timeAndQuantityBike.getTimeUsed())
			this.valueMaxTimeUsedBike = timeAndQuantityBike.getTimeUsed();
	}
}
