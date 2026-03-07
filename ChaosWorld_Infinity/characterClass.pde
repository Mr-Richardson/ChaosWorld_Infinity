Character player = new Character(35, 30, 1.2, 5.0, 150.0, 500.0);

PImage character;

class Character {
  private int radius;
  public int maxJump;
  public float posX, posY, maxSpeed, velocityX, velocityY, airInertia;
  public boolean canJump, right;

  public Character(int radius, int maxJump, float maxSpeed, float airInertia, float x, float y) {
    this.radius = radius;
    this.maxJump = maxJump;
    this.maxSpeed = maxSpeed;
    this.airInertia = airInertia;
    this.posX = x;
    this.posY = y;
  }

  void movement() {
    //  variables
    float distanceX;
    float distanceY;
    //  hitbox check (x)
    if (velocityX > epsilon) { //  forward
      distanceX = Float.POSITIVE_INFINITY;
      for (int i = 0; i < obstacles.size(); i++) {
        Obstacle o = obstacles.get(i);
        if (o.y1 <= posY + radius
            && posY - radius <= o.y2
            && o.x1 - posX - radius < distanceX
            && o.x1 > posX + radius) {
          distanceX = o.x1 - posX - radius;
        }
      }
      //  position & momentum update (x)
      float moveX = min(velocityX, distanceX);
      posX += moveX - epsilon;
      if (moveX == distanceX || velocityX < epsilon) {
        velocityX = 0;
      } else {
        if (canJump) {
          velocityX *= friction;
        } else {
          velocityX *= (friction + airInertia - 1) / airInertia;
        }
      }
    } else { // backward
      distanceX = Float.NEGATIVE_INFINITY;
      if (velocityX < -epsilon) {
        for (int i = 0; i < obstacles.size(); i++) {
          Obstacle o = obstacles.get(i);
          if (o.y1 <= posY + radius
              && posY - radius <= o.y2
              && o.x2 - posX + radius > distanceX
              && o.x2 < posX - radius) {
            distanceX = o.x2 - posX + radius;
          }
        }
        //  position & momentum update (x)
        float moveX = max(velocityX, distanceX);
        posX += moveX + epsilon;
        if (moveX == distanceX || velocityX > -epsilon) {
          velocityX = 0;
        } else {
          if (canJump) {
            velocityX *= friction;
          } else {
            velocityX *= (friction + airInertia - 1) / airInertia;
          }
        }
      }
    }
    //  hitbox check (y)
    if (velocityY > epsilon) { //  upward
      distanceY = Float.POSITIVE_INFINITY;
      for (int i = 0; i < obstacles.size(); i++) {
        Obstacle o = obstacles.get(i);
        if (o.x1 < posX + radius
            && posX - radius < o.x2
            && o.y1 - posY - radius < distanceY
            && o.y1 > posY + radius) {
          distanceY = o.y1 - posY - radius;
        }
      }
      float moveY = min(velocityY, distanceY);
      posY += moveY - epsilon;
      if (moveY == distanceY) {
        velocityY = 0;
      } else {
        velocityY -= 1;
      }
    } else { // downward
      distanceY = Float.NEGATIVE_INFINITY;
      if (velocityY < -epsilon) {
        for (int i = 0; i < obstacles.size(); i++) {
          Obstacle o = obstacles.get(i);
          if (o.x1 < posX + radius
              && posX - radius < o.x2
              && o.y2 - posY + radius > distanceY
              && o.y2 < posY - radius) {
            distanceY = o.y2 - posY + radius;
          }
        }
      }
      //  position & momentum update (y)
      float moveY = max(velocityY, distanceY);
      posY += moveY + epsilon; // Add negative velocity to move down
      if (moveY == distanceY) {
        velocityY = 0; // Hit floor
        canJump = true; // Allow jumping again
      } else {
        velocityY -= 1.5;
      }
    }
  }

  void render() {
    pushMatrix();
    translate(posX, posY);
    if (!right) {
      scale(-1, -1);
    } else {
      scale(1, -1);
    }
    image(character, -radius, radius, radius, -radius);
    popMatrix();
  }
}
