package ar.edu.tp.domain.processor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ar.edu.tp.domain.Bike;
import ar.edu.tp.domain.Travel;
import ar.edu.tp.exception.TravelNotFoundException;

public class StatisticalProcessor {

	private List<Travel> travels;

	public StatisticalProcessor(List<Travel> travels) throws TravelNotFoundException {
		this.travels = travels;
		validateEmptyTravels();
	}

	public List<Bike> getBikesUsedMoreTimes() {
		Map<Bike, Integer> useOfBike = generateUseOfBike();

		Integer moreTimes = Collections.max(useOfBike.values());

		return findBikesByFrecuency(useOfBike, moreTimes);
	}

	public List<Bike> getBikesUsedLessTimes() {
		Map<Bike, Integer> useOfBike = generateUseOfBike();

		Integer lessTimes = Collections.min(useOfBike.values());

		return findBikesByFrecuency(useOfBike, lessTimes);
	}

	public List<Travel> getTravelMoreDone() {

		Map<Travel, Integer> travelCount = getTravelCountMap();

		Integer maxCountOfTravelDone = Collections.max(travelCount.values());

		return travelMoreDone(travelCount, maxCountOfTravelDone);
	}

	private List<Travel> travelMoreDone(Map<Travel, Integer> travelCount, Integer max) {

		List<Travel> travels = new LinkedList<Travel>();

		for (Map.Entry<Travel, Integer> e : travelCount.entrySet()) {
			Travel key = e.getKey();
			Integer value = e.getValue();
			if (value.equals(max)) {
				travels.add(key);
			}
		}

		return travels;
	}

	public Double getAverageUseTime() {
		Double timeTotal = new Double(0);
		Integer quantity = 0;
		for (Travel travel : travels) {
			Double time = travel.getTime();
			timeTotal += time;
			quantity++;
		}
		// TODO NANO: validar la division por cero.
		return timeTotal / quantity;
	}

	private List<Bike> findBikesByFrecuency(Map<Bike, Integer> useOfBike, Integer frecuency) {
		List<Bike> bikes = new LinkedList<Bike>();
		for (Map.Entry<Bike, Integer> e : useOfBike.entrySet()) {
			Bike key = e.getKey();
			Integer value = e.getValue();
			if (value.equals(frecuency)) {
				bikes.add(key);
			}
		}
		return bikes;
	}

	private Map<Bike, Integer> generateUseOfBike() {
		List<Bike> bikes = getAllBikes();
		Map<Bike, Integer> useOfBike = new HashMap<Bike, Integer>();
		for (Bike bike : bikes) {
			if (!useOfBike.containsKey(bike)) {
				int frequency = Collections.frequency(bikes, bike);
				useOfBike.put(bike, new Integer(frequency));
			}
		}
		return useOfBike;
	}

	private List<Bike> getAllBikes() {
		List<Bike> bikes = new ArrayList<Bike>();
		for (Travel travel : travels) {
			bikes.add(travel.getBike());
		}
		return bikes;
	}

	private Map<Travel, Integer> getTravelCountMap() {
		Map<Travel, Integer> travelCountMap = new HashMap<Travel, Integer>();
		int count = 0;
		for (Travel travel : travels) {
			if (!travelCountMap.containsKey(travel)) {
				count = getTravelCount(travel);
				travelCountMap.put(travel, new Integer(count));
			}
		}
		return travelCountMap;
	}

	private int getTravelCount(Travel travelToCount) {
		int count = 0;
		for (Travel travel : travels) {
			if (travel.equals(travelToCount)) {
				count++;
			}
		}
		return count;
	}

	private void validateEmptyTravels() throws TravelNotFoundException {
		if (this.travels == null || this.travels.isEmpty()) {
			throw new TravelNotFoundException("No hay recorridos para procesar");
		}
	}

}
