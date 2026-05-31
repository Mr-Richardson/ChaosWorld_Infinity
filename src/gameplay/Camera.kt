package gameplay

import menu.Settings
import processing.core.PApplet

class Camera(private val p: PApplet, private val settings: Settings, private var pos: Float) {
    private var velocity = 0f

    fun move(location: Float) {
        velocity = pos + location - p.width * settings.cameraFocus
        pos -= velocity * settings.cameraSmoothness
        p.translate(pos, p.height.toFloat())
        p.scale(1f, -1f)
    }
}