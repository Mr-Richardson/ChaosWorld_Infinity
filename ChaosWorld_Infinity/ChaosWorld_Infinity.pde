//  global variables
int seed;
int characterRad = 35;
int strengthJump = 30;
int deathY = -1000;
int obstacleDistanceMin = -50;
int obstacleDistanceMax = 600;
int obstacleHeightMin = 0;
int obstacleHeightMax = 300;
int obstacleWidthMin = 10;
int obstacleWidthMax = 600;
int obstacleThicknessMin = 10;
int obstacleThicknessMax = 400;
float epsilon = 0.001;
float posCharacterX;
float posCharacterY;
float posCameraX;
float speedCharacterX = 1.2;
float velocityCharacterX;
float velocityCharacterY;
float velocityCameraX;
float smoothnessCamera = 0.02;
float friction = 0.9;
float airInertia = 5;
boolean canJump;
boolean keyA;
boolean keyD;
boolean keySpace;
boolean keyR;
FloatList x1;
FloatList x2;
FloatList y1;
FloatList y2;
PGraphics Background;
PGraphics character;

void setup() {
  //size(1000, 1000);
  fullScreen();
  smooth(8);
  backgroundRendering();
  stroke(48, 96, 128);
  strokeWeight(2);
  fill(96, 192, 255);
  rectMode(CORNERS);
  imageMode(CORNERS);
  x1 = new FloatList();
  x2 = new FloatList();
  y1 = new FloatList();
  y2 = new FloatList();
  character = loadImage("assets/textures/characterTexture.png");
  reset();
}

void draw() {
  //  key inputs
  key_inputs();
  // obstacle generation and removal
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
  image(character, posCharacterX-characterRad, posCharacterY+characterRad, posCharacterX+characterRad, posCharacterY-characterRad);
  // obstacles
  obstacleRendering();
  //  camera movement
  velocityCameraX = posCameraX+posCharacterX-width*0.3;
  posCameraX -= velocityCameraX*smoothnessCamera;
  // program reset
  if (posCharacterY < deathY || keyR) {
    reset();
  }
}

void backgroundRendering() {
  Background = createGraphics(width, height);
  Background.beginDraw();
  Background.noFill();
  for (int i = 0; i <= height; i++) {
    Background.stroke(lerpColor(color(0, 50, 200), color(0), pow(map(i, 0, height, 0, 1), 0.5)));
    Background.line(0, i, width, i);
  }
  Background.endDraw();
}

void key_inputs() {
  if (keyA) {
    if (canJump) {
      velocityCharacterX-=speedCharacterX;  //  backward
    } else {
      velocityCharacterX-=speedCharacterX/airInertia;  //  backward (airborne)
    }
  }
  if (keyD) {
    if (canJump) {
      velocityCharacterX+=speedCharacterX;  //  forward
    } else {
      velocityCharacterX+=speedCharacterX/airInertia;  //  forward (airborne)
    }
  }
  if (keySpace && canJump) {  //  jump
    velocityCharacterY+=strengthJump;
    canJump = false;
  }
}

void obstacleGeneration() {
  // generation
  while (x2.get(x2.size()-1)-posCharacterX <= width) {
    x1.append(x2.get(x2.size()-1)+random(obstacleDistanceMin, obstacleDistanceMax));
    y1.append(random(obstacleHeightMin, obstacleHeightMax));
    x2.append(x1.get(x1.size()-1)+random(obstacleWidthMin, obstacleWidthMax));
    y2.append(y1.get(y1.size()-1)+random(obstacleThicknessMin, obstacleThicknessMax));
  }
  // removal
  while (x2.get(1)-posCharacterX < -width) {
    x1.remove(0);
    x2.remove(0);
    y1.remove(0);
    y2.remove(0);
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
      if (canJump) {
        velocityCharacterX *= friction;
      } else {
        velocityCharacterX *= (friction+airInertia-1)/airInertia;
      }
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
        if (canJump) {
          velocityCharacterX *= friction;
        } else {
          velocityCharacterX *= (friction+airInertia-1)/airInertia;
        }
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
    posCharacterY += moveY+epsilon; // Add negative velocity to move down
    if (moveY == distanceY) {
      velocityCharacterY = 0; // Hit floor
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
  seed = (int)random(Integer.MAX_VALUE);
  print(seed);
  posCharacterX = 150;
  posCharacterY = 500;
  posCameraX = width*0.5;
  velocityCharacterX = 0;
  velocityCharacterY = 0;
  velocityCameraX = 0;
  canJump = true;
  keyA = false;
  keyD = false;
  keySpace = false;
  keyR = false;
  x1.clear();
  x2.clear();
  y1.clear();
  y2.clear();
  x1.append(50);
  x2.append(500);
  y1.append(0);
  y2.append(100);
  randomSeed(seed);
}
