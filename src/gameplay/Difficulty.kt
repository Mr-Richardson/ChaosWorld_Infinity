package gameplay

import kotlin.math.max

class Difficulty(var score: Int) {
    fun updateScore(pos: Float) {
        score = max(score, pos.toInt())
    }
}
