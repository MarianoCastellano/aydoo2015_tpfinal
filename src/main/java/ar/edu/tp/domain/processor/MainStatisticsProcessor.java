package ar.edu.tp.domain.processor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.List;

import ar.edu.tp.domain.Bike;
import ar.edu.tp.domain.Travel;
import ar.edu.tp.domain.exporter.FileFormatExporter;
import ar.edu.tp.domain.exporter.YamlExporter;
import ar.edu.tp.domain.parser.ParserZipDeamon;
import ar.edu.tp.domain.parser.ParserZipOnDemand;

public class MainStatisticsProcessor {

	public static void main(String[] args) throws Exception {
		String folder = args[0];

		if (args.length > 1 && args[1].equalsIgnoreCase("demonio")) {
			System.out.println("Modo demonio.");
			Path folderPath = Paths.get(folder);

			WatchService watcher = folderPath.getFileSystem().newWatchService();
			folderPath.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);

			WatchKey watckKey = watcher.take();

			while (true) {
				List<WatchEvent<?>> events = watckKey.pollEvents();
				for (WatchEvent<?> event : events) {
					if (event.kind().equals(StandardWatchEventKinds.ENTRY_CREATE)) {

						List<String> paths = MainStatisticsProcessor.findPaths(folder);

						for (String path : paths) {
							String file = event.context().toString();
							int length = file.length();
							String fileName = file.substring(0, length - 4);

							ParserZipDeamon parserZipDeamon = new ParserZipDeamon(path);
							List<Travel> travels = parserZipDeamon.parse();
							StatisticalProcessor processor = new StatisticalProcessor(travels);
							generateStatistics(processor, fileName);
						}

					}
				}
			}
		} else {
			System.out.println("Modo On-demand.");

			List<String> paths = MainStatisticsProcessor.findPaths(folder);

			ParserZipOnDemand parserZipOnDemand = new ParserZipOnDemand(paths);
			List<Travel> travels = parserZipOnDemand.parse();
			StatisticalProcessor processor = new StatisticalProcessor(travels);

			String[] file = folder.split("/");
			String fileName = file[file.length - 1];

			generateStatistics(processor, fileName);
		}
	}

	private static void generateStatistics(StatisticalProcessor processor, String fileName) throws IOException {
		List<Bike> bikesUsedMoreTimes = processor.getBikesUsedMoreTimes();
		List<Bike> bikesUsedLessTimes = processor.getBikesUsedLessTimes();
		List<Travel> travelsMoreDone = processor.getTravelMoreDone();
		Double averageUseTime = processor.getAverageUseTime();

		FileFormatExporter yamlExporter = new YamlExporter(fileName, bikesUsedMoreTimes, bikesUsedLessTimes, travelsMoreDone, averageUseTime);
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