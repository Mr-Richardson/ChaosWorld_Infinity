import menu.Settings

class ObjectManager {
    @JvmField
    val settings: Settings = Settings()

    @JvmField
    val key: Key = Key(settings)

    @JvmField
    val cursor: Cursor = Cursor(3000, true)

    @JvmField
    val font: Font = Font("fonts/JetBrainsMono-VariableFont_wght.ttf", 32f)
}