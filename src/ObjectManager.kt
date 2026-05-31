import menu.Settings
import processing.core.PApplet

class ObjectManager(private val p: PApplet) {
    @JvmField
    val settings: Settings = Settings()

    @JvmField
    val key: Key = Key(p, settings)

    @JvmField
    val cursor: Cursor = Cursor(p, 3000, true)

    @JvmField
    val font: Font = Font(p, "fonts/JetBrainsMono-VariableFont_wght.ttf", 32f)
}