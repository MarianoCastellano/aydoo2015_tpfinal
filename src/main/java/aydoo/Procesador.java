package aydoo;

import java.io.File;

import ar.edu.tp.domain.processor.StatisticalProcessorDeamonStrategy;
import ar.edu.tp.domain.processor.StatisticalProcessorOnDemandStrategy;
import ar.edu.tp.domain.processor.StatisticalProcessorStrategy;

public class Procesador {

	public static void main(String[] args) throws Exception {
		String folder = args[0];
		File folderOutput = new File("salida");
		if (!folderOutput.exists()) {
			folderOutput.mkdir();
		}
		
		if (folderOutput.exists()){
			proccessCommandUserSelection(args, folder, folderOutput);
		}else
			System.out.println("ERROR - No se puede crear el directorio de salida");
		
	}

	private static void proccessCommandUserSelection(String[] args,
			String folder, File folderOutput) throws Exception {
		StatisticalProcessorStrategy processorStrategy;

		if (args.length > 1 && args[1].equalsIgnoreCase("demonio")) {
			System.out.println("Modo demonio.");

			processorStrategy = new StatisticalProcessorDeamonStrategy();
			processorStrategy.processStatistics(folder,folderOutput);

		} else {
			System.out.println("Modo On-demand.");

			processorStrategy = new StatisticalProcessorOnDemandStrategy();
			processorStrategy.processStatistics(folder,folderOutput);
		}
	}
}