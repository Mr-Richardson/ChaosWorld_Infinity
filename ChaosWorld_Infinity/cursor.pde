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
