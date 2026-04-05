package io.github.richardson;

import io.github.richardson.gameover.Gameover;
import io.github.richardson.gameplay.Gameplay;
import io.github.richardson.menu.Menu;
import processing.core.PApplet;

public class Main extends PApplet {
    State status = State.MENU;
    ObjectManager objectManager = new ObjectManager(this);
    Menu menu = new Menu(this);
    Gameplay gameplay = new Gameplay(this, objectManager);
    Gameover gameover = new Gameover(this);

    public static void main(String[] args) {
        PApplet.main("io.github.richardson.Main");
    }

    @Override
    public void setup() {
        // size(1000, 1000);
        fullScreen();
        frameRate(60);
        smooth(8);
    }

    @Override
    public void draw() {
        switch (status) {
            case MENU:
                menu.main();
                break;
            case GAME:
                gameplay.main();
                break;
            case GAMEOVER:
                gameover.main();
                break;
        }
    }

    @Override
    public void keyPressed() {
        objectManager.key.keyPressed();
    }

    @Override
    public void keyReleased() {
        objectManager.key.keyReleased();
    }

    enum State {
        MENU,
        GAME,
        GAMEOVER
    }
}