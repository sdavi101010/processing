package com.scottdavidson.fractal.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class LineTest {

	@Test
	public void testQuadrant() {

		// Upper Left
		KochSnowflakeLine upperLeft = KochSnowflakeLine.newLine(Point.newPoint(267, 484),
				Point.newPoint(333, 369), Point.newPoint(400, 426));
		assertEquals(KochSnowflakeLine.Quadrant.UPPER_LEFT, upperLeft.getQuadrant());

		// Upper Right
		KochSnowflakeLine upperRight = KochSnowflakeLine.newLine(Point.newPoint(533, 484),
				Point.newPoint(467, 369), Point.newPoint(400, 426));
		assertEquals(KochSnowflakeLine.Quadrant.UPPER_RIGHT, upperRight.getQuadrant());

	}

}
