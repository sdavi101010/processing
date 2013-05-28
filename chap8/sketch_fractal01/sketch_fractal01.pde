final int MAX_LEVELS = 3;
final int NUM_CHILDREN = 3;
Branch trunk;

void setup() {
  
  size(750,500);
  background(255);
  noFill();
  smooth();
  newTree();
}

void newTree() {
  trunk = new Branch(1,0,width/2,50);
  trunk.drawMe();
}

class Branch {
  
  float level;
  float index;
  float x;
  float y;
  float endx;
  float endy;
  
  float strokeWidth, alpha;
  float length, lengthChange;
  float rotation, rotationChange;
  
  Branch[] children = new Branch[0];
  
  Branch(float level, float index, float xCoord, float yCoord) {
    this.level = level;
    this.index = index;
  
    this.strokeWidth = (1/this.level) * 100;
    this.alpha = 255 / this.level;
    this.length = (1/level) * random(200);
    this.rotation = random(360);
    this.lengthChange = random(10) -5;
    this.rotationChange = random(10) -5;

    
    updateMe(xCoord,yCoord);
    
    if(this.level < MAX_LEVELS) {
      children = new Branch[NUM_CHILDREN];
      for (int i=0; i < NUM_CHILDREN; i++) {
        children[i] = new Branch(level+1, i, endx, endy);
      }
    }
  }
  
  void updateMe(float xCoord, float yCoord) {
    println("xCoord = " + xCoord + " , yCoord = " + yCoord);
    
    this.x = xCoord;
    this.y = yCoord;
    
    this.rotation += rotationChange;
    if (this.rotation > 360) {
      this.rotation = 0;
    } else if ( this.rotation < 0 ) {
      this.rotation = 360;
    }
    
    this.length -= this.lengthChange;
    if (length < 0 ) {
      this.lengthChange *= -1;
    } else if ( this.length > 200) {
      this.lengthChange *= -1;
    }
    
    float radian = radians(this.rotation);
    
    this.endx = this.x + (this.length * cos(radian));
    this.endy = this.y + (this.length * sin(radian));
    
    for (int i=0; i<children.length;i++) {
      children[i].updateMe(endx, endy);
    }
  }
  
  void drawMe() {
    
    strokeWeight(strokeWidth);
    stroke(0,alpha);
    fill(255,alpha);
    line(this.x, this.y, this.endx, this.endy);
    ellipse(this.endx, this.endy, this.length/12,this.length/12);
    
    for(int i=0; i<children.length; i++) {
      children[i].drawMe();
    }
  }
  
  void draw() {
    background(255);
    trunk.updateMe(width/2,height/2);
    trunk.drawMe();
  }
  
}


