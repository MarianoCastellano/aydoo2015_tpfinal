package ar.edu.tp.domain.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import ar.edu.tp.domain.Bike;
import ar.edu.tp.domain.Location;
import ar.edu.tp.domain.Travel;
import ar.edu.tp.domain.User;

public class ParserZipDeamon implements ParserZip {

	private String path;
	private HashMap<Bike, TimeAndQuantityBike> mapBike;
	private HashMap<Travel, Integer> mapTravel;

	public HashMap<Bike, TimeAndQuantityBike> getMapBike() {
		return mapBike;
	}

	public HashMap<Travel, Integer> getMapTravel() {
		return mapTravel;
	}

	public ParserZipDeamon(String path) {
		this.path = path;
	}

	public void setPath(String path) {
		this.path = path;

	}

	@SuppressWarnings({ "resource", "unchecked" })
	public void parse() throws IOException {

		ZipFile zipFile = new ZipFile(path);

		Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>) zipFile
				.entries();

		while (entries.hasMoreElements()) {
			ZipEntry entry = entries.nextElement();
			InputStream stream = zipFile.getInputStream(entry);
			proccesTravel(stream);
		}

	}

	private void allocateMapBike(Bike bike, String time) {
		if (mapBike.containsKey(bike)) {

			TimeAndQuantityBike timeAndQuantityBike = mapBike.get(bike);
			timeAndQuantityBike.setQuantityBikeOneMore();
			timeAndQuantityBike.setTimeUsed(Float.valueOf(time));

			mapBike.put(bike, timeAndQuantityBike);
		} else {
			TimeAndQuantityBike timeAndQuantityBike = new TimeAndQuantityBike();
			mapBike.put(bike, timeAndQuantityBike);
		}

	}

	private void allocateTravel(String originDate, String originStationId,
			String originName, String destinationDate,
			String destinationStationId, String destinationName) {
		Location origin = new Location(originStationId, originName, originDate);
		Location destination = new Location(destinationStationId,
				destinationName, destinationDate);

		Travel travel = new Travel(origin, destination);

		if (mapTravel.containsKey(travel)) {

			int count = mapTravel.get(travel);
			count++;

			mapTravel.put(travel, count);
		} else {

			mapTravel.put(travel, 1);
		}

	}

	private void proccesTravel(InputStream stream) throws IOException {
		String cvsSplitBy = ";";
		BufferedReader br = new BufferedReader(new InputStreamReader(stream));
		String line = "";
		Boolean isHeader = false;
		this.mapBike = new HashMap<Bike, TimeAndQuantityBike>();
		this.mapTravel = new HashMap<Travel, Integer>();

		while ((line = br.readLine()) != null) {
			if (!isHeader) {
				isHeader = true;
			} else {
				String[] row = line.split(cvsSplitBy);
				String userId = row[0];
				String bikeId = row[1];
				String originDate = row[2];
				String originStationId = row[3];
				String originName = row[4];
				String destinationDate = row[5];
				String destinationStationId = row[6];
				String destinationName = row[7];
				String time;

				if (row.length == 8)
					time = "0";
				else
					time = row[8];

				User user = new User(userId);
				Bike bike = new Bike(bikeId);
				bike.use(user);

				this.allocateMapBike(bike, time);
				this.allocateTravel(originDate, originStationId, originName,
						destinationDate, destinationStationId, destinationName);
			}
		}

	}

}
