void keyPressed() {
  switch (key) {
    case 'a':
      keyA = true;
      player.right = false;
      break;
    case 'd':
      keyD = true;
      player.right = true;
      break;
    case ' ':
      keySpace = true;
      break;
    case 'r':
      keyR = true;
      break;
    case 'p':
      textFont(italic);
      break;
    case 'o':
      textFont(normal);
      break;
  }
}

void keyReleased() {
  switch (key) {
    case 'a':
      keyA = false;
      if (keyD) player.right = true;
      break;
    case 'd':
      keyD = false;
      if (keyA) player.right = false;
      break;
    case ' ':
      keySpace = false;
      break;
    case 'r':
      keyR = false;
      break;
  }
}

void key_inputs() {
  if (keyA) {
    if (player.canJump) {
      player.velocityX -= player.maxSpeed; //  backward
    } else {
      player.velocityX -= player.maxSpeed / player.airInertia; //  backward (airborne)
    }
  }
  if (keyD) {
    if (player.canJump) {
      player.velocityX += player.maxSpeed; //  forward
    } else {
      player.velocityX += player.maxSpeed / player.airInertia; //  forward (airborne)
    }
  }
  if (keySpace && player.canJump) { //  jump
    player.velocityY += player.maxJump;
    player.canJump = false;
  }
}
