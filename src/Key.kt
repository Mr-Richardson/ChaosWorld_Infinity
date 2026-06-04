import menu.Settings

class Key(private val settings: Settings) {
    var isRight = false
    var isLeft = false
    var isJump = false
    var isMore = false
    var isLess = false
    var isReset = false
    var isZoom = false
    var isCtrl = false

    fun keyPressed() {
        when (p.keyCode) {
            settings.keyRight -> isRight = true
            settings.keyLeft -> isLeft = true
            settings.keyJump -> isJump = true
            settings.keyMore -> isMore = true
            settings.keyLess -> isLess = true
            settings.keyReset -> isReset = true
            settings.keyZoom -> isZoom = true
            settings.keyCtrl -> isCtrl = true
        }
//        println(p.keyCode)
    }

    fun keyReleased() {
        when (p.keyCode) {
            settings.keyRight -> isRight = false
            settings.keyLeft -> isLeft = false
            settings.keyJump -> isJump = false
            settings.keyMore -> isMore = false
            settings.keyLess -> isLess = false
            settings.keyReset -> isReset = false
            settings.keyZoom -> isZoom = false
            settings.keyCtrl -> isCtrl = false
        }
    }
}
