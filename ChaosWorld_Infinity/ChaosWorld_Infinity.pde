enum State {
  MENU,
  GAME,
  GAMEOVER
}

int mouseLastMoved = 0;
int mouseIdleTime = 5000;
boolean isCursorVisible = true;
State status = State.MENU;

void setup() {
  // size(1000, 1000);
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
  normal = createFont("fonts/JetBrainsMono-VariableFont_wght.ttf", 256);
  italic = createFont("fonts/JetBrainsMono-Italic-VariableFont_wght.ttf", 256);
  textFont(normal);
  keySettings = new JSONObject();
  resetGame();
}

void draw() {
  switch (status) {
    case MENU:
      menu();
      break;
    case GAME:
      gameplay();
      break;
    case GAMEOVER:
      gameover();
      break;
  }
}
