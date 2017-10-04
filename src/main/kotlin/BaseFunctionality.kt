class BaseFunctionality {
    fun findPersonWithMaxAge(list: List<Person>): Person? = list.maxBy { it.age ?: 0 }

    fun findBiggestAgeTimesByValue(list: List<Person>, multiply: Int): Int? {
        return list.map { it.age ?: 0 * multiply }.maxBy { it }
    }

    fun getSumInList(vararg args: Int): Int = args.map { it * 2 }.sum()

    fun filterListByPredicate(list: List<Int>, predicate: (Int) -> Boolean): List<Int> = list.filter(predicate)
}