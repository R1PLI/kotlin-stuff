
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FunSpec
import strings.*
import java.util.Arrays.asList

class BaseTest : FunSpec() {
    private val persons = listOf(Person("Alice", 22), Person("Bob", 21))
    private val isEven: (Int) -> Boolean = { it % 2 == 0 }
    private val isOdd: (Int) -> Boolean = { it % 2 != 0 }
    private val bf = BaseFunctionality()

    init {
        test("Method should return person with max age") {
            val actual = bf.findPersonWithMaxAge(persons)
            actual shouldBe Person("Bob", 21)
        }
        test("Method should return person with biggest age timed 2") {
            val actual = bf.findBiggestAgeTimesByValue(persons, 2)
            actual shouldBe 21
        }
        test("Method should calculate sum of input varargs") {
            val actual = bf.getSumInList(1, 2, 3)
            actual shouldBe 12
        }
        test("Method should filter list by odevity") {
            val actual = bf.filterListByPredicate(asList(1, 2, 3, 4, 5, 6), isEven)
            actual shouldBe asList(2, 4, 6)
        }
        test("Method should filter list by oddness") {
            val actual = bf.filterListByPredicate(asList(1, 2, 3, 4, 5, 6), isOdd)
            actual shouldBe asList(1, 3, 5)
        }

        test("Method should return separated collection") {
            val intList = listOf(1,2,3,4)
            val stringList = listOf("1", "2", "3", "4")
            println(intList.joinzToString())
            println(stringList.joen())
        }

        test("Should return last element in string") {
            val result = "asd".lastChar()
            assert(result == 'd')
        }

        test("Should replace last char in string") {
            val sb = StringBuilder("Kotlin?")
            sb.builderLastChar = '!'
            println(sb)
            assert(sb.toString() == "Kotlin!")
        }

        test("Should return list of values") {
            val result = spreadExample(1,2,3,4,5,6)
            println(result)
        }
    }
}

