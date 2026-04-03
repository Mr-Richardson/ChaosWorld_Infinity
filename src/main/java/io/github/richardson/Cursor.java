package io.github.richardson;

import processing.core.PApplet;

public class Cursor {
    private final PApplet p;
    boolean isCursorVisible;
    private int mouseIdleTime;
    private int mouseLastMoved = 0;

    public Cursor(PApplet p, int mouseIdleTime, boolean isCursorVisible) {
        this.p = p;
        this.mouseIdleTime = mouseIdleTime;
        this.isCursorVisible = isCursorVisible;
    }

    public void hideCheck() {
        if (p.mouseX != p.pmouseX || p.mouseY != p.pmouseY) {
            mouseLastMoved = p.millis();
            if (!isCursorVisible) {
                p.cursor(PApplet.ARROW);
                isCursorVisible = true;
            }
        } else {
            if (isCursorVisible && p.millis() - mouseLastMoved >= mouseIdleTime) {
                p.noCursor();
                isCursorVisible = false;
            }
        }
    }

    public boolean isCursorVisible() {
        return isCursorVisible;
    }
}