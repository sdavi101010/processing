package com.scottdavidson.fractal.util;


/**
 * An immutable representation of a basic line consisting of a simple start and end point.
 * 
 * @author scdavidson
 * 
 */
public class BasicLine implements Line {

	private final Point start;
	private final Point end;

	public static Line newBasicLine(Point start, Point end) {
		return new BasicLine(start, end);
	}

	@Override
	public BasicLine copy() {
		return this;	
	}
	
	@Override
	public Point getStart() {
		return start;
	}

	@Override
	public Point getEnd() {
		return end;
	}

	@Override
	public Point calculateMidPoint() {
		float xMidCoord = this.getStart().getX()
				+ (this.calculateRelativeXLength() / new Float(2.0));

		float yMidCoord = this.getStart().getY()
				+ (this.calculateRelativeYLength() / new Float(2.0));

		return Point.newPoint(xMidCoord, yMidCoord);
	}

	@Override
	public String toString() {
		return "Start: " + this.start.toString() + " , End: "
				+ this.end.toString();
	}

	/**
	 * Helper method which simply calculates the relative length (from start to
	 * end) of this line in the X dimension.
	 * 
	 * @return returns the length of this line in relative (from start to end)
	 *         units
	 */
	@Override
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
	@Override
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
	@Override
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
	@Override
	public float calculateAbsoluteYLength() {

		return Math.abs(this.getEnd().getY() - this.getStart().getY());

	}

	/**
	 * Helper method which calculates the length of this line (using pythagoras'
	 * theorem)
	 * 
	 * @return the length of this line
	 */
	@Override
	public float calculateLength() {

		return this.getStart().distanceTo(this.getEnd());

	}

	/**
	 * This helper method normalizes the current line from left to right with
	 * respect to the start / end and x coordinate, that is, the start will have
	 * a smaller x coordinate than the end.
	 * 
	 * @return a line that is normalized from left to right
	 */
	@Override
	public Line normalizedLine() {

		if (getStart().getX() <= getEnd().getX()) {
			return this;
		} else {
			return newBasicLine(getEnd(), getStart());
		}

	}

	/**
	 * Single (protected) constructor to support factory method but not expose
	 * the constructor.
	 * 
	 * @param start
	 * @param end
	 */
	protected BasicLine(Point start, Point end) {
		this.start = start;
		this.end = end;
	}

	@SuppressWarnings("unused")
	private BasicLine() {
		this.start = null;
		this.end = null;
	}

}
