package io.github.richardson

import io.github.richardson.gameover.Gameover
import io.github.richardson.gameplay.Gameplay
import io.github.richardson.menu.Menu
import processing.core.PApplet

class Main : PApplet() {
    var status: State = State.GAME
    var objectManager: ObjectManager? = null
    var menu: Menu? = null
    var gameplay: Gameplay? = null
    var gameover: Gameover? = null

    override fun setup() {
        frameRate(60f)
        objectManager = ObjectManager(this)
        menu = Menu(this)
        gameplay = Gameplay(this, objectManager)
        gameover = Gameover(this)
    }

    override fun settings() {
        fullScreen()
        //size(800, 600);
        smooth(8)
    }

    override fun draw() {
        when (status) {
            State.MENU -> menu!!.main()
            State.GAME -> {
                gameplay!!.render() //gameplay.main();
                gameplay!!.physic()
            }

            State.GAMEOVER -> gameover!!.main()
        }
    }

    override fun keyPressed() {
        objectManager!!.key.keyPressed()
    }

    override fun keyReleased() {
        objectManager!!.key.keyReleased()
    }

    internal enum class State {
        MENU,
        GAME,
        GAMEOVER
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            main("io.github.richardson.Main")
        }
    }
}