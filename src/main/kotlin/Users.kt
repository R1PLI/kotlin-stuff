import java.util.*

interface User {
    val nickname: String
}

class PrivateUser(override val nickname: String) : User
class SubscribedUser(val email: String) : User {
    override val nickname: String
        get() = email.substringBefore('@')
}

class FacebookUser(accountId: Int) : User {
    override val nickname = accountId.toString()
}

object Payroll {
    var item: String = "item"
        get() = field + "hiya"
        set(value) {
            field = value.trim()
        }

    var items: String = "items"
        set(value) {
            field = value.trim()
        }
}

fun sendEmail(person: Person, letter: String): String {
    return "person $person sends $letter"
}

fun sendDefaultEmail(): String = "person Bob sends letter"

fun alphabetOld(): String {
    val result = StringBuilder()
    for (letter in 'A'..'Z') {
        result.append(letter)
    }
    result.append("\nNow i know the alphabet!")
    return result.toString()
}

fun alphabetNewWithWith(): String = with(StringBuilder()) {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nNow i know the alphabet!")
    toString()
}

fun alphabetNewWithApply(): String = StringBuilder().apply {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nNow i know the alphabet!")
}.toString()

fun alphabetNewWithStandardFunction(): String = buildString {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nNow i know the alphabet!")
}

fun strLen(s: String?) = s?.length ?: 0

fun sendEmailTo(email: String) {
    println("$email sent")
}

fun <T> copyFunction(source: Collection<T>,
                     target: MutableCollection<T>

): MutableCollection<T> {
    target += source
    return target
}

@Suppress("USELESS_IS_CHECK")
fun main(args: Array<String>) {
    //working with param args example
    if (args.isNotEmpty() && args[0] == "facebook") {
        println(FacebookUser(15).nickname)
    }
    //custom set and get example
    val pay = Payroll
    println(pay.item)
    pay.item = "item2   "
    pay.items = "items3   "
    println(pay.item)
    println(pay.items)


    val people = listOf(Person("Alice", 22), Person("Bob", 31))
    val stringPeople = people.joinToString(" - ") { it.name }
    println(stringPeople)
    println("-----------------------")


    val action = { person: Person, message: String -> sendEmail(person, message) }
    //reference (for empty constructor class) example
    val nextAction = ::sendDefaultEmail
    //creating reference for new Person object example
    val createPerson = ::Person

    println(action(createPerson("Alice", 22), "asd"))
    println("-----------------------")
    println(run(nextAction))
    println("-----------------------")

    //creating list of people using reference
    val peoples = listOf(createPerson("Alice", 27), createPerson("Bob", 31), createPerson("Kate", 27))

    //predicates for Person.age
    val notInClub27 = peoples.maxBy(Person::age)!!.age
    val canBeInClub27 = { p: Person -> p.age <= 27 }

    //built-in predicates example
    println(peoples.filter { it.age == notInClub27 }.map(Person::name))
    println("-----------------------")
    println(peoples.map { it.age + 1 })
    println("-----------------------")
    println(peoples.any(canBeInClub27))
    println("-----------------------")
    println(peoples.all(canBeInClub27))

    //groupBy example
    println(peoples.groupBy { it.age })

    //practical use of FlatMap on authors
    val books = listOf(Book("asd", listOf("Ivan", "Peter")), Book("dsa", listOf("Ivan", "Fedor")))
    println(books.flatMap { it.authors }.toSet())

    //flatMap example on strings
    val strings = listOf("abc", "abc")
    println(strings.flatMap { it.toList() })

    //collection flatter example
    val colCol = listOf(listOf("asd", "ytr"), listOf("bsd", "hgf"), listOf("asdasdas", 888))
    println(colCol)
    println(colCol.flatten())

    //generating sequence
    val sumNumbersTo100 = generateSequence(0) { it + 1 }.takeWhile { it <= 100 }.sum()
    println(sumNumbersTo100)
    println("--------------")
    println(alphabetOld())
    println("--------------")
    println(alphabetNewWithWith())
    println("--------------")
    println(alphabetNewWithApply())
    println("--------------")
    println(alphabetNewWithStandardFunction())
    println("--------------")


    val email1: String? = null
    val email2: String? = "yolooooooooooo@"
    val email3 = "vasya"
    //sendEmailTo(email1) - can't do this because function takes non-null parameter (null)
    email1?.let { sendEmailTo(it) }
    //sendEmailTo(email2) - can't do this because function takes non-null parameter (String?) type
    email2?.let { sendEmailTo(it) }
    sendEmailTo(email3)
    println("--------------------------")
    val sourceList: Collection<Int> = listOf(2, 3, 5) // returns arrayList, cannot be changed
    val targetList: MutableCollection<Int> = arrayListOf(1) // can be changed
    println(copyFunction(sourceList, targetList))

    val letters = Array(26) { i -> ('a' + i).toString() }
    println(letters.joinToString(" "))

    val fiveIntZeros = IntArray(5)
    val fiveIntZerosFactory = intArrayOf(0, 0, 0, 0, 0)
    val fiveDoubleZeros = DoubleArray(5)
    val fiveDoubleZerosFactory = doubleArrayOf(0.0, 0.0, 0.0, 0.0, 0.0)
    val doubleSquaresArray = IntArray(5) { i -> (i + 1) * (i + 1) }
    val filteredArray = doubleSquaresArray.filter { it % 2 == 0 }

    println(Arrays.toString(fiveIntZeros))
    println(Arrays.toString(fiveIntZerosFactory))
    println(Arrays.toString(fiveDoubleZeros))
    println(Arrays.toString(fiveDoubleZerosFactory))
    println(Arrays.toString(doubleSquaresArray))
    println(filteredArray)
}