fun String.filter(predicate: (Char) -> Boolean): String {
    val sb = StringBuilder()
    (0 until length)
            .map { get(it) }
            .filter { predicate(it) }
            .forEach { sb.append(it) }
    return sb.toString()
}

fun processTheAnswer(number: Int, f: (Int) -> Int) = f(number + 1)


fun main(args: Array<String>) {
    println("ab1c".filter { it in 'a'..'z' })
    println(processTheAnswer(42) { it })


    var lists: List<Number> = mutableListOf(1, 1.0, 2, 3L)
    print(lists.joinToString(","))
    lists += 10
    println()
    print(lists.joinToString(","))
}
