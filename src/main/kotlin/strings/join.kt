package strings

fun <T> Collection<T>.joinzToString(
        separator: String = ", ",
        prefix: String = "",
        postfix: String = ""
): String {
    val result = StringBuilder(prefix)

    this.withIndex().forEach { (index, element) ->
        if (index > 0) result.append(separator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}

fun <T> spreadExample(vararg values: T): List<Any?> {
    return listOf("values: ", *values)
}

fun String.lastChar(): Char = get(length - 1)

var StringBuilder.builderLastChar: Char
    get() = get(length - 1)
    set(value) = this.setCharAt(length - 1, value)

fun Collection<String>.joen(
        separator: String = ", ",
        prefix: String = "",
        postfix: String = ""
) = joinzToString(separator, prefix, postfix)



