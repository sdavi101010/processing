package com.scottdavidson.fractal;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.scottdavidson.fractal.util.Line;
import com.scottdavidson.fractal.util.Point;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class KochSnowflakePresentation extends PApplet {
	int width = 800, height = 800;
	

	// PImage scope, background, nightscope;
	// PFont font;
	// ArrayList<Zombie> zombies;
	// ZombieSpawner zspawner;
	// int counter = 1;
	// int playerhealth = 100, score = 0, ammo = 5;
	// boolean scratch = false;
	// int gameOverCount;
	// boolean regScope = true;

	public void setup() {
		size(width, height);
		smooth();
		background(255);

		// // background=loadImage("D:\\_Pictures\\foggy_zombies.jpg");
		// // scope= loadImage("D:\\_Pictures\\scope.png");
		// background = loadImage("thick_fog.jpg");
		// scope = loadImage("scope.png");
		// nightscope = loadImage("scopegood1nightvis.png");
		// font = createFont("Georgia", 20);
		// textFont(font);
		//
		// zombies = new ArrayList<Zombie>();
		// zspawner = new ZombieSpawner(this);
		
		

//		KochSnowflake snowFlake = new KochSnowflake(centerPoint());
//		Line baseLine = Line.newLine(Point.newPoint(100, 100),
//				Point.newPoint(130, 70));
//		List<Line> transformedSegments = snowFlake.transformSegment(baseLine);
//		drawLineShape(transformedSegments);
//		
//		baseLine = Line.newLine(Point.newPoint(200, 200),
//				Point.newPoint(400, 330));
//		transformedSegments = snowFlake.transformSegment(baseLine);
//		drawLineShape(transformedSegments);
//
//		baseLine = Line.newLine(Point.newPoint(700, 700),
//				Point.newPoint(750, 630));
//		transformedSegments = snowFlake.transformSegment(baseLine);
//		drawLineShape(transformedSegments);

		
		KochSnowflake snowFlake = new KochSnowflake(centerPoint());
		Line baseLine = Line.newLine(Point.newPoint(200, 600),
				Point.newPoint(600, 600));
		List<Line> transformedSegments = snowFlake.transformSegment(baseLine);
		drawLineShape(transformedSegments);

		baseLine = Line.newLine(Point.newPoint(200, 600),
				Point.newPoint(400, 260));
		transformedSegments = snowFlake.transformSegment(baseLine);
		drawLineShape(transformedSegments);
		
		baseLine = Line.newLine(Point.newPoint(600, 600),
				Point.newPoint(400, 260));
		transformedSegments = snowFlake.transformSegment(baseLine);
		drawLineShape(transformedSegments);
	}

	protected void drawLine(Line line) {
		line(line.getStart().getX(), line.getStart().getY(), line.getEnd()
				.getX(), line.getEnd().getY());
	}

	protected void drawLineShape(List<Line> lines) {
		for (Line line : lines) {
			drawLine(line);
		}
	}

	protected Point centerPoint() {
		return Point.newPoint((float) Math.floor(this.width / 2.0),
				(float) Math.floor(this.height / 2.0));
	}

//	public void draw() {
//
//		KochSnowflake snowFlake = new KochSnowflake(centerPoint());
//		Line baseLine = Line.newLine(Point.newPoint(100, 100),
//				Point.newPoint(130, 70));
//		List<Line> transformedSegments = snowFlake.transformSegment(baseLine);
//		drawLineShape(transformedSegments);
//		
//		baseLine = Line.newLine(Point.newPoint(200, 200),
//				Point.newPoint(400, 330));
//		transformedSegments = snowFlake.transformSegment(baseLine);
//		drawLineShape(transformedSegments);
//
//		baseLine = Line.newLine(Point.newPoint(700, 700),
//				Point.newPoint(750, 630));
//		transformedSegments = snowFlake.transformSegment(baseLine);
//		drawLineShape(transformedSegments);
//
//
//	}

}
