package io.github.richardson

import io.github.richardson.menu.Settings
import processing.core.PApplet

class Key(private val p: PApplet, private val settings: Settings) {
    var isRight: Boolean = false
        private set
    var isLeft: Boolean = false
        private set
    var isJump: Boolean = false
        private set

    fun keyPressed() {
        if (p.key == settings.keyRight) {
            this.isRight = true
        } else if (p.key == settings.keyLeft) {
            this.isLeft = true
        } else if (p.key == settings.keyJump) {
            this.isJump = true
        }
    }


    fun keyReleased() {
        if (p.key == settings.keyRight) {
            this.isRight = false
        } else if (p.key == settings.keyLeft) {
            this.isLeft = false
        } else if (p.key == settings.keyJump) {
            this.isJump = false
        }
    }
}
