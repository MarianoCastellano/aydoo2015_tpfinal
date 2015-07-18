package aydoo.edu.tp.domain.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import aydoo.edu.tp.domain.Bike;
import aydoo.edu.tp.domain.Location;
import aydoo.edu.tp.domain.Trip;
import aydoo.edu.tp.domain.User;

public class ParserZipDaemon implements ParserZip {

	private String path;

	public ParserZipDaemon(String path) {
		this.path = path;
	}

	@SuppressWarnings({ "resource", "unchecked" })
	public List<Trip> parse() throws IOException {
		List<Trip> trips = new ArrayList<Trip>();

		ZipFile zipFile = new ZipFile(path);

		Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>) zipFile.entries();

		while (entries.hasMoreElements()) {
			ZipEntry entry = entries.nextElement();
			InputStream stream = zipFile.getInputStream(entry);
			proccesTrip(trips, stream);
		}
		return trips;
	}

	private void proccesTrip(List<Trip> trips, InputStream stream) throws IOException {
		String cvsSplitBy = ";";
		BufferedReader br = new BufferedReader(new InputStreamReader(stream));
		String line = "";
		Boolean isHeader = false;

		while ((line = br.readLine()) != null) {
			if (!isHeader) {
				isHeader = true;
			} else {
				String[] row = line.split(cvsSplitBy);
				try {
					String userId = row[0];
					String bikeId = row[1];
					String originDate = row[2];
					String originStationId = row[3];
					String originName = row[4];
					String destinationDate = row[5];
					String destinationStationId = row[6];
					String destinationName = row[7];
					String time = row[8];

					User user = new User(userId);
					Bike bike = new Bike(bikeId);
					bike.use(user);
					Location origin = new Location(originStationId, originName, originDate);
					Location destination = new Location(destinationStationId, destinationName, destinationDate);
					Trip trip = new Trip(bike, origin, destination, Double.valueOf(time));
					trips.add(trip);
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("Error al procesar el registro");
				}
			}
		}
	}
}