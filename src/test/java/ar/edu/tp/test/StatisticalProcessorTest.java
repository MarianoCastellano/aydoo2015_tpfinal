package ar.edu.tp.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ar.edu.tp.domain.Bike;
import ar.edu.tp.domain.Location;
import ar.edu.tp.domain.Trip;
import ar.edu.tp.domain.parser.ParserZipOnDemand;
import ar.edu.tp.domain.processor.StatisticalProcessor;
import ar.edu.tp.exception.TripNotFoundException;

public class StatisticalProcessorTest {

	private static final List<String> RECORRIDOS_2013_ZIP = Arrays.asList("resources/recorrido-2013.zip");
	private List<Trip> trips;

	@Before
	public void init() throws IOException {
		ParserZipOnDemand parserZipOnDemand = new ParserZipOnDemand(RECORRIDOS_2013_ZIP);
		trips = parserZipOnDemand.parse();
	}

	@Test
	public void getBikeUsedMoreTimesShouldGetBikeWithTwoUseTest() throws TripNotFoundException {
		StatisticalProcessor processor = new StatisticalProcessor(trips);
		List<Bike> bikeUsedMoreTimes = processor.getBikesUsedMoreTimes();
		Assert.assertEquals(1, bikeUsedMoreTimes.size());
		Assert.assertEquals("986", bikeUsedMoreTimes.get(0).getBikeId());
	}

	@Test
	public void getBikesUsedLessTimesShouldGetBikesWithOneUseTest() throws TripNotFoundException {
		StatisticalProcessor processor = new StatisticalProcessor(trips);
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
	public void getTripsMoreDoneShouldGetPacificoAduanaTest() throws TripNotFoundException {
		StatisticalProcessor processor = new StatisticalProcessor(trips);
		List<Trip> tripsMoreDone = processor.getTripsMoreDone();
		Location origin = new Location("21", "PACIFICO", null);
		Location destiny = new Location("5", "ADUANA", null);

		Assert.assertEquals(origin, tripsMoreDone.get(0).getOrigin());
		Assert.assertEquals(destiny, tripsMoreDone.get(0).getDestination());
	}

	@Test
	public void getAverageUseTimeShouldGetAverageUseTest() throws TripNotFoundException {
		StatisticalProcessor processor = new StatisticalProcessor(trips);
		Double averageUseTime = processor.getAverageUseTime();
		Assert.assertEquals(25.8888888889, averageUseTime, 0.0001);
	}

	@Test(expected = TripNotFoundException.class)
	public void getBikeUsedMoreTimesShouldGetCeroBikesTest() throws TripNotFoundException {
		new StatisticalProcessor(new ArrayList<Trip>());
	}

	@Test
	public void getBikeUsedMoreTimesShouldGetTwoBikeTest() throws TripNotFoundException {
		List<Trip> trip = new ArrayList<Trip>();
		Trip tripBikeOne = createMockTripWithBikeId("1");
		Trip tripBikeTwo = createMockTripWithBikeId("2");
		trip.add(tripBikeOne);
		trip.add(tripBikeTwo);

		StatisticalProcessor processor = new StatisticalProcessor(trip);
		List<Bike> bikeUsedMoreTimes = processor.getBikesUsedMoreTimes();
		Assert.assertEquals(2, bikeUsedMoreTimes.size());
	}

	@Test
	public void getBikesUsedLessTimesShouldGetTwoBikesTest() throws TripNotFoundException {
		List<Trip> trips = new ArrayList<Trip>();
		Trip tripBikeOne = createMockTripWithBikeId("1");
		Trip tripBikeTwo = createMockTripWithBikeId("2");
		trips.add(tripBikeOne);
		trips.add(tripBikeTwo);

		StatisticalProcessor processor = new StatisticalProcessor(trips);
		List<Bike> bikeUsedMoreTimes = processor.getBikesUsedMoreTimes();
		Assert.assertEquals(2, bikeUsedMoreTimes.size());
	}

	@Test
	public void getAverageUseTimeShouldGetOnlyOneAverageTest() throws TripNotFoundException {
		List<Trip> trips = new ArrayList<Trip>();
		Trip tripBikeOne = createMockTripWithBikeIdAndTime("1", 10D);
		trips.add(tripBikeOne);

		StatisticalProcessor processor = new StatisticalProcessor(trips);
		Double averageUseTime = processor.getAverageUseTime();
		Assert.assertEquals(10, averageUseTime, 0.0001);
	}

	private Trip createMockTripWithBikeId(String bikeId) {
		Trip trip = Mockito.mock(Trip.class);
		Mockito.when(trip.getBike()).thenReturn(new Bike(bikeId));
		return trip;
	}

	private Trip createMockTripWithBikeIdAndTime(String bikeId, Double time) {
		Trip trip = Mockito.mock(Trip.class);
		Mockito.when(trip.getBike()).thenReturn(new Bike(bikeId));
		Mockito.when(trip.getTime()).thenReturn(time);
		return trip;
	}

}