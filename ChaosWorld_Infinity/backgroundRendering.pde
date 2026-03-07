PGraphics Background;

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
