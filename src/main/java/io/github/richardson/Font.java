package io.github.richardson;

import processing.core.PApplet;
import processing.core.PFont;

public class Font {
    private final PApplet p;
    private final PFont font;


    Font(PApplet p, String path, float size) {
        this.p = p;
        font = p.createFont(path, size, true);
    }

    public void apply() {
        p.textFont(font);
    }
}
