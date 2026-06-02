package gameplay

import ObjectManager
import p
import processing.core.PApplet

class Ui(private val objectManager: ObjectManager) {
    init {
        objectManager.font.apply()
    }

    fun render(seed: Long, score: Int) {
        p.resetMatrix()
        p.fill(255)
        // seed rendering
        p.textAlign(PApplet.LEFT, PApplet.BOTTOM)
        p.textSize(12f * objectManager.settings.uiScale)
        p.text(
            seed.toString(),
            1f * objectManager.settings.uiScale,
            (p.height - 1).toFloat() * objectManager.settings.uiScale
        )
        // score rendering
        p.textAlign(PApplet.LEFT, PApplet.TOP)
        p.textSize(30f * objectManager.settings.uiScale)
        p.text(score, 10 * objectManager.settings.uiScale, 10 * objectManager.settings.uiScale) //  distance printing
    }
}