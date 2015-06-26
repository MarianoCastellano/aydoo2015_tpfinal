package ar.edu.tp.domain;

import java.util.List;

import ar.edu.tp.domain.parser.ParserZipOnDeamon;

public class Main {

	private static final String PATH_FILE_NAME = "prueba.yml";
	private static final String RECORRIDOS_2013_ZIP = "resources";

	public static void main(String[] args) throws Exception {
		StatisticalProcessor processor = new StatisticalProcessor(new ParserZipOnDeamon(RECORRIDOS_2013_ZIP));
		List<Bike> bikesUsedMoreTimes = processor.getBikesUsedMoreTimes();
		List<Bike> bikesUsedLessTimes = processor.getBikesUsedLessTimes();
		Double averageUseTime = processor.getAverageUseTime();

		FileFormatExporter yamlExporter = new YamlExporter(PATH_FILE_NAME, bikesUsedMoreTimes, bikesUsedLessTimes, averageUseTime);
		yamlExporter.export();
	}

}