package ar.edu.tp.domain.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import ar.edu.tp.domain.Bike;
import ar.edu.tp.domain.Location;
import ar.edu.tp.domain.Travel;
import ar.edu.tp.domain.User;

public class ParserZipOnDemand implements ParserZip {

	private List<String> paths;

	public ParserZipOnDemand(String folder) {
		this.paths = findPaths(folder);
	}

	@SuppressWarnings({ "resource", "unchecked" })
	public List<Travel> parse() throws IOException {
		List<Travel> travels = new ArrayList<Travel>();

		for (String path : paths) {
			ZipFile zipFile = new ZipFile(path);

			Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>) zipFile.entries();

			String cvsSplitBy = ",";

			while (entries.hasMoreElements()) {
				ZipEntry entry = entries.nextElement();
				InputStream stream = zipFile.getInputStream(entry);

				BufferedReader br = new BufferedReader(new InputStreamReader(stream));
				String line = "";
				Boolean isHeader = false;

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
						String time = row[8];

						User user = new User(userId);
						Bike bike = new Bike(bikeId);
						bike.use(user);
						Location origin = new Location(originStationId, originName, originDate);
						Location destination = new Location(destinationStationId, destinationName, destinationDate);
						Travel travel = new Travel(bike, origin, destination, time);
						travels.add(travel);
					}
				}
			}
		}
		return travels;
	}

	private List<String> findPaths(String folder) {
		List<String> paths = new ArrayList<String>();
		File file = new File(folder);
		File[] listFiles = file.listFiles();
		for (int i = 0; i < listFiles.length; i++) {
			File eachFile = listFiles[i];
			if (eachFile.getName().endsWith(".zip")) {
				paths.add(eachFile.getPath());
			}
		}
		return paths;
	}
}