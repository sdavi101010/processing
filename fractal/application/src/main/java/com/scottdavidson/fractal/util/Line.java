package com.scottdavidson.fractal.util;

public class Line {

	public static enum Quadrant {
		UPPER_LEFT, UPPER_RIGHT, LOWER_LEFT, LOWER_RIGHT
	}
	
	private final Point start;
	private final Point end;
	private final Quadrant quadrant;

	public static Line newLine(Point start, Point end) {
		return new Line(start, end);

	}

	/**
	 * @return the start
	 */
	public Point getStart() {
		return start;
	}

	/**
	 * @return the end
	 */
	public Point getEnd() {
		return end;
	}	

	/**
	 * @return the quadrant
	 */
	public Quadrant getQuadrant() {
		return quadrant;
	}

	/**
	 * Helper method which simply calculates the relative length (from start to
	 * end) of this line in the X dimension.
	 * 
	 * @return returns the length of this line in relative (from start to end)
	 *         units
	 */
	public float calculateRelativeXLength() {

		return this.getEnd().getX() - this.getStart().getX();

	}

	/**
	 * Helper method which simply calculates the relative length (from start to
	 * end) of this line in the Y dimension.
	 * 
	 * @return returns the length of this line in relative (from start to end)
	 *         units
	 */
	public float calculateRelativeYLength() {

		return this.getEnd().getY() - this.getStart().getY();

	}

	/**
	 * Helper method which simply calculates the absolute length (from start to
	 * end) of this line in the X dimension.
	 * 
	 * @return returns the length of this line in relative (from start to end)
	 *         units
	 */
	public float calculateAbsoluteXLength() {

		return Math.abs(this.getEnd().getX() - this.getStart().getX());

	}

	/**
	 * Helper method which simply calculates the absolute length (from start to
	 * end) of this line in the Y dimension.
	 * 
	 * @return returns the length of this line in relative (from start to end)
	 *         units
	 */
	public float calculateAbsoluteYLength() {

		return Math.abs(this.getEnd().getY() - this.getStart().getY());

	}

	/**
	 * Helper method which calculates the length of this line (using pythagoras'
	 * theorem)
	 * 
	 * @return the length of this line
	 */
	public float calculateLength() {

		return this.getStart().distanceTo(this.getEnd());

	}

	public Point calculateMidPoint() {

		float xMidCoord = this.getStart().getX()
				+ (this.calculateRelativeXLength() / new Float(2.0));

		float yMidCoord = this.getStart().getY()
				+ (this.calculateRelativeYLength() / new Float(2.0));

		return Point.newPoint(xMidCoord, yMidCoord);
	}
	
	protected Quadrant calculateQuadrant(Point start, Point end) {
		
		Line workingLine = new Line(start, end, Quadrant.UPPER_LEFT);
		Line normalizedLine = workingLine.normalize();
		
		if ( normalizedLine.getStart().getY() > normalizedLine.getEnd().getY()) {
			return Line.Quadrant.UPPER_LEFT;
		}
		else {
			return Line.Quadrant.UPPER_RIGHT;
		}
	}
	
	/**
	 * This helper method normalizes the current line from left to right with respect to the start / end 
	 * and x coordinate, that is, the start will have a smaller x coordinate than the end.
	 * 
	 * @return a line that is normalized from left to right
	 */
	protected Line normalize() {
		
		if ( this.start.getX() <= this.end.getX()) {
			return this;
		}
		else {
			return Line.newLine(end, start);
		}
		
	}

	protected Line(Point start, Point end) {
		this.start = start;
		this.end = end;
		this.quadrant = calculateQuadrant(start, end);
	}

	/**
	 * Special constructor used *only* for calculating the quandrant in the protected / nominal constructor.
	 * 
	 * @param start
	 * @param end
	 * @param quadrant
	 */
	private Line(Point start, Point end, Quadrant quadrant) {
		this.start = start;
		this.end = end;
		this.quadrant = quadrant;
	}

	/**
	 * Default constructor purposefully declared with private visibility to
	 * avoid illicit construction
	 */
	@SuppressWarnings("unused")
	private Line() {
		this.start = null;
		this.end = null;
		this.quadrant = Quadrant.UPPER_LEFT;
	}

}
