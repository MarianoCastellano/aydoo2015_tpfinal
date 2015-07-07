package ar.edu.tp.test;

import org.junit.Test;
/*
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.mockito.Mockito;

import ar.edu.tp.domain.Bike;
import ar.edu.tp.domain.Location;
import ar.edu.tp.domain.Travel;
import ar.edu.tp.domain.parser.ParserZipOnDemand;
import ar.edu.tp.domain.processor.StatisticalProcessor;
import ar.edu.tp.exception.TravelNotFoundException;
*/

public class StatisticalProcessorTest {
	
	@Test
	public void test(){
		
	}

	/*
	private static final List<String> RECORRIDOS_2013_ZIP = Arrays.asList("resources/recorrido-2013.zip");
	private List<Travel> travels;

	@Before
	public void init() throws IOException {
		ParserZipOnDemand parserZipOnDemand = new ParserZipOnDemand(RECORRIDOS_2013_ZIP);
		parserZipOnDemand.parse();
	}

	/*@Test
	public void getBikeUsedMoreTimesShouldGetBikeWithTwoUseTest() throws TravelNotFoundException {
		StatisticalProcessor processor = new StatisticalProcessor(travels);
		List<Bike> bikeUsedMoreTimes = processor.getBikesUsedMoreTimes();
		Assert.assertEquals(1, bikeUsedMoreTimes.size());
		Assert.assertEquals("986", bikeUsedMoreTimes.get(0).getBikeId());
	}

	@Test
	public void getBikesUsedLessTimesShouldGetBikesWithOneUseTest() throws TravelNotFoundException {
		StatisticalProcessor processor = new StatisticalProcessor(travels);
		List<Bike> bikeUsedLessTimes = processor.getBikesUsedLessTimes();
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
		StatisticalProcessor processor = new StatisticalProcessor(travels);
		List<Travel> travelMoreDone = processor.getTravelMoreDone();
		Location origin = new Location("21", "PACIFICO", null);
		Location destiny = new Location("5", "ADUANA", null);

		Assert.assertEquals(origin, travelMoreDone.get(0).getOrigin());
		Assert.assertEquals(destiny, travelMoreDone.get(0).getDestination());
	}

	@Test
	public void getAverageUseTimeShouldGetAverageUseTest() throws TravelNotFoundException {
		StatisticalProcessor processor = new StatisticalProcessor(travels);
		Double averageUseTime = processor.getAverageUseTime();
		Assert.assertEquals(25.8888888889, averageUseTime, 0.0001);
	}

	@Test(expected = TravelNotFoundException.class)
	public void getBikeUsedMoreTimesShouldGetCeroBikesTest() throws TravelNotFoundException {
		new StatisticalProcessor(new ArrayList<Travel>());
	}

	@Test
	public void getBikeUsedMoreTimesShouldGetTwoBikeTest() throws TravelNotFoundException {
		List<Travel> travels = new ArrayList<Travel>();
		Travel travelBikeOne = createMockTravelWithBikeId("1");
		Travel travelBikeTwo = createMockTravelWithBikeId("2");
		travels.add(travelBikeOne);
		travels.add(travelBikeTwo);

		StatisticalProcessor processor = new StatisticalProcessor(travels);
		List<Bike> bikeUsedMoreTimes = processor.getBikesUsedMoreTimes();
		Assert.assertEquals(2, bikeUsedMoreTimes.size());
	}

	@Test
	public void getBikesUsedLessTimesShouldGetTwoBikesTest() throws TravelNotFoundException {
		List<Travel> travels = new ArrayList<Travel>();
		Travel travelBikeOne = createMockTravelWithBikeId("1");
		Travel travelBikeTwo = createMockTravelWithBikeId("2");
		travels.add(travelBikeOne);
		travels.add(travelBikeTwo);

		StatisticalProcessor processor = new StatisticalProcessor(travels);
		List<Bike> bikeUsedMoreTimes = processor.getBikesUsedMoreTimes();
		Assert.assertEquals(2, bikeUsedMoreTimes.size());
	}

	@Test
	public void getAverageUseTimeShouldGetOnlyOneAverageTest() throws TravelNotFoundException {
		List<Travel> travels = new ArrayList<Travel>();
		Travel travelBikeOne = createMockTravelWithBikeIdAndTime("1", 10D);
		travels.add(travelBikeOne);

		StatisticalProcessor processor = new StatisticalProcessor(travels);
		Double averageUseTime = processor.getAverageUseTime();
		Assert.assertEquals(10, averageUseTime, 0.0001);
	}

	private Travel createMockTravelWithBikeId(String bikeId) {
		Travel travel = Mockito.mock(Travel.class);
		Mockito.when(travel.getBike()).thenReturn(new Bike(bikeId));
		return travel;
	}

	private Travel createMockTravelWithBikeIdAndTime(String bikeId, Double time) {
		Travel travel = Mockito.mock(Travel.class);
		Mockito.when(travel.getBike()).thenReturn(new Bike(bikeId));
		Mockito.when(travel.getTime()).thenReturn(time);
		return travel;
	}
*/
}