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

	private HashMap<Bike,TimeAndQuantityBike> mapBike;
	private HashMap <Travel,Integer> mapTravel;
	private int valueMaxBike;
	private int valueMinBike;
	private float valueMaxTimeUsedBike;
	private float totalTimeAverage;

	

	public StatisticalProcessor(HashMap<Bike,TimeAndQuantityBike> mapBike,HashMap <Travel,Integer> mapTravel) throws TravelNotFoundException {
		this.mapBike=mapBike;
		this.mapTravel=mapTravel;
		this.calculateMaxAndMinBikeAndTime();
		//validateEmptyTravels();//////////ver esto validar que los mapas no esten vacios
	}
	
	
	public float getAverageUseTime() {
		return totalTimeAverage;
	}
	public List<Bike> getBikesUsedMoreTimes() {
		List <Bike> listBike=new LinkedList <Bike>();
		for (Map.Entry<Bike, TimeAndQuantityBike> mapa : this.mapBike.entrySet()) {
			TimeAndQuantityBike timeAndQuantityBike=mapa.getValue();
			if (timeAndQuantityBike.getQuantityBike()==this.valueMaxBike)
				listBike.add(mapa.getKey());
		}

		return listBike;
	}

	public List<Bike> getBikesUsedLessTimes() {
		List <Bike> listBike=new LinkedList <Bike>();
		for (Map.Entry<Bike, TimeAndQuantityBike> mapa : this.mapBike.entrySet()) {
			TimeAndQuantityBike timeAndQuantityBike=mapa.getValue();
			if (timeAndQuantityBike.getQuantityBike()==this.valueMinBike)
				listBike.add(mapa.getKey());
		}

		return listBike;
	}

	public List<Bike> getBikeLongerUsed() {
		List <Bike> listBike=new LinkedList <Bike>();
		for (Map.Entry<Bike, TimeAndQuantityBike> mapa : this.mapBike.entrySet()) {
			TimeAndQuantityBike timeAndQuantityBike=mapa.getValue();
			if (timeAndQuantityBike.getTimeUsed()==this.valueMaxTimeUsedBike)
				listBike.add(mapa.getKey());
		}

		return listBike;
	}
	
	public float getValueMaxTimeUsedBike() {
		return valueMaxTimeUsedBike;
	}


	public List<Travel> getTravelMoreDone() {
		List <Travel> listTravel=new LinkedList <Travel>();
			int countMaxTravel=this.travelMoreDone();
		for (Map.Entry<Travel, Integer> e : this.mapTravel.entrySet()) {
			Travel travel=e.getKey();
			 if (countMaxTravel==e.getValue()) {
				listTravel.add(travel);
			}
		}
		return listTravel;
	}

	private int travelMoreDone() {
int countMaxTravel=0;
		

		for (Map.Entry<Travel, Integer> e : this.mapTravel.entrySet()) {
			
			 if (countMaxTravel<e.getValue()) {
				countMaxTravel=e.getValue();
			}
		}

		return countMaxTravel;
	}

	private void calculateMaxAndMinBikeAndTime() {/////////ver bien nombre
		float timeTotal = 0;
		int quantity = 0;
		boolean estadoPrimeraVuelta = false;

		for (Map.Entry<Bike, TimeAndQuantityBike> mapa : this.mapBike.entrySet()) {
			TimeAndQuantityBike timeAndQuantityBike=mapa.getValue();
			timeTotal+=timeAndQuantityBike.getTimeUsed();
			quantity+=timeAndQuantityBike.getQuantityBike();
			
			if (!estadoPrimeraVuelta) {
				
				this.valueMaxBike = this.valueMinBike=timeAndQuantityBike.getQuantityBike();
				this.valueMaxTimeUsedBike=timeAndQuantityBike.getTimeUsed();
				estadoPrimeraVuelta = true;
				

				
			} else {
				if (this.valueMaxBike < timeAndQuantityBike.getQuantityBike())
					this.valueMaxBike = timeAndQuantityBike.getQuantityBike();

				if (this.valueMinBike > timeAndQuantityBike.getQuantityBike())
					this.valueMinBike = timeAndQuantityBike.getQuantityBike();
				
				if (this.valueMaxTimeUsedBike<timeAndQuantityBike.getTimeUsed())
					this.valueMaxTimeUsedBike=timeAndQuantityBike.getTimeUsed();
			}

		}
		this.totalTimeAverage=timeTotal / quantity;
	}

	/*private List<Bike> findBikesByFrecuency(Map<Bike, Integer> useOfBike, Integer frecuency) {
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
*/
}
