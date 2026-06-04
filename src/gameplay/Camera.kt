package gameplay

import menu.Settings
import p

class Camera(private val settings: Settings, var pos: Double) {
    fun move(location: Double) {
        pos += (location - settings.cameraFocus - pos) * settings.cameraSmoothness
        p.scale(settings.zoom, -settings.zoom)
        p.translate((-pos * p.width).toFloat(), (p.height / settings.zoom * -0.9f))
    }
}