package ar.edu.tp.test;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import ar.edu.tp.domain.processor.StatisticalProcessorOnDemand;
import ar.edu.tp.domain.processor.StatisticalProcessorStrategy;
import ar.edu.tp.exception.DirectoryNotFoundException;

public class StatisticalProcessorOnDemandIntegrationTest {

	private static final String FOLDER = "resources";

	@Test
	public void processStatisticsShouldCreateYMLFile() throws Exception {
		StatisticalProcessorStrategy processorStrategy = new StatisticalProcessorOnDemand();
		processorStrategy.processStatistics(FOLDER);

		File file = new File(FOLDER);

		Assert.assertTrue(file.exists());
	}

	@Test(expected = DirectoryNotFoundException.class)
	public void processStatisticsShouldNotFoundFolder() throws Exception {
		StatisticalProcessorStrategy processorStrategy = new StatisticalProcessorOnDemand();
		processorStrategy.processStatistics("empty");
	}
}