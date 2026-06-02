package gameplay

import menu.Settings
import processing.core.PApplet

class Camera(private val p: PApplet, private val settings: Settings, private var pos: Double) {
    private var velocity = 0f

    fun move(location: Double) {
        //velocity = location - pos + settings.cameraFocus TODO: verify
        pos += velocity * settings.cameraSmoothness
        p.translate((pos * p.width).toFloat(), p.height.toFloat())
        p.scale(settings.zoom, -settings.zoom)
    }
}