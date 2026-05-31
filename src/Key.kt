import menu.Settings
import processing.core.PApplet

class Key(private val p: PApplet, private val settings: Settings) {
    var isRight: Boolean = false
        private set
    var isLeft: Boolean = false
        private set
    var isJump: Boolean = false
        private set

    fun keyPressed() {
        when (p.key) {
            settings.keyRight -> {
                this.isRight = true
            }
            settings.keyLeft -> {
                this.isLeft = true
            }
            settings.keyJump -> {
                this.isJump = true
            }
        }
    }


    fun keyReleased() {
        when (p.key) {
            settings.keyRight -> {
                this.isRight = false
            }
            settings.keyLeft -> {
                this.isLeft = false
            }
            settings.keyJump -> {
                this.isJump = false
            }
        }
    }
}
