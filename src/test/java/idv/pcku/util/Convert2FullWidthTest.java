package idv.pcku.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class Convert2FullWidthTest {

	@Test
	public void testConvertFromString() {
		assertEquals("０１２３ｔｅｓｔ測　試！－．",Convert2FullWidth.convertFromString("0123test測 試!-."));
	}

}
