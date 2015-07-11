package ar.edu.tp.test;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.mockito.Mockito;

import ar.edu.tp.domain.Bike;
import ar.edu.tp.domain.Location;
import ar.edu.tp.domain.Travel;
import ar.edu.tp.domain.parser.ParserZipOnDemand;
import ar.edu.tp.domain.parser.TimeAndQuantityBike;
import ar.edu.tp.domain.processor.StatisticalProcessor;
import ar.edu.tp.exception.TravelNotFoundException;


public class StatisticalProcessorTest {
	
	private static final List<String> RECORRIDOS_2013_ZIP = Arrays.asList("resourcesTests/recorrido-2013.zip");
	private HashMap<Bike, TimeAndQuantityBike> mapBike;
	private HashMap<Travel, Integer> mapTravel;
	private StatisticalProcessor processor;

	@Before
	public void init() throws IOException {
		
		ParserZipOnDemand parserZipOnDemand = new ParserZipOnDemand(RECORRIDOS_2013_ZIP);
		parserZipOnDemand.parse();
		
		this.mapBike =parserZipOnDemand.getDeamon().getMapBike();
		this.mapTravel=parserZipOnDemand.getDeamon().getMapTravel() ;
		try {
			this.processor = new StatisticalProcessor(mapBike,mapTravel);
		} catch (TravelNotFoundException e) {
			e.printStackTrace();
		}
				
	}

	@Test
	public void getBikeUsedMoreTimesShouldGetBikeWithTwoUseTest() throws TravelNotFoundException {
		List<Bike> bikeUsedMoreTimes = this.processor.getBikesUsedMoreTimes();
		Assert.assertEquals(1, bikeUsedMoreTimes.size());
		Assert.assertEquals("986", bikeUsedMoreTimes.get(0).getBikeId());
	}

	@Test
	public void getBikesUsedLessTimesShouldGetBikesWithOneUseTest() throws TravelNotFoundException {
		List<Bike> bikeUsedLessTimes = this.processor.getBikesUsedLessTimes();
		Assert.assertEquals(7, bikeUsedLessTimes.size());
		Assert.assertTrue(bikeUsedLessTimes.contains(new Bike("1205")));
		Assert.assertTrue(bikeUsedLessTimes.contains(new Bike("1524")));
		Assert.assertTrue(bikeUsedLessTimes.contains(new Bike("1274")));
		Assert.assertTrue(bikeUsedLessTimes.contains(new Bike("1433")));
		Assert.assertTrue(bikeUsedLessTimes.contains(new Bike("1035")));
		Assert.assertTrue(bikeUsedLessTimes.contains(new Bike("1522")));
		Assert.assertTrue(bikeUsedLessTimes.contains(new Bike("1442")));
	}

	@Test
	public void getTravelMoreDoneShouldGetPacificoAduanaTest() throws TravelNotFoundException {
		List<Travel> travelMoreDone = this.processor.getTravelMoreDone();
		Location origin = new Location("21", "PACIFICO", null);
		Location destiny = new Location("5", "ADUANA", null);

		Assert.assertEquals(origin, travelMoreDone.get(0).getOrigin());
		Assert.assertEquals(destiny, travelMoreDone.get(0).getDestination());
	}

	@Test
	public void getAverageUseTimeShouldGetAverageUseTest() throws TravelNotFoundException {
		float averageUseTime = this.processor.getAverageUseTime();
		Assert.assertEquals(25.8888888889, averageUseTime, 0.0001);
	}

	@Test
	public void getBikeUsedMoreTimesShouldGetTwoBikeTest() throws TravelNotFoundException {
		List<Travel> travels = new ArrayList<Travel>();
		Travel travelOne = createMockTravelWithOriginId("1");
		Travel travelTwo = createMockTravelWithDestinationId("2");
		travels.add(travelOne);
		travels.add(travelTwo);

		List<Bike> bikeUsedMoreTimes = this.processor.getBikesUsedMoreTimes();
		Assert.assertEquals(1, bikeUsedMoreTimes.size());
	}

	@Test
	public void getBikesUsedLessTimesShouldGetTwoBikesTest() throws TravelNotFoundException {
		List<Travel> travels = new ArrayList<Travel>();
		Travel travelOne = createMockTravelWithOriginId("1");
		Travel travelTwo = createMockTravelWithDestinationId("2");
		travels.add(travelOne);
		travels.add(travelTwo);

		List<Bike> bikeUsedMoreTimes = this.processor.getBikesUsedMoreTimes();
		Assert.assertEquals(1, bikeUsedMoreTimes.size());
	}

	@Test
	public void checkAddingAtravelTest() throws TravelNotFoundException {
		HashMap<Travel, Integer> travels = new HashMap<Travel, Integer>();
		Location travelLocationOne = new Location("1","Caseros","Retiro");
		Location travelLocationTwo = new Location("12","Retiro","Once");
		Travel travelRoutOne = new Travel(travelLocationOne,travelLocationTwo);
		travels.put(travelRoutOne,5);

		Assert.assertEquals(5,(long)travels.get(travelRoutOne));
	}

	private Travel createMockTravelWithOriginId(String originStationId) {
		Travel travel = Mockito.mock(Travel.class);
		Mockito.when(travel.getOrigin()).thenReturn(new Location(originStationId,"Caseros","Retiro"));
		return travel;
	}

	private Travel createMockTravelWithDestinationId(String originStationId) {
		Travel travel = Mockito.mock(Travel.class);
		Mockito.when(travel.getDestination()).thenReturn(new Location(originStationId,"Retiro","Once"));
		return travel;
	}
}