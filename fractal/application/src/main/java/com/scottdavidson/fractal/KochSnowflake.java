package com.scottdavidson.fractal;

import java.util.ArrayList;
import java.util.List;

import com.scottdavidson.fractal.util.Line;
import com.scottdavidson.fractal.util.Point;

public class KochSnowflake {

	// private static Logger LOGGER = Logger.getLogger(KochSnowflake.class);

	public static List<Line> transformSegment(Line lineSegment) {

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
		List<Line> additionalSegments = additionalSegments(Line.newLine(
				Point.newPoint(lineSegment.getStart().getX() + xSegmentLength, 
				           lineSegment.getStart().getY() + ySegmentLength), 
				Point.newPoint(lineSegment.getStart().getX() + 2* xSegmentLength, 
					           lineSegment.getStart().getY() + 2* ySegmentLength)));

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
	
	public static List<Line> additionalSegments(Line lineSegment) {
		
		// Calculate theta, the angle between horizontal and this line segment.
		// Known values = length of hypotenuse, length of adjacent
		// theta = acos ( adj / hyp )
		//
		// 1. Get the mid point
		Point midPoint = calculateMidPoint(lineSegment);
		
		// 2. Calculate the distance between midPoint and lineSegment.end (which is hyp)
		float hypothenuseLength = midPoint.distanceTo(lineSegment.getEnd());
		
		// 3. Calculate the x distance between midPoint and lineSegment.end (which is adj)
		Line adjacentLine = Line.newLine(midPoint, lineSegment.getEnd());
		float adjacentLength = adjacentLine.calculateAbsoluteXLength();
		
		// 4. Calculate theta
		float theta = (float) Math.acos(adjacentLength / hypothenuseLength);
		
		return null;
	}
	
	protected static Point calculateMidPoint(Line lineSegment) {
		
		float xMidCoord = 
				lineSegment.getStart().getX() + (lineSegment.calculateRelativeXLength() / new Float(2.0));
		
		float yMidCoord = 
				lineSegment.getStart().getY() + (lineSegment.calculateRelativeYLength() / new Float(2.0));
		
		return Point.newPoint(xMidCoord, yMidCoord);
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
