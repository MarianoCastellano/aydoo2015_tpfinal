package ar.edu.tp.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ar.edu.tp.domain.parser.ParserZip;

public class StatisticalProcessor {

	private List<Travel> travels;

	public StatisticalProcessor(ParserZip parser) throws Exception {
		travels = parser.parse();
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

}