package io.github.richardson;

import io.github.richardson.menu.Settings;
import lombok.Getter;
import processing.core.PApplet;

@Getter
public class Key {
    private final PApplet p;
    private Settings settings;
    private boolean right, left, jump;

    public Key(PApplet p, Settings settings) {
        this.p = p;
        this.settings = settings;
    }

    public void keyPressed() {
        if (p.key == settings.getKeyRight()) {
            right = true;
        } else if (p.key == settings.getKeyLeft()) {
            left = true;
        } else if (p.key == settings.getKeyJump()) {
            jump = true;
        }
    }


    public void keyReleased() {
        if (p.key == settings.getKeyRight()) {
            right = false;
        } else if (p.key == settings.getKeyLeft()) {
            left = false;
        } else if (p.key == settings.getKeyJump()) {
            jump = false;
        }
    }
}
