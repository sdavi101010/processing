package com.scottdavidson.fractal.util;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Immutable representation of a simple triangle (three connected lines).
 * 
 * @author scdavidson
 * 
 */
public class Triangle {

	private List<Line> edges;

	public static Triangle newTriangle(List<Line> proposedEdges) {

		// Return new triangle
		return new Triangle(proposedEdges);
	}
	
	public List<Line> getEdges() {
		
		// Make a defensive copy of the edges and return the new list
		List<Line> returnList = new ArrayList<Line>(3);
		for (Line edge: edges) {
			returnList.add(edge.copy());
		}
		
		// Return it
		return returnList;
	}
	
	public TreeSet<Point> getPoints() {
		
		// Add the points from the first two edges (one being a duplicate) to a 
		// set and return it
		TreeSet<Point> set = new TreeSet<Point>();
		for (int i=0; i < 2; i++) {
			set.add(this.edges.get(i).getStart());
			set.add(this.edges.get(i).getEnd());
		}
		
		return set;
	}

	/**
	 * Single constructor.
	 * 
	 * @param proposedEdges
	 *            the proposed edges that will make up the triangle
	 */
	protected Triangle(List<Line> proposedEdges) {

		// Error checking
		if (null == proposedEdges || proposedEdges.isEmpty()) {
			throw new NullPointerException(
					"Proposed edges list is null or empty!");
		} else if (proposedEdges.size() != 3) {
			throw new IllegalArgumentException("Triangle must be three lines!");
		}

		// Verify that the three lines are connected
		//
		// Algorithm : compare all points (starts and ends) and ensure that
		// there are
		// three (3) that are equal and each point has an equal to its start and
		// end
		int numEqualCornerPoints = 0;
		boolean onePointHasNoEquivalent = false;
		for (int i = 0; i < 3; i++) {

			boolean startHasEquivalent = false;
			boolean endHasEquivalent = false;

			for (int j = 0; j < 3; j++) {

				if (i != j) {

					// Compare ith START
					if (proposedEdges.get(i).getStart()
							.equals(proposedEdges.get(j).getStart())
							|| proposedEdges.get(i).getStart()
									.equals(proposedEdges.get(j).getEnd())) {
						startHasEquivalent = true;
						numEqualCornerPoints++;
					}

					// Compare ith END
					if (proposedEdges.get(i).getEnd()
							.equals(proposedEdges.get(j).getStart())
							|| proposedEdges.get(i).getEnd()
									.equals(proposedEdges.get(j).getEnd())) {
						endHasEquivalent = true;
						numEqualCornerPoints++;

					}

				}
			}
			
			if (!startHasEquivalent || !endHasEquivalent) {
				onePointHasNoEquivalent = true;
				System.out
						.println("The proposed edge : "
								+ proposedEdges.get(i).toString()
								+ " has at least one point with no equivalent. ["
								+ startHasEquivalent + ","
								+ endHasEquivalent + "]");
				break;
			}


		}
		
		if ( onePointHasNoEquivalent ) {
			throw new IllegalArgumentException(
					"Triangle must have three connected lines! One point found to not have an equivalent.");
		}
		
		else if (6 != numEqualCornerPoints) {
			throw new IllegalArgumentException(
					"Triangle must have three connected lines!");
		} else {
			if (proposedEdges.get(0).equals(proposedEdges.get(1))
					|| proposedEdges.get(0).equals(proposedEdges.get(2))
					|| proposedEdges.get(1).equals(proposedEdges.get(2))) {
				throw new IllegalArgumentException(
						"Triangle can't have duplicate lines!");
			}
		}

		// Essentially make a defensive copy, easy though b/c the Line class is
		// immutable
		this.edges = new ArrayList<Line>(3);
		for (Line line : proposedEdges) {
			this.edges.add(line);
		}

	}

}
