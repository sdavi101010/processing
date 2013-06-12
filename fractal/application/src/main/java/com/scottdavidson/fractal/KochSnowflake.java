package com.scottdavidson.fractal;

import java.util.ArrayList;
import java.util.List;

import com.scottdavidson.fractal.util.Line;
import com.scottdavidson.fractal.util.Point;

public class KochSnowflake {

	private final Point paneCenterPoint;

	public KochSnowflake newKochSnowflake(Point paneCenterPoint) {
		return new KochSnowflake(paneCenterPoint);
	}

	/**
	 * Single constructor for KochSnowflake.
	 * 
	 * @param paneCenterPoint
	 */
	protected KochSnowflake(Point paneCenterPoint) {
		this.paneCenterPoint = paneCenterPoint;
	}

	// private static Logger LOGGER = Logger.getLogger(KochSnowflake.class);

	/**
	 * Transforms the provide lineSegment into 4 others - 1/3 segments for first
	 * and third and then the fractal edges of the new triangle.
	 * 
	 * @param lineSegment
	 * @return
	 */
	public List<Line> transformSegment(Line lineSegment) {

		// Create the return list
		List<Line> returnList = new ArrayList<Line>(4);

		// Calculate the trisection segment length (x and y direction)
		float xSegmentLength = (lineSegment.getEnd().getX() - lineSegment
				.getStart().getX()) / new Float(3.0);
		float ySegmentLength = (lineSegment.getEnd().getY() - lineSegment
				.getStart().getY()) / new Float(3.0);

		// Generate the outer segments
		returnList.add(Line.newLine(lineSegment.getStart(), Point.newPoint(
				lineSegment.getStart().getX() + xSegmentLength, lineSegment
						.getStart().getY() + ySegmentLength)));

		returnList.add(Line.newLine(
				Point.newPoint(lineSegment.getStart().getX() + 2
						* xSegmentLength, lineSegment.getStart().getY() + 2
						* ySegmentLength), lineSegment.getEnd()));

		// Generate the 2 segements that form an equilateral triangle
		List<Line> additionalSegments = additionalSegments(Line.newLine(Point
				.newPoint(lineSegment.getStart().getX() + xSegmentLength,
						lineSegment.getStart().getY() + ySegmentLength), Point
				.newPoint(lineSegment.getStart().getX() + 2 * xSegmentLength,
						lineSegment.getStart().getY() + 2 * ySegmentLength)));

		// Add the additional segments to the return list
		returnList.add(additionalSegments.get(0));
		returnList.add(additionalSegments.get(1));

		// // Trisect the line and save the outer lines
		// //
		// // Work x coordinates first
		// Point start = lineSegment.getStart();
		// Point end = lineSegment.getEnd();
		// List<Float> orderedXCoords;
		// if ( start.getX() <= end.getX()) {
		// orderedXCoords = calculateOrderedCoordsForTrisection(start.getX(),
		// end.getX());
		// }
		// else {
		// orderedXCoords = calculateOrderedCoordsForTrisection(end.getX(),
		// start.getX());
		// }
		//
		// // Work y coordinates
		// List<Float> orderedYCoords;
		// if ( start.getY() <= end.getY()) {
		// orderedYCoords = calculateOrderedCoordsForTrisection(start.getY(),
		// end.getY());
		// }
		// else {
		// orderedYCoords = calculateOrderedCoordsForTrisection(end.getY(),
		// start.getY());
		// }
		//

		return returnList;
	}

	public List<Line> additionalSegments(Line lineSegment) {

		// Calculate theta, the angle between horizontal and this line segment.
		// Known values = length of hypotenuse, length of adjacent
		// theta = acos ( adj / hyp )
		//
		// 1. Get the mid point
		Point midPoint = lineSegment.calculateMidPoint();

		// 2. Calculate the distance between midPoint and lineSegment.end (which
		// is hyp)
		float hypothenuseLength = midPoint.distanceTo(lineSegment.getEnd());

		// 3. Calculate the x distance between midPoint and lineSegment.end
		// (which is adj)
		Line adjacentLine = Line.newLine(midPoint, lineSegment.getEnd());
		float adjacentLength = adjacentLine.calculateAbsoluteXLength();

		// 4. Calculate theta
		float theta = (float) Math.acos(adjacentLength / hypothenuseLength);

		// 5. Calculate phi (PI / 2 - theta)
		float phi1 = (float) ((Math.PI / 2.0) - theta);
		float phi2 = (float) ((3 * Math.PI / 2.0) - theta);

		// 6. Calculate the x and y for both phis based on within which
		// quandrant the line resides
		//
		// x = cos(phi) * sqrt(3/4) * segment length
		// y = sin(phi) * sqrt(3/4) * segment length
		// TODO Capture the notes (in notebook) of why it's sqrt(3/4)
		Point extendedMidPoint1;
		Point extendedMidPoint2;

		if (lineSegment.getQuadrant() == Line.Quadrant.UPPER_LEFT) {
			float xExtendedMid1 = (float) (Math.cos(phi1)
					* Math.sqrt((float) 0.75) * lineSegment.calculateLength());
			float yExtendedMid1 = (float) (Math.sin(phi1)
					* Math.sqrt((float) 0.75) * lineSegment.calculateLength());
			extendedMidPoint1 = Point.newPoint(midPoint.getX() + xExtendedMid1,
					midPoint.getY() + yExtendedMid1);
			float xExtendedMid2 = (float) (Math.cos(phi2)
					* Math.sqrt((float) 0.75) * lineSegment.calculateLength());
			float yExtendedMid2 = (float) (Math.sin(phi2)
					* Math.sqrt((float) 0.75) * lineSegment.calculateLength());
			extendedMidPoint2 = Point.newPoint(midPoint.getX() + xExtendedMid2,
					midPoint.getY() + yExtendedMid2);
		}

		else {
			float xExtendedMid1 = (float) (Math.cos(phi1)
					* Math.sqrt((float) 0.75) * lineSegment.calculateLength());
			float yExtendedMid1 = (float) (Math.sin(phi1)
					* Math.sqrt((float) 0.75) * lineSegment.calculateLength());
			extendedMidPoint1 = Point.newPoint(midPoint.getX() + xExtendedMid1,
					midPoint.getY() - yExtendedMid1);
			float xExtendedMid2 = (float) (Math.cos(phi2)
					* Math.sqrt((float) 0.75) * lineSegment.calculateLength());
			float yExtendedMid2 = (float) (Math.sin(phi2)
					* Math.sqrt((float) 0.75) * lineSegment.calculateLength());
			extendedMidPoint2 = Point.newPoint(midPoint.getX() + xExtendedMid2,
					midPoint.getY() - yExtendedMid2);
		}

		// 7. Choose the phi which is furthest away from the center point (and
		// therefore pointing "out"
		Point extendedMidPoint;
		float distanceToMidPoint1 = extendedMidPoint1.distanceTo(this.paneCenterPoint);
		float distanceToMidPoint2 = extendedMidPoint2.distanceTo(this.paneCenterPoint);
		if (extendedMidPoint1.distanceTo(this.paneCenterPoint) > extendedMidPoint2
				.distanceTo(this.paneCenterPoint)) {
			extendedMidPoint = extendedMidPoint1;
		} else {
			extendedMidPoint = extendedMidPoint2;
		}

		// 8. Add the additional line segments
		List<Line> returnAdditionalSegments = new ArrayList<Line>(2);
		returnAdditionalSegments.add(Line.newLine(lineSegment.getStart(),
				extendedMidPoint));
		returnAdditionalSegments.add(Line.newLine(lineSegment.getEnd(),
				extendedMidPoint));

		return returnAdditionalSegments;
	}

	/**
	 * Helper method that calculates 4 coords (for the outer line segments) for
	 * the trisection, based on the start and end coords (and assumes start <=
	 * end).
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	protected List<Float> calculateOrderedCoordsForTrisection(float start,
			float end) {

		List<Float> orderedCoords = new ArrayList<Float>(4);
		float length = end - start;
		float segmentLength = length / 3;
		orderedCoords.add(start);
		orderedCoords.add(start + segmentLength);
		orderedCoords.add(start + 2 * segmentLength);
		orderedCoords.add(end);

		return orderedCoords;

	}

}
