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

import aydoo.edu.tp.domain.Trip;

public class ParserZipDaemon implements ParserZip {

	private static final String CVS_SPLIT_BY = ";";

	@SuppressWarnings({ "resource", "unchecked" })
	public List<Trip> parse(String path) throws IOException {
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
		BufferedReader br = new BufferedReader(new InputStreamReader(stream));
		String line = "";
		Boolean isHeader = false;
		while ((line = br.readLine()) != null) {
			if (!isHeader) {
				isHeader = true;
			} else {
				String[] row = line.split(CVS_SPLIT_BY);
				try {
					String bikeId = row[1];
					String originStationId = row[3];
					String destinationStationId = row[6];
					String time = row[8];

					int bike = Integer.parseInt(bikeId);
					int originStation = Integer.parseInt(originStationId);
					int destinationStation = Integer.parseInt(destinationStationId);
					Trip trip = new Trip(bike, originStation, destinationStation, Double.valueOf(time));
					trips.add(trip);
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("Error al procesar el registro");
				}
			}
		}
	}
}