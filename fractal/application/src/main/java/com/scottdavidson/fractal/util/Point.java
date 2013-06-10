package com.scottdavidson.fractal.util;

public class Point implements Comparable<Point> {

	private final float x;
	private final float y;

	
	public static Point newPoint(float x, float y) {
		return new Point(x,y);
	}
	
	public float distanceTo(Point referencePoint) {
		
		// Use Pythagoras' Theorem
		// 
		// ( (this.x - referencePoint.x)^2 + (this.y - referencePoint.y)^2 )^ 1/2
		float xLength = this.x - referencePoint.getX();
		float yLength = this.y - referencePoint.getY();
		float distanceSquared = xLength * xLength + yLength * yLength;
		
		return (float)Math.sqrt(distanceSquared);
	}
	
	protected Point(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	

	/**
	 * @return the x
	 */
	public float getX() {
		return x;
	}



	/**
	 * @return the y
	 */
	public float getY() {
		return y;
	}


	@Override
	public int compareTo(Point point) {
		
		if (this == point) {
			return 0;
		}
		
		else if ( this.getX() == point.getX() && this.getY() == point.getY()) {
			return 0;
		}
		
		else if ( this.getX() < point.getX()) {
			return -1;
		} 
		
		else if (this.getX() > point.getX()) {
			return 1;
		} 
		
		else if (this.getY() < point.getY()) {
			return -1;
		} 
		
		else {
			return 1;
		}

	}
	
	public boolean equals(Point point) {
		
		return (0 == compareTo(point));
	}

	/**
	 * Default constructor purposefully declared with private visibility to
	 * avoid illicit construction
	 */
	@SuppressWarnings("unused")
	private Point() {
		this.x = new Float(0.0);
		this.y = new Float(0.0);
	}

}
