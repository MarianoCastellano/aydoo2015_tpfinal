package aydoo.edu.tp.domain.exporter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import aydoo.edu.tp.domain.Bike;
import aydoo.edu.tp.domain.Trip;

public class YamlExporter implements FileFormatExporter {

	private String fileName;
	private List<Bike> bikesUsedMoreTimes;
	private List<Bike> bikesUsedLessTimes;
	private Double averageUseTime;
	private List<Trip> tipsMoreDone;

	public YamlExporter(String fileName, List<Bike> bikesUsedMoreTimes, List<Bike> bikesUsedLessTimes, List<Trip> tipsMoreDone,
			Double averageUseTime) {
		this.fileName = fileName;
		this.bikesUsedMoreTimes = bikesUsedMoreTimes;
		this.bikesUsedLessTimes = bikesUsedLessTimes;
		this.averageUseTime = averageUseTime;
		this.tipsMoreDone = tipsMoreDone;
	}

	@Override
	public void export() throws IOException {
		File file = new File(fileName.concat(getFormat()));
		FileWriter fileWriter = new FileWriter(file);
		PrintWriter printWriter = new PrintWriter(fileWriter);

		printWriter.write(String.format("Bicicletas mas usadas: "));
		for (Bike bike : bikesUsedMoreTimes) {
			printValue(printWriter, bike);
		}

		printWriter.write(System.lineSeparator());

		printWriter.write(String.format("Bicicletas menos usadas: "));
		for (Bike bike : bikesUsedLessTimes) {
			printValue(printWriter, bike);
		}

		printWriter.write(System.lineSeparator());

		printWriter.write(String.format("Recorrido mas realizado: "));
		for (Trip trip : tipsMoreDone) {
			printTrip(printWriter, trip);
		}

		printWriter.write(System.lineSeparator());
		printWriter.write(String.format("Tiempo promedio de uso: %f", averageUseTime));
		printWriter.write(System.lineSeparator());

		printWriter.close();

		System.out.println(String.format("Archivo de salida creado: %s", file.getAbsoluteFile()));
	}

	private void printTrip(PrintWriter printWriter, Trip trip) {
		printWriter.write(System.lineSeparator());
		printWriter.write(String.format("%5s id origen: %s", " ", trip.getOrigin()));
		printWriter.write(System.lineSeparator());
		printWriter.write(String.format("%5s id destino: %s", " ", trip.getDestination()));
	}

	@Override
	public String getFormat() {
		return ".yml";
	}

	private void printValue(PrintWriter printWriter, Bike bike) {
		printWriter.write(System.lineSeparator());
		printWriter.write(String.format("%5s id: %s", " ", bike.getBikeId()));
	}

}