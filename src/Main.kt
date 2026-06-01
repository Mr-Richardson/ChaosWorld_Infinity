import gameover.Gameover
import gameplay.Gameplay
import menu.Menu
import processing.core.PApplet

class Main : PApplet() {
    var status: State = State.GAME
    lateinit var objectManager: ObjectManager
    lateinit var menu: Menu
    lateinit var gameplay: Gameplay
    lateinit var gameover: Gameover

    override fun setup() {
        frameRate(60f)
        windowResizable(true)
        objectManager = ObjectManager(this)
        menu = Menu(this)
        gameplay = Gameplay(this, objectManager)
        gameover = Gameover(this)
    }

    override fun settings() {
        //fullScreen()
        size(1200, 675, P2D)
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