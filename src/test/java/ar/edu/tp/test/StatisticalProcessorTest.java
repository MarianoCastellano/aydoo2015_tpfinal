package ar.edu.tp.test;

import org.junit.Assert;
import org.junit.Test;

import ar.edu.tp.domain.Bike;
import ar.edu.tp.domain.StatisticalProcessor;
import ar.edu.tp.domain.parser.ParserZipDeamon;

public class StatisticalProcessorTest {

	private static final String RECORRIDOS_2013_ZIP = "resources";

	@Test
	public void getBikeUsedMoreTimesShouldGetBikeWithOneUse() throws Exception {
		StatisticalProcessor processor = new StatisticalProcessor(new ParserZipDeamon(RECORRIDOS_2013_ZIP));
		Bike bikeUsedMoreTimes = processor.getBikeUsedMoreTimes();
		Assert.assertEquals("986", bikeUsedMoreTimes.getBikeId());
	}
}