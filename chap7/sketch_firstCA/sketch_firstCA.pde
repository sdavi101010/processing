Cell[][] cellArray;
int numX;
int numY;
final int CELL_SIZE = 10;

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
      cellArray[x][y].vichniacCalcNextState();
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
  boolean state;
  boolean nextState;
  Cell[] neighbors;
  
  Cell( float x, float y) {
    this.x = x * CELL_SIZE;
    this.y = y * CELL_SIZE;
    
    if ( random (2) > 1 ) {
      nextState = true;
    } else {
      nextState = false;
    }
    
    state = nextState;
    neighbors = new Cell[0];
  }
  
  void addNeighbor(Cell neighborCell) {
    neighbors = (Cell[])append(neighbors, neighborCell);
  }
  
  void calcNextState() {
    
    int liveCount = 0;
    for (int i=0; i < neighbors.length; i++ ) {
      if (neighbors[i].state == true ) {
        liveCount++;
      }
    }
    
    if (state == true) {
      if ( liveCount == 2 || liveCount == 3) {
        nextState = true;
      }
      else {
        nextState = false;
      }
    } else {
      if ( liveCount == 3) {
        nextState = true;
      } else {
        nextState = false;
      }
    }
    
  }
  
  void vichniacCalcNextState() {
    
    int liveCount = 0;
    if(state) {
      liveCount++;
    }
    
    for (int i=0; i < neighbors.length; i++ ) {
      if (neighbors[i].state == true ) {
        liveCount++;
      }
    }
    
    // Conform based on majority (including this one's current state)
    if (liveCount <= 4) {
      nextState = false;
    } else {
      nextState = true;
    }
    
    // Add some variability
    if (liveCount == 4 || liveCount == 5) {
      nextState = !nextState;
    }
        
  }
  
  void drawMe() {
    state = nextState;
    stroke(0);
    if ( state == true ) {
      fill(0);
    }
    else {
      fill(255);
    }
    
//    println("... drawing [" + x + "][" + y + "]");
    ellipse(x,y,CELL_SIZE,CELL_SIZE);
  }
  
}
