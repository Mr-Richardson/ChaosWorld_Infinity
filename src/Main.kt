import gameover.Gameover
import gameplay.Gameplay
import menu.Menu
import processing.core.PApplet

class Main : PApplet() {
    var status: State = State.GAME
    var objectManager: ObjectManager = ObjectManager(this)
    var menu: Menu = Menu(this)
    var gameplay: Gameplay = Gameplay(this, objectManager)
    var gameover: Gameover = Gameover(this)

    override fun setup() {
        frameRate(60f)
    }

    override fun settings() {
        //fullScreen()
        size(1600, 900)
        smooth(8)
    }

    override fun draw() {
        when (status) {
            State.MENU -> menu.main()
            State.GAME -> {
                gameplay.main()
            }

            State.GAMEOVER -> gameover.main()
        }
    }

    override fun keyPressed() {
        objectManager.key.keyPressed()
    }

    override fun keyReleased() {
        objectManager.key.keyReleased()
    }

    enum class State {
        MENU,
        GAME,
        GAMEOVER
    }
}

fun main() {
    PApplet.main("Main")
}