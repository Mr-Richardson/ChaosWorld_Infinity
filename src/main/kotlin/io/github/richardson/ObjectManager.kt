package io.github.richardson

import io.github.richardson.menu.Settings
import processing.core.PApplet

class ObjectManager(p: PApplet) {
    @JvmField
    val settings: Settings
    @JvmField
    val key: Key
    @JvmField
    val cursor: Cursor
    @JvmField
    val font: Font
    private val p: PApplet?

    init {
        this.p = p
        this.settings = Settings()
        this.key = Key(p, settings)
        this.cursor = Cursor(p, 3000, true)
        this.font = Font(p, "fonts/JetBrainsMono-VariableFont_wght.ttf", 32f)
    }
}