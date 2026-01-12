//  global variables
int characterRad = 35;
float epsilon = 0.001;
float posCharacterX;
float posCharacterY;
float posCameraX;
float velocityCharacterX;
float velocityCharacterY;
float velocityCameraX;
float friction;
boolean keyA;
boolean keyD;
boolean keySpace;
boolean canJump;
boolean keyR;
FloatList x1;
FloatList x2;
FloatList y1;
FloatList y2;
PGraphics Background;

void setup() {
  //size(1000, 1000);
  fullScreen();
  backgroundRendering();
  stroke(128);
  strokeWeight(2);
  fill(255);
  rectMode(CORNERS);
  x1 = new FloatList();
  x2 = new FloatList();
  y1 = new FloatList();
  y2 = new FloatList();
  reset();
}

void draw() {
  //  key inputs
  key_inputs();
  // obstacle generation
  obstacleGeneration();
  // character movement
  movementCharacter();
  //  rendering
  //  frame reset
  image(Background, 0, 0);
  //  camera alignment
  translate(posCameraX, height);
  scale(1, -1);
  //  character
  rect(posCharacterX-characterRad, posCharacterY+characterRad, posCharacterX+characterRad, posCharacterY-characterRad);
  // obstacles
  obstacleRendering();
  //  camera movement
  velocityCameraX = posCameraX+posCharacterX-width*0.3;
  posCameraX -= velocityCameraX*0.02;
  // program reset
  if (posCharacterY < -1000 || keyR) {
    reset();
  }
}

void backgroundRendering() {
  Background = createGraphics(width, height);
  Background.beginDraw();
  Background.noFill();
  for (int i = 0; i <= height; i++) {
    float inter = map(i, 0, height, 0, 1);
    color c = lerpColor(color(255, 150, 0), color(0, 50, 200), inter);
    Background.stroke(c);
    Background.line(0, i, width, i);
  }
  Background.endDraw();
}

void key_inputs() {
  if (keyA) {
    velocityCharacterX-=1;  //  backward
  }
  if (keyD) {
    velocityCharacterX+=1;  //  forward
  }
  if (keySpace && canJump) {  //  jump
    velocityCharacterY+=30;
    canJump = false;
  }
}

void obstacleGeneration() {
  while (x2.get(x2.size()-1)-posCharacterX <= width) {
    x1.append(x2.get(x2.size()-1)+random(100, 550));
    x2.append(x1.get(x1.size()-1)+random(100, 600));
    y1.append(random(50, 350));
    y2.append(y1.get(y1.size()-1)+random(50, 200));
  }
}

void movementCharacter() {
  //  variables
  float distanceX;
  float distanceY;
  //  hitbox check (x)
  if (velocityCharacterX>epsilon) {  //  forward
    distanceX = Float.POSITIVE_INFINITY;
    for (int i = 0; i<x1.size(); i++) {
      if (y1.get(i)<=posCharacterY+characterRad && posCharacterY-characterRad<=y2.get(i) && x1.get(i)-posCharacterX-characterRad < distanceX && x1.get(i) > posCharacterX + characterRad) {
        distanceX = x1.get(i)-posCharacterX-characterRad;
      }
    }
    //  position & momentum update (x)
    float moveX = min(velocityCharacterX, distanceX);
    posCharacterX += moveX-epsilon;
    if (moveX == distanceX || velocityCharacterX<epsilon) {
      velocityCharacterX = 0;
    } else {
      velocityCharacterX *= friction;
    }
  } else {  // backward
    distanceX = Float.NEGATIVE_INFINITY;
    if (velocityCharacterX<-epsilon) {
      for (int i = 0; i<x2.size(); i++) {
        if (y1.get(i)<=posCharacterY+characterRad && posCharacterY-characterRad<=y2.get(i) && x2.get(i)-posCharacterX+characterRad > distanceX && x2.get(i)<posCharacterX-characterRad) {
          distanceX = x2.get(i)-posCharacterX+characterRad;
        }
      }
      //  position & momentum update (x)
      float moveX = max(velocityCharacterX, distanceX);
      posCharacterX += moveX+epsilon;
      if (moveX == distanceX|| velocityCharacterX>-epsilon) {
        velocityCharacterX = 0;
      } else {
        velocityCharacterX *= friction;
      }
    }
  }
  //  hitbox check (y)
  if (velocityCharacterY>epsilon) {  //  upward
    distanceY = Float.POSITIVE_INFINITY;
    for (int i = 0; i<y1.size(); i++) {
      if (x1.get(i)<posCharacterX+characterRad && posCharacterX-characterRad<x2.get(i) && y1.get(i)-posCharacterY-characterRad < distanceY && y1.get(i) > posCharacterY+characterRad) {
        distanceY = y1.get(i)-posCharacterY-characterRad;
      }
    }
    float moveY = min(velocityCharacterY, distanceY);
    posCharacterY += moveY-epsilon;
    if (moveY == distanceY) {
      velocityCharacterY = 0;
    } else {
      velocityCharacterY -=1;
    }
  } else {  // downward
    distanceY = Float.NEGATIVE_INFINITY;
    if (velocityCharacterY<-epsilon) {
      for (int i = 0; i<y2.size(); i++) {
        if (x1.get(i)<posCharacterX+characterRad && posCharacterX-characterRad<x2.get(i) && y2.get(i)-posCharacterY+characterRad > distanceY && y2.get(i)<posCharacterY-characterRad) {
          distanceY = y2.get(i)-posCharacterY+characterRad;
        }
      }
    }
    //  position & momentum update (y)
    float moveY = max(velocityCharacterY, distanceY);
    posCharacterY += moveY+epsilon; // Add negative velocity to move up
    if (moveY == distanceY) {
      velocityCharacterY = 0; // Hit ceiling
      canJump = true; // Allow jumping again
    } else {
      velocityCharacterY -= 1.5;
    }
  }
}

void obstacleRendering() {
  float viewLeft = -posCameraX;
  float viewRight = -posCameraX + width;
  for (int i =0; i<x1.size(); i++) {
    if (x1.get(i)<=viewRight && x2.get(i)>=viewLeft) {
      rect(x1.get(i), y1.get(i), x2.get(i), y2.get(i));
    }
  }
}


void keyPressed() {
  if (key == 'a') keyA = true;
  if (key == 'd') keyD = true;
  if (key == ' ') keySpace = true;
  if (key == 'r') keyR = true;
  if (key == 'p') println(posCharacterX + ", " + posCharacterY);
}

void keyReleased() {
  if (key == 'a') keyA = false;
  if (key == 'd') keyD = false;
  if (key == ' ') keySpace = false;
  if (key == 'r') keyR = false;
}

void reset() {
  posCharacterX = 150;
  posCharacterY = 500;
  posCameraX = width*0.5;
  velocityCharacterX = 0;
  velocityCharacterY = 0;
  velocityCameraX = 0;
  friction = 0.9;
  keyA = false;
  keyD = false;
  keySpace = false;
  canJump = true;
  keyR = false;
  x1.clear();
  x2.clear();
  y1.clear();
  y2.clear();
  x1.append(50);
  x2.append(500);
  y1.append(50);
  y2.append(100);
}
