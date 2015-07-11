package ar.edu.tp.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import ar.edu.tp.domain.Bike;
import ar.edu.tp.domain.Location;
import ar.edu.tp.domain.Travel;
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
		List<Bike> bikeLongerUsed=this.bikeLongerUsed();
	
		float averageUseTime = 0;
		List<Travel> travelsMoreDone = generateTravelsMoreDone();

		File folderOutput = new File("salidaTests");
		if (!folderOutput.exists()) {
			folderOutput.mkdir();
		}
	
		FileFormatExporter yamlExporter = new YamlExporter(folderOutput,FILE_NAME, bikesUsedMoreTimes, bikesUsedLessTimes,bikeLongerUsed ,travelsMoreDone, averageUseTime,0);
		yamlExporter.export(10);
		String[]  files=folderOutput.list();
		int quantityFileInFolder=files.length;
		File file = new File(folderOutput.getAbsolutePath().concat("/prueba".concat(String.valueOf(quantityFileInFolder)).concat(EXTENSION_FILE)));
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
	
	private List<Bike> bikeLongerUsed() {
		List<Bike> bikesUsedMoreTimes = new ArrayList<Bike>();
		bikesUsedMoreTimes.add(new Bike("5"));
		return bikesUsedMoreTimes;
	}
	private List<Travel> generateTravelsMoreDone() {
		Location origin = new Location("12", "PALERMO", null );
		Location destiny = new Location("10", "RETIRO", null );
		List<Travel> travelsMoreDone = new ArrayList<Travel>();
		travelsMoreDone.add(new Travel(origin, destiny));
		return travelsMoreDone;
	}
}