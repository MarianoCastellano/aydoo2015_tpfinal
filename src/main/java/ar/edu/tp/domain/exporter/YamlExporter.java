package ar.edu.tp.domain.exporter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import ar.edu.tp.domain.Bike;
import ar.edu.tp.domain.Travel;

public class YamlExporter implements FileFormatExporter {

	private String fileName;
	private List<Bike> bikesUsedMoreTimes;
	private List<Bike> bikesUsedLessTimes;
	private List<Bike> bikeLongerUsed;
	private float averageUseTime;
	private List<Travel> travelsMoreDone;
	private float valueMaxTimeUsedBike;

	public YamlExporter(String fileName, List<Bike> bikesUsedMoreTimes, List<Bike> bikesUsedLessTimes, List<Bike> bikeLongerUsed,List<Travel> travelsMoreDone,
			float averageUseTime,float valueMaxTimeUsedBike) {
		this.fileName = fileName;
		this.bikesUsedMoreTimes = bikesUsedMoreTimes;
		this.bikesUsedLessTimes = bikesUsedLessTimes;
		this.bikeLongerUsed=bikeLongerUsed;
		this.averageUseTime = averageUseTime;
		this.travelsMoreDone = travelsMoreDone;
		this.valueMaxTimeUsedBike=valueMaxTimeUsedBike;
	}

	@Override
	public void export(long timeEjecution) throws IOException {
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
		for (Travel travel : travelsMoreDone) {
			printTravel(printWriter, travel);
		}

		printWriter.write(System.lineSeparator());
		printWriter.write(String.format("Tiempo promedio de uso: %f", averageUseTime));
		printWriter.write(System.lineSeparator());
		
		printWriter.write(String.format("Bicicletas utilizada mas tiempo : "));
		for (Bike bike : this.bikeLongerUsed) {
			printValue(printWriter, bike);
		}
		printWriter.write(System.lineSeparator());
		
		printWriter.write(String.format("Tiempo de la bicicleta mas utilizada : %f", this.valueMaxTimeUsedBike*60));

printWriter.write(System.lineSeparator());
		
		printWriter.write(String.format("Tiempo de ejecution en segundos: %f", (float)timeEjecution/1000));

		
		printWriter.close();

		System.out.println(String.format("Archivo de salida creado: %s", file.getAbsoluteFile()));
	}

	private void printTravel(PrintWriter printWriter, Travel travel) {
		printWriter.write(System.lineSeparator());
		printWriter.write(String.format("%5s id origen: %s", " ", travel.getOrigin().getOriginStationId()));
		printWriter.write(System.lineSeparator());
		printWriter.write(String.format("%5s id destino: %s", " ", travel.getDestination().getOriginStationId()));
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