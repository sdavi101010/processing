Cell[][] cellArray;
int numX;
int numY;
final int CELL_SIZE = 10;
final int OFF = 0;
final int FIRING = 1;
final int RESTING = 2;

void setup() {
  size(500,300);
  frameRate(24);
  numX = floor(width/CELL_SIZE);
  numY = floor(height/CELL_SIZE);
  println("numX = " + numX + " , numY = " + numY);
  restart();
  
}

void restart() {
  
  cellArray = new Cell[numX][numY];
  for(int x=0; x<numX;x++) {
    for(int y=0; y<numY; y++) {
      Cell newCell = new Cell(x,y);
      cellArray[x][y] = newCell;
    }
  }
  
  for(int x=0; x<numX;x++) {
    for(int y=0; y<numY; y++) {
      int above = y-1;
      if ( above < 0 ) { above = numY-1; }
      int below = y+1;
      if (below == numY) { below = 0; }
      int left = x-1;
      if ( left < 0 ) { left = numX -1; }
      int right = x + 1;
      if (right == numX) { right = 0; }
      
      // Set the neighbors
      cellArray[x][y].addNeighbor(cellArray[left][above]);
      cellArray[x][y].addNeighbor(cellArray[left][y]);
      cellArray[x][y].addNeighbor(cellArray[left][below]);
      cellArray[x][y].addNeighbor(cellArray[x][below]);
      cellArray[x][y].addNeighbor(cellArray[right][below]);
      cellArray[x][y].addNeighbor(cellArray[right][y]);
      cellArray[x][y].addNeighbor(cellArray[right][above]);
      cellArray[x][y].addNeighbor(cellArray[x][above]);
      
    }
  }

}

void draw() {
  background(200);
  
  for(int x=0; x<numX;x++) {
    for(int y=0; y<numY; y++) {
      cellArray[x][y].calcNextState();
    }
  }
  
  translate(CELL_SIZE/2,CELL_SIZE/2);
  
  for(int x=0; x<numX;x++) {
    for(int y=0; y<numY; y++) {
      cellArray[x][y].drawMe();
    }
  }
  
}

void mousePressed() {
  restart();
}


  

class Cell {
  
  float x;
  float y;
  int state;
  int nextState;
  Cell[] neighbors;
  
  Cell( float x, float y) {
    
    // Kind of confusing, basically set X/Y w/in the context of the whole display
    this.x = x * CELL_SIZE;
    this.y = y * CELL_SIZE;
    
    // Randomly set state
    nextState = int(random(2));
    state = nextState;
    
    neighbors = new Cell[0];
  }
  
  void addNeighbor(Cell neighborCell) {
    neighbors = (Cell[])append(neighbors, neighborCell);
  }
  
  void calcNextState() {

    // If state is OFF, count firing neighbors; if 2 are firing, then next state is FIRING
    if (state == OFF) {
      int firingCount = 0;
      for(int i=0; i<neighbors.length; i++) {
        if(neighbors[i].state == FIRING) {
          firingCount++;
        }
      }
      
      if(firingCount == 2) {
        nextState = FIRING;
      }
      else {
        nextState = state;
      }
    }
    
    // If state is FIRING, next state is RESTING
    else if ( state == FIRING ) {
      nextState = RESTING;
    }
    
    // If state is RESTING, next state is OFF
    else {
      nextState = OFF;
    }    
  }
    
  void drawMe() {
    state = nextState;
    stroke(0);
    if ( state == OFF) {
      fill(255);
    }
    else if (state == FIRING) {
      fill(150);
    }
    else {
      fill(0);
    }
    
    ellipse(x,y,CELL_SIZE,CELL_SIZE);
  }
  
}
