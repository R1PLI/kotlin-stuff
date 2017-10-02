import java.util.*

class Rectangle(private val height: Int, private val width: Int) {
    val isSquare: Boolean
        get() = height == width

    override fun toString(): String {
        return "Rectangle(height=$height, width=$width)"
    }

}

fun createRandomRectangle(): Rectangle {
    val random = Random()
    return Rectangle(
            height = random.nextInt(),
            width = random.nextInt()
    )
}