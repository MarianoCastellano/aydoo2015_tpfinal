package ar.edu.tp.domain.processor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
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
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

public class StatisticalProcessorDeamonStrategy implements
		StatisticalProcessorStrategy {
	private long startTime;
	private File folderOutput;
	private final String fileName = "salida";
	private String folderIn;

	@Override
	public void processStatistics(String folder, File folderOutput)
			throws Exception {
		FileManager fileManager = new FileManager(folder);
		fileManager.validateFolder();
		this.folderOutput = folderOutput;
		this.folderIn = folder;
		Path folderPath = Paths.get(folder);

		WatchService watcher = folderPath.getFileSystem().newWatchService();
		folderPath.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);

		WatchKey watckKey = null;

		while (true) {
			watckKey = watcher.take();
			listenEvents(fileManager, watckKey);
			if (!watckKey.reset()) {
				break;
			}
		}
	}

	private void listenEvents(FileManager fileManager, WatchKey key)
			throws IOException, TravelNotFoundException {

		Kind<?> kind = null;

		try {
 			Thread.sleep(5000);
 		} catch (InterruptedException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
	
		for (WatchEvent<?> watchEvent : key.pollEvents()) {

			kind = watchEvent.kind();
			if (OVERFLOW == kind) {
				continue;
			} else if (ENTRY_CREATE == kind) {
				String extendfile = watchEvent
						.context()
						.toString()
						.substring(watchEvent.context().toString().length() - 3);
				@SuppressWarnings("unchecked")
				WatchEvent<Path> ev = (WatchEvent<Path>) watchEvent;
				Path dir = (Path)key.watchable();
				Path fullPath = dir.resolve(ev.context());
				
				System.out.println("Processing File name: "+	fullPath);
			
				if (extendfile.equals("zip"))
					proccessStatisticsByPaths(fullPath.toString());
				else
					System.out.println("File is not zip");

			}
		}

	}

	private void proccessStatisticsByPaths(String path)
			throws IOException, TravelNotFoundException {
		this.startTime = System.currentTimeMillis();

		
		ParserZipDeamon parserZipDeamon = new ParserZipDeamon(path);
		parserZipDeamon.parse();
		HashMap<Bike, TimeAndQuantityBike> mapBike = parserZipDeamon
				.getMapBike();
		HashMap<Travel, Integer> mapTravel = parserZipDeamon.getMapTravel();
		StatisticalProcessor processor = new StatisticalProcessor(mapBike,
				mapTravel);
		generateStatistics(processor, fileName);

	}

	private void generateStatistics(StatisticalProcessor processor,
			String fileName) throws IOException {
		List<Bike> bikesUsedMoreTimes = processor.getBikesUsedMoreTimes();
		List<Bike> bikesUsedLessTimes = processor.getBikesUsedLessTimes();
		List<Travel> travelsMoreDone = processor.getTravelMoreDone();
		List<Bike> bikeLongerUsed = processor.getBikeLongerUsed();
		float averageUseTime = processor.getAverageUseTime();
		float valueMaxTimeUsedBike = processor.getValueMaxTimeUsedBike();

		FileFormatExporter yamlExporter = new YamlExporter(folderOutput,
				this.fileName, bikesUsedMoreTimes, bikesUsedLessTimes,
				bikeLongerUsed, travelsMoreDone, averageUseTime,
				valueMaxTimeUsedBike);

		long endTime = System.currentTimeMillis() - this.startTime;
		yamlExporter.export(endTime);
	}
}
