data class Vector(var x: Double, var y: Double) {
    operator fun plus(other: Vector) = Vector(this.x + other.x, this.y + other.y)
    operator fun minus(other: Vector) = Vector(this.x - other.x, this.y - other.y)
    operator fun times(other: Vector) = Vector(this.x * other.x, this.y * other.y)
}
