package com.scottdavidson.fractal.util;

public class FloatingPointComparator {
	
	private final static float DEFAULT_EPSILON = 0.00001f;

	/**
	 * Floating point comparison helper method that takes into account the
	 * special circumstances of the inaccuracies when directly comparing
	 * floating point numbers which uses a default epsilon (0.00001).
	 * 
	 * @param floatA
	 * @param floatB
	 * @param epsilon
	 * @return
	 */
	public static boolean nearlyEqual(float floatA, float floatB) {
		return nearlyEqual(floatA, floatB, DEFAULT_EPSILON);
	}
	
	/**
	 * Floating point comparison helper method that takes into account the
	 * special circumstances of the inaccuracies when directly comparing
	 * floating point numbers.
	 * 
	 * @param floatA
	 * @param floatB
	 * @param epsilon
	 * @return
	 */
	public static boolean nearlyEqual(float floatA, float floatB, float epsilon) {
		
		final float absA = Math.abs(floatA);
		final float absB = Math.abs(floatB);
		final float diff = Math.abs(floatA - floatB);

		// Short to handle infinities
		if (floatA == floatB) {
			return true;

			// If floatA or floatB is zero (or extremely close to it), the
			// relative error is less meaningful
		} else if (floatA == 0 || floatB == 0 || diff < Float.MIN_NORMAL) {

			return diff < (epsilon * Float.MIN_NORMAL);

			// Use relative error
		} else {
			return diff / (absA + absB) < epsilon;
		}
	}

}
