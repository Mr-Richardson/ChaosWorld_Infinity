package menu

import Vector

class Settings {
    val epsilon: Double = 1e-5

    //File config = new File(path); TODO: save settings in file
    val deathY: Double = -0.5
    val obstacleDistanceMin: Double = 0.05
    val obstacleDistanceMax: Double = 0.2
    val obstacleHeightMin: Double = 0.05
    val obstacleHeightMax: Double = 0.15
    val obstacleWidthMin: Double = 0.05
    val obstacleWidthMax: Double = 0.3
    val obstacleThicknessMin: Double = 0.05
    val obstacleThicknessMax: Double = 0.2

    val friction: Double = 0.8

    // Camera
    val cameraSmoothness: Double = 0.02
    val cameraFocus: Double = 0.3

    // Keys
    val keyRight = 68
    val keyLeft = 65
    val keyJump = 32
    val keyMore = 93
    val keyLess = 47
    val keyReset = 82
    val keyZoom = 67
    val keyCtrl = 17

    val playerRadius: Double = 0.032
    val playerMaxJump: Double = 0.025
    val playerMaxSpeed: Double = 0.001
    val playerAirInertia: Double = 5.0
    val playerStart: Vector = Vector(0.15, 0.2)
    val gravity: Double = 0.002
    val uiScale: Float = 1.0f
    var zoom: Float = 1.0f
}
