package com.scottdavidson.fractal.util;

import java.util.ArrayList;
import java.util.List;

public class ShapeFactory {

	/**
	 * Generates an equilateral triangle based on the provided base line. This
	 * method is very restrictive currently, requiring the baseline to be
	 * horizontal.
	 * 
	 * @param baseline
	 * @return
	 */
	public static <T> List<Line> createTriangle(Line baseline) {

		// Error Check
		if (baseline.getStart().getY() != baseline.getEnd().getY()) {
			throw new IllegalArgumentException(
					"Baseline must be horizontal to create a triangle.");
		}

		// Instantiate return list
		List<Line> triangleLines = new ArrayList<Line>();

		// Calculate the Mid Point
		Point midPoint = baseline.calculateMidPoint();

		// Calculate the third point in the triangle w/ basic trig
		float apexPointYCoordinate = (float) (midPoint.getY() - (Math.sqrt(0.75
				* baseline.calculateLength() * baseline.calculateLength())));
		Point apexPoint = Point.newPoint(midPoint.getX(), apexPointYCoordinate);

		// Add the three point
		triangleLines.add(baseline);
		triangleLines.add(Line.newLine(baseline.getStart(), apexPoint));
		triangleLines.add(Line.newLine(baseline.getEnd(), apexPoint));

		// Return the list of lines
		return triangleLines;

	}

}
