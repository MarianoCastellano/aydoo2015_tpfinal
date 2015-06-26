package ar.edu.tp.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import ar.edu.tp.domain.Bike;
import ar.edu.tp.domain.FileFormatExporter;
import ar.edu.tp.domain.YamlExporter;

public class YamlExporterTest {

	private final String PATH_FILE_NAME = "prueba.yml";

	@After
	public void clean() {
		File file = new File(PATH_FILE_NAME);
		file.delete();
	}

	@Test
	public void exportYamlShouldCreateAFileWithStatisticalInformation() throws Exception {
		List<Bike> bikesUsedMoreTimes = generateBikesUsedMoreTimes();
		List<Bike> bikesUsedLessTimes = generateBikesUsedLessTimes();
		Double averageUseTime = 0D;

		FileFormatExporter yamlExporter = new YamlExporter(PATH_FILE_NAME, bikesUsedMoreTimes, bikesUsedLessTimes, averageUseTime);
		yamlExporter.export();

		File file = new File(PATH_FILE_NAME);
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
}