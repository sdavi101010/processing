package com.scottdavidson.fractal.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TriangleTest {

	@Test(expected=NullPointerException.class)
	public void testNullProposedEdges() {
		
		Triangle.newTriangle(null);
	}

	@Test(expected=NullPointerException.class)
	public void testEmptyProposedEdges() {
		
		Triangle.newTriangle(new ArrayList<Line>());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testWrongNumberIllegalArgument() {
		
		List<Line> proposedEdges = new ArrayList<Line>(2);
		proposedEdges.add(BasicLine.newBasicLine(Point.newPoint(5, 4), Point.newPoint(6,7)));
		proposedEdges.add(BasicLine.newBasicLine(Point.newPoint(5, 4), Point.newPoint(6,7)));
		
		Triangle.newTriangle(proposedEdges);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNotConnectedIllegalArgument() {
		
		List<Line> proposedEdges = new ArrayList<Line>(3);
		proposedEdges.add(BasicLine.newBasicLine(Point.newPoint(50.0f, 40.0f), Point.newPoint(60.5f,70.25f)));
		proposedEdges.add(BasicLine.newBasicLine(Point.newPoint(60.5f,70.25f), Point.newPoint(90.1f, 140.7f)));
		proposedEdges.add(BasicLine.newBasicLine(Point.newPoint(90.1f, 140.7f), Point.newPoint(51.0f, 40.0f)));
		
		Triangle.newTriangle(proposedEdges);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDuplicateEdgeIllegalArgument() {
		
		List<Line> proposedEdges = new ArrayList<Line>(3);
		proposedEdges.add(BasicLine.newBasicLine(Point.newPoint(50.0f, 40.0f), Point.newPoint(60.5f,70.25f)));
		proposedEdges.add(BasicLine.newBasicLine(Point.newPoint(60.5f,70.25f), Point.newPoint(90.1f, 140.7f)));
		proposedEdges.add(BasicLine.newBasicLine(Point.newPoint(50.0f, 40.0f), Point.newPoint(60.5f,70.25f)));
		
		Triangle.newTriangle(proposedEdges);
	}
}
