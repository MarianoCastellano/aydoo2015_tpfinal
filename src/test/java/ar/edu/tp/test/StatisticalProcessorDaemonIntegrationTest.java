package ar.edu.tp.test;

import org.junit.Test;

import ar.edu.tp.domain.processor.StatisticalProcessorDaemonStrategy;
import ar.edu.tp.domain.processor.StatisticalProcessorStrategy;
import ar.edu.tp.exception.DirectoryNotFoundException;

public class StatisticalProcessorDaemonIntegrationTest {

	@Test(expected = DirectoryNotFoundException.class)
	public void processStatisticsShouldNotFoundFolder() throws Exception {
		StatisticalProcessorStrategy processorStrategy = new StatisticalProcessorDaemonStrategy();
		processorStrategy.processStatistics("empty");
	}
}