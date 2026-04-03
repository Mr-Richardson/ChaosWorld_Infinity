package io.github.richardson;

import io.github.richardson.menu.Settings;
import processing.core.PApplet;

public class Key {
    private final PApplet p;
    private Settings settings;
    private boolean right, left, jump;

    public Key(PApplet p, Settings settings) {
        this.p = p;
        this.settings = settings;
    }

    public void keyPressed() {
        switch (p.key) {
            case settings.getRight():
                right = true;
                break;
            case settings.getLeft():
                left = true;
                break;
            case settings.keyJump():
                jump = true;
                break;
        }
    }

    public void keyReleased() {
        switch (p.key) {
            case settings.getRight():
                right = false;
                break;
            case settings.getLeft():
                left = false;
                break;
            case settings.keyJump():
                jump = false;
                break;
        }
    }

    public boolean getRight() {
        return right;
    }

    public boolean getLeft() {
        return left;
    }

    public boolean getJump() {
        return jump;
    }
}