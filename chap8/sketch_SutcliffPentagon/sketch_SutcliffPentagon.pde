FractalRoot pentagon;
final int MAX_LEVELS = 5;

void setup() {
  size(1000,1000);
  smooth();
  pentagon = new FractalRoot();
  pentagon.drawShape();
  
}

class Point {
  
  float x;
  float y;
  
  Point(float x, float y) {
    this.x = x;
    this.y = y;
  }
  
  float getX() {
    return this.x;
  }
  
  float getY() {
    return this.y;
  }
  
  void print(String prefix) {
    println(prefix + " X = " + x + " , Y = " + y);
  }
  
}

class FractalRoot {
  Point[] points = new Point[5];
  Branch rootBranch;
  
  FractalRoot() {
    
    float centX = width/2;
    float centY = height/2;
    int count = 0;
    
    for (int i=0; i<360; i+=72) {
      float x = centX + (400 * cos(radians(i)));
      float y = centY + (400 * sin(radians(i)));
      points[count] = new Point(x,y);
      points[count].print("Created Point --> ");
      count++;
      
    }
    rootBranch = new Branch(0,0,points);
  }
  
  void drawShape() {
    rootBranch.drawMe();
  }
}

class Branch {
  
  int level;
  int num;
  Point[] outerPoints = {};
  Point[] midPoints = {};
  
  Branch(int level, int num, Point[] points) {
    this.level = level;
    this.num = num;
    this.outerPoints = points;
    
    midPoints = calcMidPoints();
  }
    
    Point[] calcMidPoints() {
      Point[] midpoints = new Point[outerPoints.length];
      for (int i=0; i<outerPoints.length; i++) {
        int nexti=i+1;
        if (nexti == outerPoints.length) {nexti = 0; }
        
        outerPoints[i].print("Outer i: ");
        outerPoints[nexti].print("Outer nexti: ");
        
        Point midpoint = calcMidPoint(outerPoints[i], outerPoints[nexti]);
        midpoint.print("Mid Point calc = ");
        midpoints[i] = midpoint;
      }
      
      return midpoints;
    }
    
    Point calcMidPoint(Point end1, Point end2) {
      float midX, midY;
      
      if (end1.x > end2.x) {
        midX = end2.x + ((end1.x - end2.x)/2);
      } else {
        midX = end1.x + ((end2.x - end1.x)/2);
      }
      
      if (end1.y > end2.y) {
        midY = end2.y + ((end1.y - end2.y)/2);
      } else {
        midY = end1.y + ((end2.y - end1.y)/2);
      }
      
      Point returnPoint = new Point(midX, midY);
      returnPoint.print("Mid Calc: ");
      return returnPoint;
      
    }
  
  void drawMe() {
    strokeWeight(5 - level);
    
    // Draw outer shape
    for (int i=0; i<outerPoints.length; i++) {
      int nexti = i+1;
      if (nexti == outerPoints.length) { nexti = 0; }
      line(outerPoints[i].x, outerPoints[i].y, outerPoints[nexti].x, outerPoints[nexti].y);
    }

    // Draw the mid point ellipses    
    strokeWeight(0.5);
    fill(255,150);
    for (int j=0; j< midPoints.length; j++) {
      midPoints[j].print("drawing MidPoint = ");
      ellipse(midPoints[j].x, outerPoints[j].y, 15,15);
    }
    
    
  }
}
