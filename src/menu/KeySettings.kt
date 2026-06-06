package menu

import Button
import Key
import ObjectManager
import Vector
import p
import processing.event.MouseEvent
import kotlin.reflect.full.declaredMemberProperties


class KeySettings(val objectManager: ObjectManager) { // TODO: finish implementation
    val buttons = ArrayList<Button>()
    var scrawl = 0f

    init {
        var variables = Key::class.declaredMemberProperties.toMutableList()
        variables.removeAt(0)
        for (i in variables.indices) {
            buttons.add(
                Button(
                    objectManager.cursor,
                    Vector(0.1, 0.1 + i * 0.1),
                    Vector(0.1, 0.08),
                    0xFFBBBBBB.toInt(),
                    false,
                    variables[i].name
                )
            )
        }
    }

    fun render() {
        p.translate(0f, scrawl)
        for (button in buttons) {
            button.render()
        }
    }

    fun mouseWheel(event: MouseEvent) {
        scrawl += event.getCount()
        //println(scrawl)
    }
}
