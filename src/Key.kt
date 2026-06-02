import menu.Settings

class Key(private val settings: Settings) {
    var isRight = false
    var isLeft = false
    var isJump = false
    var isReset = false
    var isZoomIn = false
    var isZoomOut = false

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

            settings.keyReset -> {
                this.isReset = true
            }

            settings.keyZoomIn -> {
                this.isZoomIn = true
            }

            settings.keyZoomOut -> {
                this.isZoomOut = true
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

            settings.keyReset -> {
                this.isReset = false
            }

            settings.keyZoomIn -> {
                this.isZoomIn = false
            }

            settings.keyZoomOut -> {
                this.isZoomOut = false
            }
        }
    }
}
