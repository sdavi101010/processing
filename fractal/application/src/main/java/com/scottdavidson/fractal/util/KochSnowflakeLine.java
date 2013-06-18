package com.scottdavidson.fractal.util;

public class KochSnowflakeLine {

	public static enum Quadrant {
		UPPER_LEFT, UPPER_RIGHT, LOWER_LEFT, LOWER_RIGHT
	}

	private final Line basicLine;
	private final Quadrant quadrant;
	private final Point referencePoint;

	public static KochSnowflakeLine newLine(Point start, Point end,
			Point referencePoint) {
		return new KochSnowflakeLine(start, end, referencePoint);

	}

	public static KochSnowflakeLine newLine(Line line, Point referencePoint) {
		return new KochSnowflakeLine(line.getStart(), line.getEnd(),
				referencePoint);

	}

	/**
	 * @ return the basic line.
	 */
	public Line getLine() {
		return this.basicLine;
	}
	
	public Point getStart() {
		return this.basicLine.getStart();
	}
	
	public Point getEnd() {
		return this.basicLine.getEnd();
	}

	/**
	 * @return the referencePoint
	 */
	public Point getReferencePoint() {
		return referencePoint;
	}

	/**
	 * @return the quadrant
	 */
	public Quadrant getQuadrant() {
		return quadrant;
	}

	@Override
	public String toString() {
		return this.basicLine.toString() + " , Quadrant: " + this.quadrant
				+ " , Reference: " + this.referencePoint.toString();
	}

	/**
	 * Determines the quandrant of this KochSnowflake.
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	protected Quadrant calculateQuadrant(Point start, Point end) {

		KochSnowflakeLine normalizedLine = normalizedLine(BasicLine
				.newBasicLine(start, end));

		if (normalizedLine.getLine().getStart().getY() > normalizedLine
				.getLine().getEnd().getY()) {
			return KochSnowflakeLine.Quadrant.UPPER_LEFT;
		} else {
			return KochSnowflakeLine.Quadrant.UPPER_RIGHT;
		}
	}

	/**
	 * This helper method normalizes the current line from left to right with
	 * respect to the start / end and x coordinate, that is, the start will have
	 * a smaller x coordinate than the end.
	 * 
	 * @return a line that is normalized from left to right
	 */
	protected KochSnowflakeLine normalizedLine(Line line) {

		// Normalize the basic line first
		Line normalized = this.basicLine.normalizedLine();

		return new KochSnowflakeLine(normalized.getStart(),
				normalized.getEnd(), null, null);

	}

	protected KochSnowflakeLine(Point start, Point end, Point referencePoint) {
		this.basicLine = BasicLine.newBasicLine(start, end);
		this.referencePoint = referencePoint;
		this.quadrant = calculateQuadrant(start, end);
	}

	/**
	 * Special constructor used *only* for calculating the quandrant in the
	 * protected / nominal constructor.
	 * 
	 * @param start
	 * @param end
	 * @param quadrant
	 */
	private KochSnowflakeLine(Point start, Point end, Point referencePoint,
			Quadrant quadrant) {
		this.basicLine = BasicLine.newBasicLine(start, end);
		this.referencePoint = referencePoint;
		this.quadrant = quadrant;
	}

	/**
	 * Default constructor purposefully declared with private visibility to
	 * avoid illicit construction
	 */
	@SuppressWarnings("unused")
	private KochSnowflakeLine() {
		this.basicLine = null;
		this.referencePoint = null;
		this.quadrant = Quadrant.UPPER_LEFT;
	}

}
