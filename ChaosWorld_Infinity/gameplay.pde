void gameplay() {
  key_inputs();
  generateObstacles();
  player.movement();
  image(Background, 0, 0);
  fill(255);
  textAlign(LEFT, BOTTOM);
  textSize(13);
  text(seed, 1, height - 1); //  seed printing
  textAlign(LEFT, TOP);
  textSize(height * 0.03);
  if (score < player.posX * 0.01) {
    score = (int) (player.posX * 0.01);
  }
  text(score, height * 0.01, height * 0.01); //  distance printing

  translate(posCameraX, height); //  camera alignment
  scale(1, -1);
  player.render();
  renderObstacles();
  velocityCameraX = posCameraX + player.posX - width * 0.3;
  posCameraX -= velocityCameraX * smoothnessCamera;
  if (player.posY < deathY || keyR) {
    resetGame();
  }
  hideCursor();
}

void resetGame() {
  seed = (int) random(Integer.MAX_VALUE);
  print(seed);
  score = 0;
  player.posX = 150;
  player.posY = 500;
  posCameraX = width * 0.5;
  player.velocityX = 0;
  player.velocityY = 0;
  velocityCameraX = 0;
  player.right = true;
  player.canJump = true;
  keyA = false;
  keyD = false;
  keySpace = false;
  keyR = false;
  obstacles.clear();
  obstacles.add(new Obstacle(50, 0, 500, 100));
  randomSeed(seed);
}
