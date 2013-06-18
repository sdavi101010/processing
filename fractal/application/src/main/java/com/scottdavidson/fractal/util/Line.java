package com.scottdavidson.fractal.util;

/**
 * Interface for a basic line consisting of a start and end point.
 * 
 * @author scdavidson
 *
 */
public interface Line {
	
	/**
	 * Copy constructor which allows clients to construct defensive copies as needed
	 * (e.g., when building shapes with Line objects) and allows concrete implementations to 
	 * make this optimally efficient if the implementation is an immutable one.
	 */
	Line copy();

	/**
	 * Returns the starting point of the line
	 * 
	 * @return the starting point of the line
	 */
	Point getStart();

	/**
	 * Returns the ending point of the line
	 * 
	 * @return the ending point of the line
	 */
	Point getEnd();

	/**
	 * Calculate the mid point of this line.
	 * @return the mid point of this line.
	 */
	Point calculateMidPoint();

	public abstract Line normalizedLine();

	public abstract float calculateLength();

	public abstract float calculateAbsoluteYLength();

	public abstract float calculateAbsoluteXLength();

	public abstract float calculateRelativeYLength();

	public abstract float calculateRelativeXLength();

}
