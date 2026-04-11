package io.github.richardson.gameplay;

import processing.core.PApplet;
import processing.core.PGraphics;

public class Background {
    private final PApplet p;
    private final PGraphics bg;

    public Background(PApplet p, int c1, int c2) {
        this.p = p;
        this.bg = p.createGraphics(p.width, p.height);
        this.bg.beginDraw();
        this.bg.noFill();
        for (int i = 0; i <= p.height; i++) {
            this.bg.stroke(p.lerpColor(c1, c2, (float) Math.sqrt((float) i / p.height)));
            this.bg.line(0, i, p.width, i);
        }
        this.bg.endDraw();
    }

    public void render() {
        p.image(bg, 0, 0);
        //java.lang.System.out.println("rendering background (" + p.millis() + "ms)");
    }
}