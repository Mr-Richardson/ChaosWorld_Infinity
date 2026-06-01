package gameplay

import ObjectManager
import processing.core.PApplet

class Ui(private val p: PApplet, private val objectManager: ObjectManager) { // TODO implement Ui class
    fun render(seed: Int, score: Int) {
        p.fill(255)
        p.textAlign(PApplet.LEFT, PApplet.BOTTOM)
        p.textSize(13f * objectManager.settings.uiScale)
        objectManager.font.apply()
        p.text(seed, 1f * objectManager.settings.uiScale, (p.height - 1).toFloat() * objectManager.settings.uiScale) //  seed printing
        p.textAlign(PApplet.LEFT, PApplet.TOP)
        p.textSize(0.03f * objectManager.settings.uiScale)
        p.text(score, 10 * objectManager.settings.uiScale, p.height * objectManager.settings.uiScale) //  distance printing
    }
}