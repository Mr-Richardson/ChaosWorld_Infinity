package menu

import ObjectManager

class Menu(objectManager: ObjectManager) {
    val keySettings = KeySettings(objectManager)

    fun main() {
        keySettings.render()
    }
}