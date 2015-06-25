package ar.edu.tp.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ar.edu.tp.domain.Bike;
import ar.edu.tp.domain.StatisticalProcessor;
import ar.edu.tp.domain.parser.ParserZipDeamon;

public class StatisticalProcessorTest {

	private static final String RECORRIDOS_2013_ZIP = "resources";

	@Test
	public void getBikeUsedMoreTimesShouldGetBikeWithTwoUse() throws Exception {
		StatisticalProcessor processor = new StatisticalProcessor(new ParserZipDeamon(RECORRIDOS_2013_ZIP));
		List<Bike> bikeUsedMoreTimes = processor.getBikesUsedMoreTimes();
		Assert.assertEquals(1, bikeUsedMoreTimes.size());
		Assert.assertEquals("986", bikeUsedMoreTimes.get(0).getBikeId());
	}
	
	@Test
	public void getBikesUsedLessTimesShouldGetBikesWithOneUse() throws Exception {
		StatisticalProcessor processor = new StatisticalProcessor(new ParserZipDeamon(RECORRIDOS_2013_ZIP));
		List<Bike> bikeUsedLessTimes = processor.getBikesUsedLessTimes();
		Assert.assertEquals(7, bikeUsedLessTimes.size());
		Assert.assertTrue(bikeUsedLessTimes.contains(new Bike("1205")));	
		Assert.assertTrue(bikeUsedLessTimes.contains(new Bike("1524")));
		Assert.assertTrue(bikeUsedLessTimes.contains(new Bike("1274")));
		Assert.assertTrue(bikeUsedLessTimes.contains(new Bike("1433")));
		Assert.assertTrue(bikeUsedLessTimes.contains(new Bike("1035")));
		Assert.assertTrue(bikeUsedLessTimes.contains(new Bike("1522")));
		Assert.assertTrue(bikeUsedLessTimes.contains(new Bike("1442")));
	}
}