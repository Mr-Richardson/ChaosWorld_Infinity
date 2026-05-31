package menu

import processing.core.PVector

class Settings {
    var epsilon: Float = 0.001f

    //File config = new File(path);    TODO: save settings in file
    val deathY: Int = -1000
    val obstacleDistanceMin: Int = -100
    val obstacleDistanceMax: Int = 600
    val obstacleHeightMin: Int = 100
    val obstacleHeightMax: Int = 300
    val obstacleWidthMin: Int = 100
    val obstacleWidthMax: Int = 600
    val obstacleThicknessMin: Int = 10
    val obstacleThicknessMax: Int = 400
    val friction: Float = 0.9f
    val cameraSmoothness: Float = 0.02f
    val cameraFocus: Float = 0.3f
    val keyRight: Char = 'd'
    val keyLeft: Char = 'a'
    val keyJump: Char = ' '
    val keyReset: Char = 'r'
    val playerRadius: Int = 35
    val playerMaxJump: Int = 30
    val playerMaxSpeed: Float = 1.2f
    val playerAirInertia: Float = 5.0f
    val playerStart: PVector = PVector(150.0f, 500f)
}