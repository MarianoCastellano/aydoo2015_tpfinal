package ar.edu.tp.test;

import org.junit.Assert;
import org.junit.Test;

import ar.edu.tp.domain.parser.TimeAndQuantityBike;

public class TimeAndQuantityBikeTest {
	@Test
	public void returnTimeUsed() {
		TimeAndQuantityBike timeBike = new TimeAndQuantityBike();

		Assert.assertEquals(timeBike.getTimeUsed(), 0, 0);

		timeBike.setTimeUsed(10);
		Assert.assertEquals(timeBike.getTimeUsed(), 10, 0);

		timeBike.setTimeUsed(25);
		Assert.assertEquals(timeBike.getTimeUsed(), 35, 0);

	}

	@Test
	public void returnQuantity() {
		TimeAndQuantityBike quantityBike = new TimeAndQuantityBike();

		Assert.assertEquals(quantityBike.getQuantityBike(), 1, 0);

		quantityBike.setQuantityBikeOneMore();
		Assert.assertEquals(quantityBike.getQuantityBike(), 2, 0);

		quantityBike.setQuantityBikeOneMore();
		Assert.assertEquals(quantityBike.getQuantityBike(), 3, 0);

	}

}
