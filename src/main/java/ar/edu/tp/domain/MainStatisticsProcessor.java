package ar.edu.tp.domain;

import java.util.List;

import ar.edu.tp.domain.parser.ParserZipOnDeamon;

public class MainStatisticsProcessor {

	private static final String PATH_FILE_NAME = "resultado.yml";

	public static void main(String[] args) throws Exception {
		if (args.length > 1 && args[1].equalsIgnoreCase("demonio")) {
			System.out.println("Modo demonio.");
		} else {
			StatisticalProcessor processor = new StatisticalProcessor(new ParserZipOnDeamon(args[0]));
			List<Bike> bikesUsedMoreTimes = processor.getBikesUsedMoreTimes();
			List<Bike> bikesUsedLessTimes = processor.getBikesUsedLessTimes();
			Double averageUseTime = processor.getAverageUseTime();

			FileFormatExporter yamlExporter = new YamlExporter(PATH_FILE_NAME, bikesUsedMoreTimes, bikesUsedLessTimes, averageUseTime);
			yamlExporter.export();
		}
	}
}