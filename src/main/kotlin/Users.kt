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

fun Person.isAdult() = age >= 21

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
}