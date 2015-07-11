package ar.edu.tp.test;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import ar.edu.tp.domain.processor.StatisticalProcessorOnDemandStrategy;
import ar.edu.tp.domain.processor.StatisticalProcessorStrategy;
import ar.edu.tp.exception.DirectoryNotFoundException;
import ar.edu.tp.exception.TravelNotFoundException;

public class StatisticalProcessorOnDemandIntegrationTest {

	private static final String FOLDER_RESOURCES = "resourcesTests";
	private static final String FOLDER_DIAGRAMS = "diagrams";

	@Test
	public void processStatisticsShouldCreateYMLFile() throws Exception {
		File folderOutput = new File("salidaTests");
		if (!folderOutput.exists()) {
			folderOutput.mkdir();
		}
		StatisticalProcessorStrategy processorStrategy = new StatisticalProcessorOnDemandStrategy();
		processorStrategy.processStatistics(FOLDER_RESOURCES,folderOutput);

		File file = new File(folderOutput.getAbsolutePath());

		Assert.assertTrue(file.exists());
	}

	@Test
	public void processStatisticsShouldNotFoundTravels() throws TravelNotFoundException {
		try{	
			File folderOutput = new File("salidaTests");
			if (!folderOutput.exists()) {
				folderOutput.mkdir();
			}
			StatisticalProcessorStrategy processorStrategy = new StatisticalProcessorOnDemandStrategy();
			processorStrategy.processStatistics(FOLDER_DIAGRAMS,folderOutput);
		}catch(Exception e){
			e.printStackTrace();
		}	
	}

	@Test(expected = DirectoryNotFoundException.class)
	public void processStatisticsShouldNotFoundFolder() throws Exception {
		File folderOutput = new File("salidaTests");
		if (!folderOutput.exists()) {
			folderOutput.mkdir();
		}
		StatisticalProcessorStrategy processorStrategy = new StatisticalProcessorOnDemandStrategy();
		processorStrategy.processStatistics("empty",folderOutput);
	}
}
