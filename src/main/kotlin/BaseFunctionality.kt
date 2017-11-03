class BaseFunctionality {
    fun findPersonWithMaxAge(list: List<Person>): Person? = list.maxBy { it.age }

    fun findBiggestAgeTimesByValue(list: List<Person>, multiply: Int): Int? {
        return list.map { it.age * multiply }.maxBy { it }
    }

    fun getSumInList(vararg args: Int): Int = args.map { it * 2 }.sum()

    fun filterListByPredicate(list: List<Int>, predicate: (Int) -> Boolean): List<Int> = list.filter(predicate)

    fun <T> joinToString(
            collectionz: Collection<T>,
            separator: String = ", ",
            prefix: String = "",
            postfix: String = ""
    ): String {
        val result = StringBuilder(prefix)

        for ((index, element) in collectionz.withIndex()) {
            if (index > 0) result.append(separator)
            result.append(element)
        }

        result.append(postfix)
        return result.toString()
    }
}