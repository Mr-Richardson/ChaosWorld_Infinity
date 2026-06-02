package menu

import Vector

class Settings {
    val epsilon: Double = 1e-5

    //File config = new File(path); TODO: save settings in file
    val deathY: Double = -0.5
    val obstacleDistanceMin: Double = 0.05
    val obstacleDistanceMax: Double = 0.5
    val obstacleHeightMin: Double = 0.1
    val obstacleHeightMax: Double = 0.3
    val obstacleWidthMin: Double = 0.1
    val obstacleWidthMax: Double = 0.6
    val obstacleThicknessMin: Double = 0.01
    val obstacleThicknessMax: Double = 0.4
    val friction: Double = 0.9
    val cameraSmoothness: Double = 0.02
    val cameraFocus: Double = 0.3

    // Keys
    val keyRight: Char = 'd'
    val keyLeft: Char = 'a'
    val keyJump: Char = ' '
    val keyReset: Char = 'r'
    val keyZoomIn: Char = '+'
    val keyZoomOut: Char = '-'

    val playerRadius: Double = 0.032
    val playerMaxJump: Double = 0.025
    val playerMaxSpeed: Double = 0.001
    val playerAirInertia: Double = 5.0
    val playerStart: Vector = Vector(0.15, 0.2)
    val gravity: Double = 0.002
    val uiScale: Float = 1.0f
    var zoom: Float = 1.0f
}
