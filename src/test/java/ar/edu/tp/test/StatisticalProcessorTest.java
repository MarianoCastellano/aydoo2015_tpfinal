package ar.edu.tp.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import aydoo.edu.tp.domain.Bike;
import aydoo.edu.tp.domain.Trip;
import aydoo.edu.tp.domain.processor.StatisticalProcessor;
import aydoo.edu.tp.exception.TripNotFoundException;

public class StatisticalProcessorTest {

	private List<Trip> trips = new ArrayList<Trip>();

	@Before
	public void init() throws IOException {
		createTrip(1524, 20, 32, 22);
		createTrip(1205, 20, 7, 31);
		createTrip(986, 25, 19, 31);
		createTrip(986, 21, 5, 60);
		createTrip(1274, 25, 10, 18);
		createTrip(1433, 21, 5, 19);
		createTrip(1035, 21, 21, 1);
		createTrip(1522, 3, 36, 8);
		createTrip(1442, 32, 21, 43);

		createTrip(1524, 20, 7, 22);
		createTrip(1205, 20, 7, 31);
		createTrip(986, 25, 19, 31);
		createTrip(986, 21, 5, 60);
		createTrip(1274, 25, 10, 18);
		createTrip(1433, 21, 5, 19);
		createTrip(1035, 21, 21, 1);
		createTrip(1522, 3, 36, 8);
		createTrip(1442, 32, 21, 43);
	}

	private void createTrip(int bikeId, int originStationId, int destinationStationId, double time) {
		Trip trip = new Trip(bikeId, originStationId, destinationStationId, time);
		trips.add(trip);
	}

	@Test
	public void getBikeUsedMoreTimesShouldGetBikeWithTwoUseTest() throws TripNotFoundException {
		StatisticalProcessor processor = new StatisticalProcessor();
		processor.addTripsAndProcess(trips);
		List<Bike> bikeUsedMoreTimes = processor.getBikesUsedMoreTimes();
		Assert.assertEquals(1, bikeUsedMoreTimes.size());
		Assert.assertEquals(986, bikeUsedMoreTimes.get(0).getBikeId());
	}

	@Test
	public void getBikesUsedLessTimesShouldGetBikesWithOneUseTest() throws TripNotFoundException {
		StatisticalProcessor processor = new StatisticalProcessor();
		processor.addTripsAndProcess(trips);
		List<Bike> bikeUsedLessTimes = processor.getBikesUsedLessTimes();
		Assert.assertEquals(7, bikeUsedLessTimes.size());
		Assert.assertTrue(bikeUsedLessTimes.contains(new Bike(1205)));
		Assert.assertTrue(bikeUsedLessTimes.contains(new Bike(1524)));
		Assert.assertTrue(bikeUsedLessTimes.contains(new Bike(1274)));
		Assert.assertTrue(bikeUsedLessTimes.contains(new Bike(1433)));
		Assert.assertTrue(bikeUsedLessTimes.contains(new Bike(1035)));
		Assert.assertTrue(bikeUsedLessTimes.contains(new Bike(1522)));
		Assert.assertTrue(bikeUsedLessTimes.contains(new Bike(1442)));
	}

	@Test
	public void getTripsMoreDoneShouldGetPacificoAduanaTest() throws TripNotFoundException {
		StatisticalProcessor processor = new StatisticalProcessor();
		processor.addTripsAndProcess(trips);
		List<Trip> tripsMoreDone = processor.getTripsMoreDone();

		Integer originExpected = tripsMoreDone.get(0).getOrigin();
		Integer destinationExpected = tripsMoreDone.get(0).getDestination();

		Assert.assertEquals(new Integer(21), originExpected);
		Assert.assertEquals(new Integer(5), destinationExpected);
	}

	@Test
	public void getAverageUseTimeShouldGetAverageUseTest() throws TripNotFoundException {
		StatisticalProcessor processor = new StatisticalProcessor();
		processor.addTripsAndProcess(trips);
		Double averageUseTime = processor.getAverageUseTime();
		Assert.assertEquals(25.8888888889, averageUseTime, 0.0001);
	}

	@Test
	public void getBikeUsedMoreTimesShouldGetTwoBikeTest() throws TripNotFoundException {
		List<Trip> trip = new ArrayList<Trip>();
		Trip tripBikeOne = createMockTripWithBikeId(1);
		Trip tripBikeTwo = createMockTripWithBikeId(2);
		trip.add(tripBikeOne);
		trip.add(tripBikeTwo);

		StatisticalProcessor processor = new StatisticalProcessor();
		processor.addTripsAndProcess(trip);
		List<Bike> bikeUsedMoreTimes = processor.getBikesUsedMoreTimes();
		Assert.assertEquals(2, bikeUsedMoreTimes.size());
	}

	@Test
	public void getBikesUsedLessTimesShouldGetTwoBikesTest() throws TripNotFoundException {
		List<Trip> trips = new ArrayList<Trip>();
		Trip tripBikeOne = createMockTripWithBikeId(1);
		Trip tripBikeTwo = createMockTripWithBikeId(2);
		trips.add(tripBikeOne);
		trips.add(tripBikeTwo);

		StatisticalProcessor processor = new StatisticalProcessor();
		processor.addTripsAndProcess(trips);
		List<Bike> bikeUsedMoreTimes = processor.getBikesUsedMoreTimes();
		Assert.assertEquals(2, bikeUsedMoreTimes.size());
	}

	@Test
	public void getAverageUseTimeShouldGetOnlyOneAverageTest() throws TripNotFoundException {
		List<Trip> trips = new ArrayList<Trip>();
		Trip tripBikeOne = createMockTripWithBikeIdAndTime(1, 10D);
		trips.add(tripBikeOne);

		StatisticalProcessor processor = new StatisticalProcessor();
		processor.addTripsAndProcess(trips);
		Double averageUseTime = processor.getAverageUseTime();
		Assert.assertEquals(10, averageUseTime, 0.0001);
	}

	@Test(expected = TripNotFoundException.class)
	public void addTripsAndProcessShouldThrowTripNotFoundException() throws TripNotFoundException {
		List<Trip> trips = new ArrayList<Trip>();
		StatisticalProcessor processor = new StatisticalProcessor();
		processor.addTripsAndProcess(trips);
	}

	private Trip createMockTripWithBikeId(int bikeId) {
		Trip trip = Mockito.mock(Trip.class);
		Mockito.when(trip.getBike()).thenReturn(bikeId);
		return trip;
	}

	private Trip createMockTripWithBikeIdAndTime(int bikeId, Double time) {
		Trip trip = Mockito.mock(Trip.class);
		Mockito.when(trip.getBike()).thenReturn(bikeId);
		Mockito.when(trip.getTime()).thenReturn(time);
		return trip;
	}

}