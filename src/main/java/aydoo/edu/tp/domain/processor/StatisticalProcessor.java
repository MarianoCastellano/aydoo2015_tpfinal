package aydoo.edu.tp.domain.processor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import aydoo.edu.tp.domain.Bike;
import aydoo.edu.tp.domain.Trip;
import aydoo.edu.tp.exception.TripNotFoundException;

public class StatisticalProcessor {

	private List<Trip> trips;

	public StatisticalProcessor(List<Trip> trips) throws TripNotFoundException {
		this.trips = trips;
		validateEmptyTrips();
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

	public List<Trip> getTripsMoreDone() {

		Map<Trip, Integer> tripCount = getTripCountMap();

		Integer maxCountOfTripDone = Collections.max(tripCount.values());

		return tripMoreDone(tripCount, maxCountOfTripDone);
	}

	private List<Trip> tripMoreDone(Map<Trip, Integer> tripCount, Integer max) {

		List<Trip> trips = new LinkedList<Trip>();

		for (Map.Entry<Trip, Integer> e : tripCount.entrySet()) {
			Trip key = e.getKey();
			Integer value = e.getValue();
			if (value.equals(max)) {
				trips.add(key);
			}
		}

		return trips;
	}

	public Double getAverageUseTime() {
		Double timeTotal = new Double(0);
		Integer quantity = 0;
		for (Trip trip : trips) {
			Double time = trip.getTime();
			timeTotal += time;
			quantity++;
		}
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
		for (Trip trip : trips) {
			bikes.add(trip.getBike());
		}
		return bikes;
	}

	private Map<Trip, Integer> getTripCountMap() {
		Map<Trip, Integer> tripCountMap = new HashMap<Trip, Integer>();
		int count = 0;
		for (Trip trip : trips) {
			if (!tripCountMap.containsKey(trip)) {
				count = getTripCount(trip);
				tripCountMap.put(trip, new Integer(count));
			}
		}
		return tripCountMap;
	}

	private int getTripCount(Trip tripToCount) {
		int count = 0;
		for (Trip trip : trips) {
			if (trip.equals(tripToCount)) {
				count++;
			}
		}
		return count;
	}

	private void validateEmptyTrips() throws TripNotFoundException {
		if (this.trips == null || this.trips.isEmpty()) {
			throw new TripNotFoundException("No hay recorridos para procesar");
		}
	}

}
