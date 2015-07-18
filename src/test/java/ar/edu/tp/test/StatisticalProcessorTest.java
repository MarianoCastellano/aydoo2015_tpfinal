package ar.edu.tp.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ar.edu.tp.domain.Bike;
import ar.edu.tp.domain.Location;
import ar.edu.tp.domain.Trip;
import ar.edu.tp.domain.User;
import ar.edu.tp.domain.processor.StatisticalProcessor;
import ar.edu.tp.exception.TripNotFoundException;

public class StatisticalProcessorTest {

	private List<Trip> trips = new ArrayList<Trip>();

	@Before
	public void init() throws IOException {
		createTrip("36680", "1524", "2013-01-02 07:49:52.937", "20", "ONCE", "2013-01-02 08:11:36.67", "32", "PARQUE PATRICIOS", "22");
		createTrip("68894", "1205", "2013-01-02 07:52:11.53", "20", "ONCE", "2013-01-02 08:23:01.123", "7", "PLAZA ROMA", "31");
		createTrip("69014", "986", "2013-01-02 07:53:56.14", "25", "PLAZA ALMAGRO", "2013-01-02 08:25:04.297", "19", "PLAZA VICENTE LOPEZ", "31");
		createTrip("72429", "986", "2013-01-02 07:54:55.187", "21", "PACIFICO", "2013-01-02 08:54:29.293", "5", "ADUANA", "60");
		createTrip("35910", "1274", "2013-01-02 07:55:22.483", "25", "PLAZA ALMAGRO", "2013-01-02 08:13:43.577", "10", "OBELISCO", "18");
		createTrip("60364", "1433", "2013-01-02 07:55:40.53", "21", "PACIFICO", "2013-01-02 08:14:10.687", "5", "ADUANA", "19");
		createTrip("55665", "1035", "2013-01-02 07:55:46.78", "21", "PACIFICO", "2013-01-02 07:57:12.06", "21", "PACIFICO", "1");
		createTrip("72167", "1522", "2013-01-02 07:56:08.36", "3", "RETIRO", "2013-01-02 08:03:51.39", "36", "EMMA DE LA BARRA", "8");
		createTrip("70759", "1442", "2013-01-02 07:56:56.95", "32", "PARQUE PATRICIOS", "2013-01-02 08:40:15.297", "21", "PACIFICO", "43");

		createTrip("36680", "1524", "2013-01-02 07:49:52.937", "20", "ONCE", "2013-01-02 08:11:36.67", "7", "PLAZA ROMA", "22");
		createTrip("68894", "1205", "2013-01-02 07:52:11.53", "20", "ONCE", "2013-01-02 08:23:01.123", "7", "PLAZA ROMA", "31");
		createTrip("69014", "986", "2013-01-02 07:53:56.14", "25", "PLAZA ALMAGRO", "2013-01-02 08:25:04.297", "19", "PLAZA VICENTE LOPEZ", "31");
		createTrip("72429", "986", "2013-01-02 07:54:55.187", "21", "PACIFICO", "2013-01-02 08:54:29.293", "5", "ADUANA", "60");
		createTrip("35910", "1274", "2013-01-02 07:55:22.483", "25", "PLAZA ALMAGRO", "2013-01-02 08:13:43.577", "10", "OBELISCO", "18");
		createTrip("60364", "1433", "2013-01-02 07:55:40.53", "21", "PACIFICO", "2013-01-02 08:14:10.687", "5", "ADUANA", "19");
		createTrip("55665", "1035", "2013-01-02 07:55:46.78", "21", "PACIFICO", "2013-01-02 07:57:12.06", "21", "PACIFICO", "1");
		createTrip("72167", "1522", "2013-01-02 07:56:08.36", "3", "RETIRO", "2013-01-02 08:03:51.39", "36", "EMMA DE LA BARRA", "8");
		createTrip("70759", "1442", "2013-01-02 07:56:56.95", "32", "PARQUE PATRICIOS", "2013-01-02 08:40:15.297", "21", "PACIFICO", "43");
	}

	private void createTrip(String userId, String bikeId, String originDate, String originStationId, String originName, String destinationDate,
			String destinationStationId, String destinationName, String time) {
		User user = new User(userId);
		Bike bike = new Bike(bikeId);
		bike.use(user);
		Location origin = new Location(originStationId, originName, originDate);
		Location destination = new Location(destinationStationId, destinationName, destinationDate);
		Trip trip = new Trip(bike, origin, destination, Double.valueOf(time));
		trips.add(trip);
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

		String originExpected = tripsMoreDone.get(0).getOrigin().getOriginStationId();
		String destinationExpected = tripsMoreDone.get(0).getDestination().getOriginStationId();

		Assert.assertEquals("21", originExpected);
		Assert.assertEquals("5", destinationExpected);
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