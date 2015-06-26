package ar.edu.tp.domain;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.tp.domain.parser.ParserZipOnDemand;

public class MainStatisticsProcessor {

	private static final String FILE_NAME = "resultado";

	public static void main(String[] args) throws Exception {

		List<String> paths = MainStatisticsProcessor.findPaths(args[0]);

		if (args.length > 1 && args[1].equalsIgnoreCase("demonio")) {
			System.out.println("Modo demonio.");

			for (String path : paths) {
				StatisticalProcessor processor = new StatisticalProcessor(new ParserZipDeamon(path));
				generateStatistics(processor);
			}

		} else {
			System.out.println("Modo On-demand.");

			StatisticalProcessor processor = new StatisticalProcessor(new ParserZipOnDemand(paths));
			generateStatistics(processor);
		}
	}

	private static void generateStatistics(StatisticalProcessor processor) throws IOException {
		List<Bike> bikesUsedMoreTimes = processor.getBikesUsedMoreTimes();
		List<Bike> bikesUsedLessTimes = processor.getBikesUsedLessTimes();
		List<Travel> travelsMoreDone = processor.getTravelMoreDone();
		Double averageUseTime = processor.getAverageUseTime();

		FileFormatExporter yamlExporter = new YamlExporter(FILE_NAME, bikesUsedMoreTimes, bikesUsedLessTimes, travelsMoreDone, averageUseTime);
		yamlExporter.export();
	}

	public static List<String> findPaths(String folder) {
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