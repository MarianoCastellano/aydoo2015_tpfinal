package aydoo.edu.tp.domain.processor;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import aydoo.edu.tp.domain.Bike;
import aydoo.edu.tp.domain.Trip;
import aydoo.edu.tp.exception.TripNotFoundException;

public class StatisticalProcessor {

	private Map<Integer, Integer> useOfBike;
	private Map<Trip, Integer> tripCount;
	private Double timeTotal;
	private Double quantity;

	public StatisticalProcessor() throws TripNotFoundException {
		useOfBike = new HashMap<Integer, Integer>();
		tripCount = new HashMap<Trip, Integer>();
		timeTotal = new Double(0);
		quantity = new Double(0);
	}

	public void addTripsAndProcess(List<Trip> trips) throws TripNotFoundException {
		validateEmptyTrips(trips);
		for (Trip trip : trips) {
			upgradeAverageUseTime(trip);
			upgradeTripCountMap(trip);
			upgradeUseOfBikes(trip);
		}
	}

	public List<Bike> getBikesUsedMoreTimes() {
		Integer moreTimes = Collections.max(useOfBike.values());

		return findBikesByFrecuency(useOfBike, moreTimes);
	}

	public List<Bike> getBikesUsedLessTimes() {
		Integer lessTimes = Collections.min(useOfBike.values());

		return findBikesByFrecuency(useOfBike, lessTimes);
	}

	public List<Trip> getTripsMoreDone() {
		Integer maxCountOfTripDone = Collections.max(tripCount.values());

		return tripMoreDone(tripCount, maxCountOfTripDone);
	}

	public Double getAverageUseTime() {
		return timeTotal / quantity;
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

	private void upgradeAverageUseTime(Trip trip) {
		Double time = trip.getTime();
		timeTotal += time;
		quantity++;
	}

	private List<Bike> findBikesByFrecuency(Map<Integer, Integer> useOfBike2, Integer frecuency) {
		List<Bike> bikes = new LinkedList<Bike>();
		for (Map.Entry<Integer, Integer> e : useOfBike2.entrySet()) {
			Integer key = e.getKey();
			Integer value = e.getValue();
			if (value.equals(frecuency)) {
				bikes.add(new Bike(key));
			}
		}
		return bikes;
	}

	private void upgradeUseOfBikes(Trip trip) {
		int times = 1;
		if (useOfBike.containsKey(trip.getBike())) {
			times = times + useOfBike.get(trip.getBike());
		}
		useOfBike.put(trip.getBike(), times);
	}

	private void upgradeTripCountMap(Trip trip) {
		int count = 1;
		if (tripCount.containsKey(trip)) {
			count = count + tripCount.get(trip);
		}
		tripCount.put(trip, count);
	}

	private void validateEmptyTrips(List<Trip> trips) throws TripNotFoundException {
		if (trips == null || trips.isEmpty()) {
			throw new TripNotFoundException("No hay recorridos para procesar");
		}
	}

}