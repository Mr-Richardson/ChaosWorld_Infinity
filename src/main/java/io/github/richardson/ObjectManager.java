package io.github.richardson;

import io.github.richardson.menu.Settings;
import processing.core.PApplet;

public class ObjectManager {
    public final Settings settings;
    public final Key key;
    public final Cursor cursor;
    public final Font font;
    private final PApplet p;

    public ObjectManager(PApplet p) {
        this.p = p;
        this.settings = new Settings();
        this.key = new Key(p, settings);
        this.cursor = new Cursor(p, 3000, true);
        this.font = new Font(p, "fonts/JetBrainsMono-VariableFont_wght.ttf", 32);
    }
}