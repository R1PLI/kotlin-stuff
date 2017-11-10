
import java.time.LocalDate
import kotlin.reflect.KProperty

data class Point(val x: Int, val y: Int)

operator fun Point.plus(other: Point): Point {
    return Point(x + other.x, y + other.y)
}

operator fun Point.unaryMinus(): Point {
    return Point(this.x + 1, this.y + 1)
}

operator fun Point.inc(): Point {
    return this + Point(1, 1)
}

operator fun Point.get(index: Int): Int {
    return when (index) {
        0 -> x
        1 -> y
        else -> throw IndexOutOfBoundsException("Invalid coordinate $index")
    }
}

operator fun Char.times(count: Int): String {
    return toString().repeat(count)
}

data class MutablePoint(var x: Int, var y: Int)

operator fun MutablePoint.get(index: Int): Int {
    return when (index) {
        0 -> x
        1 -> y
        else -> throw IndexOutOfBoundsException("Invalid coordinate $index")
    }
}

operator fun MutablePoint.set(index: Int, value: Int) {
    return when (index) {
        0 -> x = value
        1 -> y = value
        else -> throw IndexOutOfBoundsException("Invalid coordinate $index")
    }
}

data class Rectangle(val upperLeft: Point, val lowerRight: Point)

operator fun Rectangle.contains(p: Point): Boolean {
    return p.x in upperLeft.x until lowerRight.x &&
            p.y in upperLeft.y until lowerRight.y
}

data class NameComponents(val name: String, val extension: String)

fun splitFilename(fullName: String): NameComponents {
    val result = fullName.split('.', limit = 2)
    return NameComponents(result[0], result[1])
}

fun <T, R> Map<T, R>.printEntries() {
    for ((key, value) in this) {
        println("$key -> $value")
    }
}

//delegated properties example
class Users {
    var todayTasks: String by DelegatedUser()
}

class DelegatedUser {
    operator fun getValue(user: Users, property: KProperty<*>): String {
        return "thanks for delegating '${property.name}' to me!"
    }

    operator fun setValue(user: Users, property: KProperty<*>, value: String) {
        println("assigned to '${property.name}' in $user. Details: '$value'")
    }

}

fun main(args: Array<String>) {
    //Example of overriding plus operator
    val p1 = Point(10, 20)
    val p2 = Point(20, 10)
    println(p1 + p2)

    //Example of overriding * operator for Char
    println('x' * 3)

    //Example of using plusApply operator or Point class
    var p3 = Point(11, 22)
    p3 += Point(9, 8)

    //Example of overriding unaryOperator - or Point class
    var p4 = Point(10, 10)
    println(-p3)

    //Example of overriding inc operator for Point class
    println(++p4)
    p4++
    println(p4)

    println("----------------------------------")
    //Example accessing elements by index
    val map1 = mutableMapOf(1 to "Stepan", 2 to "Fedor")
    println(map1[1])
    map1[1] = "Semen"
    println(map1[1])

    //example of overriding get in Point class
    println(p4[1])
    println("----------------------------------")
    //get set for mutablePoint class
    val p5 = MutablePoint(10, 10)
    println(p5[0])
    p5[0] = 11
    println(p5[0])
    println("------------------")
    //overriding contains method ( in )
    val rectangle = Rectangle(Point(10, 20), Point(50, 50))
    val testedPoint = Point(20, 30)
    val secondTestedPoint = Point(5, 5)
    println(testedPoint in rectangle)
    println(secondTestedPoint in rectangle)
    //range examples
    val now = LocalDate.now()
    val vacation = now..now.plusDays(10)

    val isInVacationRange = now.plusWeeks(1) in vacation
    println("This date is in vacation range: $isInVacationRange")

    (0..9).forEach { print("$it ") }

    println("----------------")
    val (chelnok, shlupka) = p5
    println(chelnok)
    println(shlupka)
    println("----------------")
    val (name, ext) = splitFilename("saha.kt")
    println("File name = $name")
    println("Ext name = $ext")

    val map2 = mapOf(1 to "Marie", "Twice" to "Adriana")
    map2.printEntries()

    val users = Users()
    println(users.todayTasks)
    users.todayTasks = "I learn kotlin"
}