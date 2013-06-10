package com.scottdavidson.fractal.util;

public class Line {
	
	private final Point start;
	private final Point end;
	
	
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
	 * Helper method which simply calculates the relative length (from start to end) of this line in the X dimension.
	 * @return returns the length of this line in relative (from start to end) units
	 */
	public float calculateRelativeXLength() {
		
		return this.getEnd().getX() - this.getStart().getX();
		
	}

	/**
	 * Helper method which simply calculates the relative length (from start to end) of this line in the Y dimension.
	 * @return returns the length of this line in relative (from start to end) units
	 */
	public float calculateRelativeYLength() {
		
		return this.getEnd().getY() - this.getStart().getY();
		
	}

	/**
	 * Helper method which simply calculates the absolute length (from start to end) of this line in the X dimension.
	 * @return returns the length of this line in relative (from start to end) units
	 */
	public float calculateAbsoluteXLength() {
		
		return Math.abs(this.getEnd().getX() - this.getStart().getX());
		
	}

	/**
	 * Helper method which simply calculates the absolute length (from start to end) of this line in the Y dimension.
	 * @return returns the length of this line in relative (from start to end) units
	 */
	public float calculateAbsoluteYLength() {
		
		return Math.abs(this.getEnd().getY() - this.getStart().getY());
		
	}
	
	/**
	 * Helper method which calculates the length of this line (using pythagoras' theorem) 
	 * @return the length of this line
	 */
	public float calculateLength() {
		
		return this.getStart().distanceTo(this.getEnd());
		
	}

	protected Line(Point start, Point end) {
		this.start = start;
		this.end = end;
	}

	
	/**
	 * Default constructor purposefully declared with private visibility to
	 * avoid illicit construction
	 */
	@SuppressWarnings("unused")
	private Line() {
		this.start = null;
		this.end = null;
	}

}
