package com.scottdavidson.fractal;

import java.util.ArrayList;
import java.util.List;

import com.scottdavidson.fractal.util.KochSnowflakeLine;
import com.scottdavidson.fractal.util.Point;

public class KochSnowflake {

	public KochSnowflake newKochSnowflake() {
		return new KochSnowflake();
	}

	/**
	 * Single constructor for KochSnowflake.
	 * 
	 * @param paneCenterPoint
	 */
	protected KochSnowflake() {
	}

	// private static Logger LOGGER = Logger.getLogger(KochSnowflake.class);

	/**
	 * Transforms the provide lineSegment into 4 others - 1/3 segments for first
	 * and third and then the fractal edges of the new triangle.
	 * 
	 * @param lineSegment
	 * @return
	 */
	public List<KochSnowflakeLine> transformSegment(
			KochSnowflakeLine lineSegment) {

		// Create the return list
		List<KochSnowflakeLine> returnList = new ArrayList<KochSnowflakeLine>(4);

		// Calculate the trisection segment length (x and y direction)
		float xSegmentLength = (lineSegment.getEnd().getX() - lineSegment
				.getStart().getX()) / new Float(3.0);
		float ySegmentLength = (lineSegment.getEnd().getY() - lineSegment
				.getStart().getY()) / new Float(3.0);

		// Generate the outer segments
		returnList.add(KochSnowflakeLine.newLine(lineSegment.getStart(), Point
				.newPoint(lineSegment.getStart().getX() + xSegmentLength,
						lineSegment.getStart().getY() + ySegmentLength),
				lineSegment.getReferencePoint()));

		returnList.add(KochSnowflakeLine.newLine(
				Point.newPoint(lineSegment.getStart().getX() + 2
						* xSegmentLength, lineSegment.getStart().getY() + 2
						* ySegmentLength), lineSegment.getEnd(),
				lineSegment.getReferencePoint()));

		// Generate the 2 segements that form an equilateral triangle
		List<KochSnowflakeLine> additionalSegments = additionalSegments(KochSnowflakeLine
				.newLine(
						Point.newPoint(lineSegment.getStart().getX()
								+ xSegmentLength, lineSegment.getStart().getY()
								+ ySegmentLength),
						Point.newPoint(lineSegment.getStart().getX() + 2
								* xSegmentLength, lineSegment.getStart().getY()
								+ 2 * ySegmentLength),
						lineSegment.getReferencePoint()));

		// Add the additional segments to the return list
		returnList.add(additionalSegments.get(0));
		returnList.add(additionalSegments.get(1));

		return returnList;
	}

	public List<KochSnowflakeLine> additionalSegments(
			KochSnowflakeLine lineSegment) {

		System.out.println("lineSegment = " + lineSegment.toString());

		// Calculate theta, the angle between horizontal and this line segment.
		// Known values = length of hypotenuse, length of adjacent
		// theta = acos ( adj / hyp )
		//
		// 1. Get the mid point
		Point midPoint = lineSegment.getLine().calculateMidPoint();

		// 2. Calculate the distance between midPoint and lineSegment.end (which
		// is hyp)
		float hypothenuseLength = midPoint.distanceTo(lineSegment.getEnd());

		// 3. Calculate the x distance between midPoint and lineSegment.end
		// (which is adj)
		// TODO adjacentLine can be replaced with a simple line (no R or Q
		// required)
		KochSnowflakeLine adjacentLine = KochSnowflakeLine.newLine(midPoint,
				lineSegment.getEnd(), lineSegment.getReferencePoint());
		float adjacentLength = adjacentLine.getLine().calculateAbsoluteXLength();

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
		Point extendedReferencePoint1;
		Point extendedReferencePoint2;

		if (lineSegment.getQuadrant() == KochSnowflakeLine.Quadrant.UPPER_LEFT) {
			float xExtendedMid1 = (float) (Math.cos(phi1)
					* Math.sqrt((float) 0.75) * lineSegment.getLine().calculateLength());
			float yExtendedMid1 = (float) (Math.sin(phi1)
					* Math.sqrt((float) 0.75) * lineSegment.getLine().calculateLength());
			extendedMidPoint1 = Point.newPoint(midPoint.getX() + xExtendedMid1,
					midPoint.getY() + yExtendedMid1);

			float xExtendedRef1 = (float) (Math.cos(phi1)
					* Math.sqrt((float) 0.75) * lineSegment.getLine().calculateLength() / 2);
			float yExtendedRef1 = (float) (Math.sin(phi1)
					* Math.sqrt((float) 0.75) * lineSegment.getLine().calculateLength() / 2);
			extendedReferencePoint1 = Point.newPoint(midPoint.getX()
					+ xExtendedRef1, midPoint.getY() + yExtendedRef1);

			float xExtendedMid2 = (float) (Math.cos(phi2)
					* Math.sqrt((float) 0.75) * lineSegment.getLine().calculateLength());
			float yExtendedMid2 = (float) (Math.sin(phi2)
					* Math.sqrt((float) 0.75) * lineSegment.getLine().calculateLength());
			extendedMidPoint2 = Point.newPoint(midPoint.getX() + xExtendedMid2,
					midPoint.getY() + yExtendedMid2);

			float xExtendedRef2 = (float) (Math.cos(phi2)
					* Math.sqrt((float) 0.75) * lineSegment.getLine().calculateLength() / 2);
			float yExtendedRef2 = (float) (Math.sin(phi2)
					* Math.sqrt((float) 0.75) * lineSegment.getLine().calculateLength() / 2);
			extendedReferencePoint2 = Point.newPoint(midPoint.getX()
					+ xExtendedRef2, midPoint.getY() + yExtendedRef2);

			System.out.println("(upper left) ");
			System.out.println("xMP1: " + extendedMidPoint1.toString());
			System.out.println("xMP2: " + extendedMidPoint2.toString());
			System.out.println("rMP1: " + extendedReferencePoint1.toString());
			System.out.println("rMP2: " + extendedReferencePoint2.toString());
		}

		else {
			float xExtendedMid1 = (float) (Math.cos(phi1)
					* Math.sqrt((float) 0.75) * lineSegment.getLine().calculateLength());
			float yExtendedMid1 = (float) (Math.sin(phi1)
					* Math.sqrt((float) 0.75) * lineSegment.getLine().calculateLength());
			extendedMidPoint1 = Point.newPoint(midPoint.getX() + xExtendedMid1,
					midPoint.getY() - yExtendedMid1);

			float xExtendedRef1 = (float) (Math.cos(phi1)
					* Math.sqrt((float) 0.75) * lineSegment.getLine().calculateLength() / 2);
			float yExtendedRef1 = (float) (Math.sin(phi1)
					* Math.sqrt((float) 0.75) * lineSegment.getLine().calculateLength() / 2);
			extendedReferencePoint1 = Point.newPoint(midPoint.getX()
					+ xExtendedRef1, midPoint.getY() - yExtendedRef1);

			float xExtendedMid2 = (float) (Math.cos(phi2)
					* Math.sqrt((float) 0.75) * lineSegment.getLine().calculateLength());
			float yExtendedMid2 = (float) (Math.sin(phi2)
					* Math.sqrt((float) 0.75) * lineSegment.getLine().calculateLength());
			extendedMidPoint2 = Point.newPoint(midPoint.getX() + xExtendedMid2,
					midPoint.getY() - yExtendedMid2);

			float xExtendedRef2 = (float) (Math.cos(phi2)
					* Math.sqrt((float) 0.75) * lineSegment.getLine().calculateLength() / 2);
			float yExtendedRef2 = (float) (Math.sin(phi2)
					* Math.sqrt((float) 0.75) * lineSegment.getLine().calculateLength() / 2);
			extendedReferencePoint2 = Point.newPoint(midPoint.getX()
					+ xExtendedRef2, midPoint.getY() - yExtendedRef2);

			System.out.println("(upper right) ");
			System.out.println("xMP1: " + extendedMidPoint1.toString());
			System.out.println("xMP2: " + extendedMidPoint2.toString());
			System.out.println("rMP1: " + extendedReferencePoint1.toString());
			System.out.println("rMP2: " + extendedReferencePoint2.toString());

		}

		// 7. Choose the phi which is furthest away from the center point (and
		// therefore pointing "out"
		Point extendedMidPoint;
		Point extendedReferencePoint;
		if (extendedMidPoint1.distanceTo(lineSegment.getReferencePoint()) > extendedMidPoint2
				.distanceTo(lineSegment.getReferencePoint())) {
			extendedMidPoint = extendedMidPoint1;
			extendedReferencePoint = extendedReferencePoint1;
		} else {
			extendedMidPoint = extendedMidPoint2;
			extendedReferencePoint = extendedReferencePoint2;
		}

		// 8. Add the additional line segments
		List<KochSnowflakeLine> returnAdditionalSegments = new ArrayList<KochSnowflakeLine>(
				2);
		returnAdditionalSegments.add(KochSnowflakeLine.newLine(
				lineSegment.getStart(), extendedMidPoint,
				extendedReferencePoint));
		returnAdditionalSegments
				.add(KochSnowflakeLine.newLine(lineSegment.getEnd(),
						extendedMidPoint, extendedReferencePoint));

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
