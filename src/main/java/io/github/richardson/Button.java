package io.github.richardson;

import processing.core.PApplet;

public class Button {
    private final PApplet p;
    private final Cursor cursor;
    private final boolean ellipse;
    private int xPos, yPos, w, h, color;

    public Button(PApplet p, Cursor cursor, int xPos, int yPos, int w, int h, int color, boolean ellipse) {
        this.p = p;
        this.cursor = cursor;
        this.xPos = xPos;
        this.yPos = yPos;
        this.w = w;
        this.h = h;
        this.color = color;
        this.ellipse = ellipse;
    }

    public void render() {
        p.fill(color);
        if (ellipse) {
            p.ellipseMode(PApplet.CENTER);
            p.ellipse(xPos, yPos, w, h);
        } else {
            p.rectMode(PApplet.CENTER);
            p.rect(xPos, yPos, w, h);
        }
    }

    public boolean hovered() {
        boolean collide;
        float w2 = w * 0.5f;
        float h2 = h * 0.5f;
        if (ellipse) {
            collide = 1 >= (p.mouseX - xPos) * (p.mouseX - xPos) / (w2 * w2) + (p.mouseY - yPos) * (p.mouseY - yPos) / (h2 * h2);
        } else {
            collide = Math.abs(p.mouseX - xPos) < w2 && Math.abs(p.mouseY - yPos) < h2;
        }
        return collide && cursor.isCursorVisible();
    }

    public boolean pressed() {
        return hovered() && p.mousePressed;
    }
}
