import gameover.Gameover
import gameplay.Gameplay
import menu.Menu
import processing.core.PApplet

lateinit var p: PApplet

class Main : PApplet() {
    var status: State = State.GAME
    lateinit var objectManager: ObjectManager
    lateinit var menu: Menu
    lateinit var gameplay: Gameplay
    lateinit var gameover: Gameover

    override fun setup() {
        surface.setTitle("ChaosWorld:Infinity")
        frameRate(60f)
        windowResizable(true)
        p = this
        objectManager = ObjectManager()
        menu = Menu()
        gameplay = Gameplay(objectManager)
        gameover = Gameover()
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