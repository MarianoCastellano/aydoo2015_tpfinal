package ar.edu.tp.domain.processor;

import java.io.IOException;
import java.util.List;

import ar.edu.tp.domain.Bike;
import ar.edu.tp.domain.Travel;
import ar.edu.tp.domain.exporter.FileFormatExporter;
import ar.edu.tp.domain.exporter.YamlExporter;
import ar.edu.tp.domain.parser.ParserZipOnDemand;

public class StatisticalProcessorOnDemandStrategy implements StatisticalProcessorStrategy {

	@Override
	public void processStatistics(String folder) throws Exception {
		FileManager fileManager = new FileManager(folder);
		fileManager.validateFolder();

		List<String> paths = fileManager.findPaths();

		ParserZipOnDemand parserZipOnDemand = new ParserZipOnDemand(paths);
		List<Travel> travels = parserZipOnDemand.parse();
		StatisticalProcessor processor = new StatisticalProcessor(travels);
		String fileName = fileManager.extractNameFromFolder(folder);
		generateStatistics(processor, fileName);
	}

	private static void generateStatistics(StatisticalProcessor processor, String fileName) throws IOException {
		List<Bike> bikesUsedMoreTimes = processor.getBikesUsedMoreTimes();
		List<Bike> bikesUsedLessTimes = processor.getBikesUsedLessTimes();
		List<Travel> travelsMoreDone = processor.getTravelMoreDone();
		Double averageUseTime = processor.getAverageUseTime();

		FileFormatExporter yamlExporter = new YamlExporter(fileName, bikesUsedMoreTimes, bikesUsedLessTimes, travelsMoreDone, averageUseTime);
		yamlExporter.export();
	}
}