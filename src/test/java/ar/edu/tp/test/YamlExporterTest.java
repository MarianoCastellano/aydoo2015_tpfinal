package ar.edu.tp.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import ar.edu.tp.domain.Bike;
import ar.edu.tp.domain.Location;
import ar.edu.tp.domain.Trip;
import ar.edu.tp.domain.exporter.FileFormatExporter;
import ar.edu.tp.domain.exporter.YamlExporter;

public class YamlExporterTest {

	private final String FILE_NAME = "prueba";
	private final String EXTENSION_FILE = ".yml";

	@After
	public void clean() {
	File file = new File("prueba".concat(EXTENSION_FILE));
		file.delete();
	}

	@Test
	public void exportYamlShouldCreateAFileWithStatisticalInformation() throws Exception {
		List<Bike> bikesUsedMoreTimes = generateBikesUsedMoreTimes();
		List<Bike> bikesUsedLessTimes = generateBikesUsedLessTimes();
		Double averageUseTime = 0D;
		List<Trip> tipsMoreDone = generateTripMoreDone();

		FileFormatExporter yamlExporter = new YamlExporter(FILE_NAME, bikesUsedMoreTimes, bikesUsedLessTimes, tipsMoreDone, averageUseTime);
		yamlExporter.export();

		File file = new File("prueba".concat(EXTENSION_FILE));
		Assert.assertTrue(file.exists());
	}

	private List<Bike> generateBikesUsedLessTimes() {
		List<Bike> bikesUsedLessTimes = new ArrayList<Bike>();
		bikesUsedLessTimes.add(new Bike("2"));
		return bikesUsedLessTimes;
	}

	private List<Bike> generateBikesUsedMoreTimes() {
		List<Bike> bikesUsedMoreTimes = new ArrayList<Bike>();
		bikesUsedMoreTimes.add(new Bike("1"));
		return bikesUsedMoreTimes;
	}
	
	private List<Trip> generateTripMoreDone() {
		Location origin = new Location("12", "PALERMO", null );
		Location destiny = new Location("10", "RETIRO", null );
		List<Trip> tripMoreDone = new ArrayList<Trip>();
		tripMoreDone.add(new Trip(null, origin, destiny, "10"));
		return tripMoreDone;
	}
}