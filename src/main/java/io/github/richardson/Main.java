package io.github.richardson;

import io.github.richardson.gameover.Gameover;
import io.github.richardson.gameplay.Gameplay;
import io.github.richardson.menu.Menu;
import processing.core.PApplet;

public class Main extends PApplet {
    State status = State.GAME;
    ObjectManager objectManager;
    Menu menu;
    Gameplay gameplay;
    Gameover gameover;

    public static void main(String[] args) {
        PApplet.main("io.github.richardson.Main");
    }

    @Override
    public void setup() {
        frameRate(60);
        objectManager = new ObjectManager(this);
        menu = new Menu(this);
        gameplay = new Gameplay(this, objectManager);
        gameover = new Gameover(this);
    }

    public void settings() {
        fullScreen();
        //size(800, 600);
        smooth(8);
    }

    @Override
    public void draw() {
        switch (status) {
            case MENU:
                menu.main();
                break;
            case GAME:
                gameplay.render();//gameplay.main();
                gameplay.physic();
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