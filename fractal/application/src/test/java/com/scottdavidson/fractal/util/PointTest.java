package com.scottdavidson.fractal.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class PointTest {

	@Test
	public void testConstructionInteger() {
		
		Point point = Point.newPoint(55, 44);
		assertEquals(new Float(55), (Float)point.getX());
		assertEquals(new Float(44), (Float)point.getY());
		
	}

}
