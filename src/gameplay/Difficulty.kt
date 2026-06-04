package gameplay

import kotlin.math.max

class Difficulty(var score: Int) {
    fun updateScore(pos: Double) {
        score = max(score, (pos * 10).toInt())
    }
}
// TODO: difficulty levels and increasing difficulty during gameplay
