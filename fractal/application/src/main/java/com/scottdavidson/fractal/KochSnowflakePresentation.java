package com.scottdavidson.fractal;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

import com.scottdavidson.fractal.util.BasicLine;
import com.scottdavidson.fractal.util.KochSnowflakeLine;
import com.scottdavidson.fractal.util.Line;
import com.scottdavidson.fractal.util.Point;
import com.scottdavidson.fractal.util.ShapeFactory;

public class KochSnowflakePresentation extends PApplet {
	
	private static final long serialVersionUID = 1161694110646140840L;

	int width = 800, height = 800;

	public void setup() {

		size(width, height);
		smooth();
		background(255);

		KochSnowflake snowFlake = new KochSnowflake();
//		KochSnowflakeLine baseline = KochSnowflakeLine.newLine(Point.newPoint(200, 600),
//				Point.newPoint(600, 600), Point.newPoint(400, 400));
//		Line baseline = BasicLine.newBasicLine(Point.newPoint(200, 800),
//				Point.newPoint(800, 800));
		Line baseline = BasicLine.newBasicLine(Point.newPoint(200, 600),
		Point.newPoint(600, 600));

		List<KochSnowflakeLine> snowflakeLines = createTriangle(baseline);
		for (int i = 0; i < 6; i++) {
			List<KochSnowflakeLine> nextLevelLines = new ArrayList<KochSnowflakeLine>();
			for (KochSnowflakeLine line : snowflakeLines) {
				List<KochSnowflakeLine> transformedSegments = snowFlake
						.transformSegment(line);
				nextLevelLines.addAll(transformedSegments);
			}
			snowflakeLines = new ArrayList<KochSnowflakeLine>();
			snowflakeLines.addAll(nextLevelLines);
		}
				
		drawLineShape(snowflakeLines);
  
		saveFrame("kochSnowflake6.jpg");
		
	}

	protected void drawLine(KochSnowflakeLine line) {
		line(line.getStart().getX(), line.getStart().getY(), line.getEnd()
				.getX(), line.getEnd().getY());
	}

	protected void drawLineShape(List<KochSnowflakeLine> lines) {
		for (KochSnowflakeLine line : lines) {
			drawLine(line);
		}
	}

	protected Point centerPoint() {
		return Point.newPoint((float) Math.floor(this.width / 2.0),
				(float) Math.floor(this.height / 2.0));
	}

	
	protected List<KochSnowflakeLine> createTriangle(Line baseline) {
		
		// Create a basic triangle
		List<Line> triangle = ShapeFactory.createTriangle(baseline);
		
		// Recreate it as KochSnowflakeLines
		List<KochSnowflakeLine> returnSnowflakeLines = 
				new ArrayList<KochSnowflakeLine>(3);
		for (Line line : triangle) {
			returnSnowflakeLines.add(KochSnowflakeLine.newLine(line, centerPoint()));
		}
		
		return returnSnowflakeLines;
		
	}
	
}
