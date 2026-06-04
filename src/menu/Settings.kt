package menu

import Vector

class Settings {
    //File config = new File(path); TODO: save settings in file

    val epsilon: Double = 1e-5

    // Obstacle
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
    val keySprint = 16

    // Player
    val playerDeathY: Double = -0.5
    val playerRadius: Double = 0.02
    val playerStart: Vector = Vector(0.15, 0.25)
    val playerJump: Double = 0.03
    val playerSpeed: Double = 0.002
    val playerAirResistance: Double = 0.97
    val playerAirInertia: Double = 0.4
    val gravity: Double = 0.002

    // Scaling
    val uiScale: Float = 1.0f // TODO: resize key
    var zoom: Float = 1.0f
}
