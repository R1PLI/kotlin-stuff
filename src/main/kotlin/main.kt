fun main(args: Array<String>) {
    val persons = listOf(Person("Alice", 20), Person("Bob", 21))
    println(findPersonWithMaxAge(persons))
    println("The biggest age times 2 is ${findBiggestAgeTimesByValue(persons, 2)}")
    printWithUnitFun("Oleksandr")
    println(asList(1, 2, 3))
    println(getSumInList(1, 2, 3, 4))
}

fun findPersonWithMaxAge(list: List<Person>): Person? = list.maxBy { it.age ?: 0 }

fun findBiggestAgeTimesByValue(list: List<Person>, multiply: Int): Int? {
    return list.map { it.age!! * multiply }.maxBy { it }
}

fun printWithUnitFun(name: String?) = println("Hi, my name is $name")

fun <T> asList(vararg ts: T): List<T> {
    val result = ArrayList<T>()
    result += ts
    return result
}

fun getSumInList(vararg args: Int): Int = args.map { it * 2 }.sum()