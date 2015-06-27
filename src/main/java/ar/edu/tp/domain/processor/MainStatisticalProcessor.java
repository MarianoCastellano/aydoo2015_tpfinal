package ar.edu.tp.domain.processor;

public class MainStatisticalProcessor {

	public static void main(String[] args) throws Exception {
		String folder = args[0];

		StatisticalProcessorStrategy processorStrategy;

		if (args.length > 1 && args[1].equalsIgnoreCase("demonio")) {
			System.out.println("Modo demonio.");

			processorStrategy = new StatisticalProcessorDeamonStrategy();
			processorStrategy.processStatistics(folder);

		} else {
			System.out.println("Modo On-demand.");

			processorStrategy = new StatisticalProcessorOnDemandStrategy();
			processorStrategy.processStatistics(folder);
		}
	}

}