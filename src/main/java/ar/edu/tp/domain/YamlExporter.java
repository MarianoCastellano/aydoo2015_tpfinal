package ar.edu.tp.domain;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class YamlExporter implements FileFormatExporter {

	private String fileName;
	private List<Bike> bikesUsedMoreTimes;
	private List<Bike> bikesUsedLessTimes;
	private Double averageUseTime;

	public YamlExporter(String fileName, List<Bike> bikesUsedMoreTimes, List<Bike> bikesUsedLessTimes, Double averageUseTime) {
		this.fileName = fileName;
		this.bikesUsedMoreTimes = bikesUsedMoreTimes;
		this.bikesUsedLessTimes = bikesUsedLessTimes;
		this.averageUseTime = averageUseTime;
	}

	@Override
	public void export() throws IOException {
		// TODO NANO: Tener en cuenta si ya existe un archivo con ese nombre.
		FileWriter file = new FileWriter(fileName.concat(getFormat()));
		PrintWriter printWriter = new PrintWriter(file);

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
		printWriter.write(String.format("Tiempo promedio de uso: %f", averageUseTime));
		printWriter.write(System.lineSeparator());

		printWriter.close();
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