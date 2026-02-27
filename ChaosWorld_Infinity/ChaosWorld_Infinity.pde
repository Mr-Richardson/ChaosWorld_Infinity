//  global variables
int seed, score;
int characterRad = 35;
int strengthJump = 30;
int deathY = -1000;
int mouseLastMoved = 0;
int mouseIdleTime = 5000;
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
boolean charachterRight;
boolean isCursorVisible = true;
boolean canJump;
boolean keyA;
boolean keyD;
boolean keySpace;
boolean keyR;
PGraphics Background;
PImage character;
PFont normal;
PFont italic;
JSONObject keySettings;

void setup() {
  //size(1000, 1000);
  fullScreen();
  frameRate(60);
  smooth(8);
  backgroundRendering();
  stroke(48, 96, 128);
  strokeWeight(2);
  fill(96, 192, 255);
  rectMode(CORNERS);
  ellipseMode(CORNERS);
  imageMode(CORNERS);
  character = loadImage("textures/characterTexture.png");
  normal = createFont("fonts/JetBrainsMono-VariableFont_wght.ttf", height * 0.03);
  italic = createFont("fonts/JetBrainsMono-Italic-VariableFont_wght.ttf", height * 0.3);
  textFont(normal);
  keySettings = new JSONObject();
  reset();
}

void draw() {
  key_inputs();
  generateObstacles();
  movementCharacter();
  image(Background, 0, 0);
  fill(255);
  textAlign(LEFT, BOTTOM);
  textSize(13);
  text(seed, 1, height - 1); //  seed printing
  textAlign(LEFT, TOP);
  textSize(height * 0.03);
  if (score < posCharacterX * 0.01) {
    score = (int) (posCharacterX * 0.01);
  }
  text(score, height * 0.01, height * 0.01); //  distance printing

  translate(posCameraX, height); //  camera alignment
  scale(1, -1);
  characterRendering();
  renderObstacles();
  velocityCameraX = posCameraX + posCharacterX - width * 0.3;
  posCameraX -= velocityCameraX * smoothnessCamera;
  if (posCharacterY < deathY || keyR) {
    reset();
  }
  hideCursor();
}



void key_inputs() {
  if (keyA) {
    if (canJump) {
      velocityCharacterX -= speedCharacterX; //  backward
    } else {
      velocityCharacterX -= speedCharacterX / airInertia; //  backward (airborne)
    }
  }
  if (keyD) {
    if (canJump) {
      velocityCharacterX += speedCharacterX; //  forward
    } else {
      velocityCharacterX += speedCharacterX / airInertia; //  forward (airborne)
    }
  }
  if (keySpace && canJump) { //  jump
    velocityCharacterY += strengthJump;
    canJump = false;
  }
}

void movementCharacter() {
  //  variables
  float distanceX;
  float distanceY;
  //  hitbox check (x)
  if (velocityCharacterX > epsilon) { //  forward
    distanceX = Float.POSITIVE_INFINITY;
    for (int i = 0; i < obstacles.size(); i++) {
      Obstacle o = obstacles.get(i);
      if (o.y1 <= posCharacterY + characterRad
          && posCharacterY - characterRad <= o.y2
          && o.x1 - posCharacterX - characterRad < distanceX
          && o.x1 > posCharacterX + characterRad) {
        distanceX = o.x1 - posCharacterX - characterRad;
      }
    }
    //  position & momentum update (x)
    float moveX = min(velocityCharacterX, distanceX);
    posCharacterX += moveX - epsilon;
    if (moveX == distanceX || velocityCharacterX < epsilon) {
      velocityCharacterX = 0;
    } else {
      if (canJump) {
        velocityCharacterX *= friction;
      } else {
        velocityCharacterX *= (friction + airInertia - 1) / airInertia;
      }
    }
  } else { // backward
    distanceX = Float.NEGATIVE_INFINITY;
    if (velocityCharacterX < -epsilon) {
      for (int i = 0; i < obstacles.size(); i++) {
        Obstacle o = obstacles.get(i);
        if (o.y1 <= posCharacterY + characterRad
            && posCharacterY - characterRad <= o.y2
            && o.x2 - posCharacterX + characterRad > distanceX
            && o.x2 < posCharacterX - characterRad) {
          distanceX = o.x2 - posCharacterX + characterRad;
        }
      }
      //  position & momentum update (x)
      float moveX = max(velocityCharacterX, distanceX);
      posCharacterX += moveX + epsilon;
      if (moveX == distanceX || velocityCharacterX > -epsilon) {
        velocityCharacterX = 0;
      } else {
        if (canJump) {
          velocityCharacterX *= friction;
        } else {
          velocityCharacterX *= (friction + airInertia - 1) / airInertia;
        }
      }
    }
  }
  //  hitbox check (y)
  if (velocityCharacterY > epsilon) { //  upward
    distanceY = Float.POSITIVE_INFINITY;
    for (int i = 0; i < obstacles.size(); i++) {
      Obstacle o = obstacles.get(i);
      if (o.x1 < posCharacterX + characterRad
          && posCharacterX - characterRad < o.x2
          && o.y1 - posCharacterY - characterRad < distanceY
          && o.y1 > posCharacterY + characterRad) {
        distanceY = o.y1 - posCharacterY - characterRad;
      }
    }
    float moveY = min(velocityCharacterY, distanceY);
    posCharacterY += moveY - epsilon;
    if (moveY == distanceY) {
      velocityCharacterY = 0;
    } else {
      velocityCharacterY -= 1;
    }
  } else { // downward
    distanceY = Float.NEGATIVE_INFINITY;
    if (velocityCharacterY < -epsilon) {
      for (int i = 0; i < obstacles.size(); i++) {
        Obstacle o = obstacles.get(i);
        if (o.x1 < posCharacterX + characterRad
            && posCharacterX - characterRad < o.x2
            && o.y2 - posCharacterY + characterRad > distanceY
            && o.y2 < posCharacterY - characterRad) {
          distanceY = o.y2 - posCharacterY + characterRad;
        }
      }
    }
    //  position & momentum update (y)
    float moveY = max(velocityCharacterY, distanceY);
    posCharacterY += moveY + epsilon; // Add negative velocity to move down
    if (moveY == distanceY) {
      velocityCharacterY = 0; // Hit floor
      canJump = true; // Allow jumping again
    } else {
      velocityCharacterY -= 1.5;
    }
  }
}

void characterRendering() {
  pushMatrix();
  translate(posCharacterX, posCharacterY);
  if (!charachterRight) {
    scale(-1, -1);
  } else {
    scale(1, -1);
  }
  image(character, -characterRad, characterRad, characterRad, -characterRad);
  popMatrix();
}

void hideCursor() {
  if (mouseX != pmouseX || mouseY != pmouseY) {
    mouseLastMoved = millis();
    if (!isCursorVisible) {
      cursor(ARROW);
      isCursorVisible = true;
    }
  } else {
    if (isCursorVisible && millis() - mouseLastMoved > mouseIdleTime) {
      noCursor();
      isCursorVisible = false;
    }
  }
}

void keyPressed() {
  if (key == 'a') {
    keyA = true;
    charachterRight = false;
  }
  if (key == 'd') {
    keyD = true;
    charachterRight = true;
  }
  if (key == ' ') keySpace = true;
  if (key == 'r') keyR = true;
  if (key == 'p') textFont(italic);
  if (key == 'o') textFont(normal);
}

void keyReleased() {
  switch (key) {
    case 'a':
      keyA = false;
      if (keyD) charachterRight = true;
      break;
    case 'd':
      keyD = false;
      if (keyA) charachterRight = false;
      break;
    case ' ':
      keySpace = false;
      break;
    case 'r':
      keyR = false;
      break;
  }
}

void reset() {
  seed = (int) random(Integer.MAX_VALUE);
  print(seed);
  score = 0;
  posCharacterX = 150;
  posCharacterY = 500;
  posCameraX = width * 0.5;
  velocityCharacterX = 0;
  velocityCharacterY = 0;
  velocityCameraX = 0;
  charachterRight = true;
  canJump = true;
  keyA = false;
  keyD = false;
  keySpace = false;
  keyR = false;
  obstacles.clear();
  obstacles.add(new Obstacle(50, 0, 500, 100));
  randomSeed(seed);
}
