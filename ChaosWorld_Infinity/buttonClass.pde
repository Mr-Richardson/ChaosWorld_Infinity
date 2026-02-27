// Button test = new Button(500, 500, 200, 100, color(255, 0, 0), false);

class Button {
  int xPos, yPos, w, h;
  color c;
  boolean ellipse, hovered;

  Button(int xPos, int yPos, int w, int h, color c, boolean ellipse) {
    this.xPos = xPos;
    this.yPos = yPos;
    this.w = w;
    this.h = h;
    this.c = c;
    this.ellipse = ellipse;
  }

  void render() {
    fill(c);
    if (ellipse) {
      ellipse(xPos - w * 0.5, yPos - h * 0.5, xPos + w * 0.5, yPos + h * 0.5);
    } else {
      rect(xPos - w * 0.5, yPos - h * 0.5, xPos + w * 0.5, yPos + h * 0.5);
    }
  }

  boolean hovered() {
    boolean collide;
    if (ellipse) {
      float distortedMouseY = (mouseY - yPos) * (w / h) + yPos;
      collide = dist(posCameraX, distortedMouseY, xPos, yPos) < w * 0.5;
    } else {
      collide =
          mouseX < xPos + w * 0.5
              && mouseX > xPos - w * 0.5
              && mouseY < yPos + h * 0.5
              && mouseY > yPos - h * 0.5;
    }
    return collide && isCursorVisible;
  }

  boolean pressed() {
    boolean pressed = hovered() && mousePressed;
    return pressed;
  }
}
