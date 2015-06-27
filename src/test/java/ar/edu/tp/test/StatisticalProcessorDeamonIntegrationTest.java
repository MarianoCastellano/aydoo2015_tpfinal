package ar.edu.tp.test;

import org.junit.Test;

import ar.edu.tp.domain.processor.StatisticalProcessorDeamonStrategy;
import ar.edu.tp.domain.processor.StatisticalProcessorStrategy;
import ar.edu.tp.exception.DirectoryNotFoundException;

public class StatisticalProcessorDeamonIntegrationTest {

	@Test(expected = DirectoryNotFoundException.class)
	public void processStatisticsShouldNotFoundFolder() throws Exception {
		StatisticalProcessorStrategy processorStrategy = new StatisticalProcessorDeamonStrategy();
		processorStrategy.processStatistics("empty");
	}
}