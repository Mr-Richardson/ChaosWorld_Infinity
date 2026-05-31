package io.github.richardson

import processing.core.PApplet

class Cursor(private val p: PApplet, private val mouseIdleTime: Int, var isCursorVisible: Boolean) {
    private var mouseLastMoved = 0

    fun hideCheck() {
        if (p.mouseX != p.pmouseX || p.mouseY != p.pmouseY) {
            mouseLastMoved = p.millis()
            if (!isCursorVisible) {
                p.cursor(PApplet.ARROW)
                isCursorVisible = true
            }
        } else {
            if (isCursorVisible && p.millis() - mouseLastMoved >= mouseIdleTime) {
                p.noCursor()
                isCursorVisible = false
            }
        }
    }
}