package com.scottdavidson.fractal;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.scottdavidson.fractal.util.Line;
import com.scottdavidson.fractal.util.Point;

public class KochSnowflakeTest {

	@Test
	public void test() {

		List<Line> segments = KochSnowflake.transformSegment(Line.newLine(
				Point.newPoint(100, 100), Point.newPoint(130, 70)));
		segments.add(null);

	}

	@Test
	public void testCalculateMidPoint() {

		{
			Point midPoint = KochSnowflake.calculateMidPoint(Line.newLine(
					Point.newPoint(100, 100), Point.newPoint(130, 70)));
			assertTrue(midPoint.equals(Point.newPoint(115, 85)));
		}

		{
			Point midPoint = KochSnowflake.calculateMidPoint(Line.newLine(
					Point.newPoint(100, 100), Point.newPoint(30, 170)));
			assertTrue(midPoint.equals(Point.newPoint(65, 135)));
		}

	}
	
	@Test
	public void testAdditionalSegments() {
		
		List<Line> additionalSegments = 
				KochSnowflake.additionalSegments(Line.newLine(
					Point.newPoint(100, 100), Point.newPoint(130, 70)));
		
		
	}
}
