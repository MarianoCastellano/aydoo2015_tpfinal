package ar.edu.tp.domain.processor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashMap;
import java.util.List;

import ar.edu.tp.domain.Bike;
import ar.edu.tp.domain.Travel;
import ar.edu.tp.domain.exporter.FileFormatExporter;
import ar.edu.tp.domain.exporter.YamlExporter;
import ar.edu.tp.domain.parser.ParserZipDeamon;
import ar.edu.tp.domain.parser.TimeAndQuantityBike;
import ar.edu.tp.exception.TravelNotFoundException;

public class StatisticalProcessorDeamonStrategy implements
		StatisticalProcessorStrategy {
	private long startTime;
	private File folderOutput;
	private final String  fileName="salida";


	@Override
	public void processStatistics(String folder, File folderOutput)
			throws Exception {
		FileManager fileManager = new FileManager(folder);
		fileManager.validateFolder();
		this.folderOutput = folderOutput;
		Path folderPath = Paths.get(folder);

		WatchService watcher = folderPath.getFileSystem().newWatchService();
		folderPath.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);

		WatchKey watckKey = watcher.take();

		while (true) {
			listenEvents(fileManager, watckKey);
		}
	}

	private void listenEvents(FileManager fileManager, WatchKey watckKey)
			throws IOException, TravelNotFoundException {
		List<WatchEvent<?>> events = watckKey.pollEvents();
		for (WatchEvent<?> event : events) {
			if (event.kind().equals(StandardWatchEventKinds.ENTRY_CREATE)) {
				proccessStatisticsByPaths(fileManager, event.context()
						.toString());
			}
		}
	}

	private void proccessStatisticsByPaths(FileManager fileManager,
			String fileZip) throws IOException, TravelNotFoundException {
		this.startTime = System.currentTimeMillis();

		List<String> paths = fileManager.findPaths();

		for (String path : paths) {
			
			ParserZipDeamon parserZipDeamon = new ParserZipDeamon(path);
			parserZipDeamon.parse();
			HashMap<Bike, TimeAndQuantityBike> mapBike = parserZipDeamon
					.getMapBike();
			HashMap<Travel, Integer> mapTravel = parserZipDeamon.getMapTravel();
			StatisticalProcessor processor = new StatisticalProcessor(mapBike,
					mapTravel);
			generateStatistics(processor, fileName);
		}
	}

	private void generateStatistics(StatisticalProcessor processor,
			String fileName) throws IOException {
		List<Bike> bikesUsedMoreTimes = processor.getBikesUsedMoreTimes();
		List<Bike> bikesUsedLessTimes = processor.getBikesUsedLessTimes();
		List<Travel> travelsMoreDone = processor.getTravelMoreDone();
		List<Bike> bikeLongerUsed = processor.getBikeLongerUsed();
		float averageUseTime = processor.getAverageUseTime();
		float valueMaxTimeUsedBike = processor.getValueMaxTimeUsedBike();

		FileFormatExporter yamlExporter = new YamlExporter(folderOutput,this.fileName,
				bikesUsedMoreTimes, bikesUsedLessTimes, bikeLongerUsed,
				travelsMoreDone, averageUseTime, valueMaxTimeUsedBike);

		long endTime = System.currentTimeMillis() - this.startTime;
		yamlExporter.export(endTime);
	}
}