package ar.edu.tp.test;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import ar.edu.tp.domain.processor.StatisticalProcessorOnDemandStrategy;
import ar.edu.tp.domain.processor.StatisticalProcessorStrategy;
//import ar.edu.tp.exception.DirectoryNotFoundException;
//import ar.edu.tp.exception.TravelNotFoundException;

public class StatisticalProcessorOnDemandIntegrationTest {

	private static final String FOLDER = "resources";
	//private static final String FOLDER_DIAGARMS = "diagrams";

	@Test
	public void processStatisticsShouldCreateYMLFile() throws Exception {
		File folderOutput = new File("salida");
		if (!folderOutput.exists()) {
			folderOutput.mkdir();
		}
		StatisticalProcessorStrategy processorStrategy = new StatisticalProcessorOnDemandStrategy();
		processorStrategy.processStatistics(FOLDER,folderOutput);

		File file = new File(folderOutput.getAbsolutePath());

		Assert.assertTrue(file.exists());
	}

	//@Test(expected = TravelNotFoundException.class)
	public void processStatisticsShouldNotFoundTravels() throws Exception {
	//	StatisticalProcessorStrategy processorStrategy = new StatisticalProcessorOnDemandStrategy();
	//	processorStrategy.processStatistics(FOLDER_DIAGARMS);
	}

	//@Test(expected = DirectoryNotFoundException.class)
	public void processStatisticsShouldNotFoundFolder() throws Exception {
		//StatisticalProcessorStrategy processorStrategy = new StatisticalProcessorOnDemandStrategy();
		//processorStrategy.processStatistics("empty");
	}
}
