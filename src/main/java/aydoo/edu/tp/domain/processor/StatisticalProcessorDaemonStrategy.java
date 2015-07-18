package aydoo.edu.tp.domain.processor;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

import aydoo.edu.tp.domain.Bike;
import aydoo.edu.tp.domain.Trip;
import aydoo.edu.tp.domain.exporter.FileFormatExporter;
import aydoo.edu.tp.domain.exporter.YamlExporter;
import aydoo.edu.tp.domain.parser.ParserZipDaemon;
import aydoo.edu.tp.exception.TripNotFoundException;

public class StatisticalProcessorDaemonStrategy implements StatisticalProcessorStrategy {

	@Override
	public void processStatistics(String folder) throws Exception {
		FileManager fileManager = new FileManager(folder);
		fileManager.validateFolder();

		Path folderPath = Paths.get(folder);

		WatchService watcher = folderPath.getFileSystem().newWatchService();
		folderPath.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);

		WatchKey watckKey = watcher.take();

		while (true) {
			listenEvents(fileManager, watckKey);
		}
	}

	private void listenEvents(FileManager fileManager, WatchKey watckKey) throws IOException, TripNotFoundException {
		List<WatchEvent<?>> events = watckKey.pollEvents();
		for (WatchEvent<?> event : events) {
			if (event.kind().equals(StandardWatchEventKinds.ENTRY_CREATE)) {
				proccessStatisticsByPaths(fileManager, event.context().toString());
			}
		}
	}

	private void proccessStatisticsByPaths(FileManager fileManager, String fileZip) throws IOException, TripNotFoundException {
		List<String> paths = fileManager.findPaths();

		for (String path : paths) {
			String fileName = fileManager.extractNameFromZipFile(fileZip);
			ParserZipDaemon parserZipDaemon = new ParserZipDaemon(path);
			List<Trip> trips = parserZipDaemon.parse();
			StatisticalProcessor processor = new StatisticalProcessor(trips);
			generateStatistics(processor, fileName);
		}
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