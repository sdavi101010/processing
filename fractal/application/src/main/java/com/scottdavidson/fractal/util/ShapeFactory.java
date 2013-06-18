package com.scottdavidson.fractal.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class ShapeFactory {

	/**
	 * Generates an equilateral triangle based on the provided base line. This
	 * method is very restrictive currently, requiring the baseline to be
	 * horizontal.
	 * 
	 * @param horizontalBaseline
	 * @return
	 */
	public static List<Line> createTriangle(Line horizontalBaseline) {

		// Error Check
		if (horizontalBaseline.getStart().getY() != horizontalBaseline.getEnd()
				.getY()) {
			throw new IllegalArgumentException(
					"Baseline must be horizontal to create a triangle.");
		}

		// Instantiate return list
		List<Line> triangleLines = new ArrayList<Line>();

		// Calculate the Mid Point
		Point midPoint = horizontalBaseline.calculateMidPoint();

		// Calculate the third point in the triangle w/ basic trig
		float apexPointYCoordinate = (float) (midPoint.getY() - (Math.sqrt(0.75
				* horizontalBaseline.calculateLength()
				* horizontalBaseline.calculateLength())));
		Point apexPoint = Point.newPoint(midPoint.getX(), apexPointYCoordinate);

		// Set the three points of the triangle
		triangleLines.add(horizontalBaseline);
		triangleLines.add(BasicLine.newBasicLine(apexPoint,
				horizontalBaseline.getStart()));
		triangleLines.add(BasicLine.newBasicLine(horizontalBaseline.getEnd(),
				apexPoint));

		// Return the list of lines
		return triangleLines;

	}

	// TODO Need to refactor the KochSnowflake to use the Triangle class
	public static Triangle createTriangle2(Line horizontalBaseline) {

		// Get the triangle edges
		List<Line> triangleLines = createTriangle(horizontalBaseline);

		// Instantiate a triangle from the lines
		Triangle returnTriangle = Triangle.newTriangle(triangleLines);

		// Return the triangle
		return returnTriangle;

	}

	/**
	 * This method accepts an equilateral triangle and generates the 4 inner
	 * equilateral triangles associated with the outer. This can be used for a
	 * Sierpinski Triangle.
	 * 
	 * @param triangle
	 * @return
	 */
	public static List<Triangle> generateInnerEquilateralTriangles(
			Triangle triangle) {

		// 1. Find base (this will be the segment w/ equivalent start and end
		// point y coordinates
		Line base = findBase(triangle);

		// 2. Find midpoint of base (mid1)
		Point mid1 = base.calculateMidPoint();

		// 3. Create base1 and base2 from mid1 and then tri1 and tri2 from these
		// bases
		Line base1 = BasicLine.newBasicLine(base.getStart(), mid1);
		Line base2 = BasicLine.newBasicLine(mid1, base.getStart());
		Triangle tri1 = createTriangle2(base1);
		Triangle tri2 = createTriangle2(base2);

		// 4. Create base3 from mid2 and mid3 (the non base edges)
		Point nonBase1 = findNonBasePoint(tri1);
		Point nonBase2 = findNonBasePoint(tri2);
		Line base3 = BasicLine.newBasicLine(nonBase1, nonBase2);

		// 5. Create tri3 from base3
		Triangle tri3 = createTriangle2(base3);

		// 6. Create tri4 from base3 and base3's end points to mid1
		Triangle tri4 = Triangle.newTriangle(Arrays.asList(base3,
				BasicLine.newBasicLine(base3.getStart(), mid1),
				BasicLine.newBasicLine(base3.getEnd(), mid1)));
		
		// 7. Return the triangles as a list
		return Arrays.asList(tri1, tri2,tri3,tri4);
		
	}

	protected static Line findBase(Triangle triangle) {

		for (Line edge : triangle.getEdges()) {
			if (edge.getStart().equalYCoordinate(edge.getEnd())) {
				return edge;
			}
		}
		return null;
	}

	protected static Point findNonBasePoint(Triangle triangle) {

		// Find the base
		Line base = findBase(triangle);

		// Get the points of the triangle
		TreeSet<Point> set = triangle.getPoints();

		// Remove the points that are part of the base which will leave the
		// point we want
		set.remove(base.getStart());
		set.remove(base.getEnd());

		// Return the remaining item in the list
		return set.first();
	}
}
