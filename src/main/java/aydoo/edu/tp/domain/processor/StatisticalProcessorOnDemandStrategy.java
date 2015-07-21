package aydoo.edu.tp.domain.processor;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import aydoo.edu.tp.domain.Bike;
import aydoo.edu.tp.domain.Trip;
import aydoo.edu.tp.domain.exporter.FileFormatExporter;
import aydoo.edu.tp.domain.exporter.YamlExporter;
import aydoo.edu.tp.domain.parser.ParserZipOnDemand;

public class StatisticalProcessorOnDemandStrategy implements StatisticalProcessorStrategy {

	@Override
	public void processStatistics(String folder) throws Exception {
		FileManager fileManager = new FileManager(folder);
		fileManager.validateFolder();

		List<String> paths = fileManager.findPaths();

		ParserZipOnDemand parserZipOnDemand = new ParserZipOnDemand();
		StatisticalProcessor processor = new StatisticalProcessor();
		for (String path : paths) {
			List<Trip> trips = new LinkedList<Trip>();
			trips = parserZipOnDemand.parse(path);
			processor.addTripsAndProcess(trips);
		}
		String fileName = fileManager.extractNameFromFolder(folder);
		generateStatistics(processor, fileName);
	}

	private static void generateStatistics(StatisticalProcessor processor, String fileName) throws IOException {
		List<Bike> bikesUsedMoreTimes = processor.getBikesUsedMoreTimes();
		List<Bike> bikesUsedLessTimes = processor.getBikesUsedLessTimes();
		List<Trip> tripsMoreDone = processor.getTripsMoreDone();
		Double averageUseTime = processor.getAverageUseTime();

		FileFormatExporter yamlExporter = new YamlExporter(fileName, bikesUsedMoreTimes, bikesUsedLessTimes, tripsMoreDone, averageUseTime);
		yamlExporter.export();
	}
}