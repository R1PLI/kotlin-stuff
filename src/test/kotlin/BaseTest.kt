
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FunSpec
import java.util.Arrays.asList

class BaseTest : FunSpec() {
    private val persons = listOf(Person("Alice"), Person("Bob", 21))
    private val isEven: (Int) -> Boolean = { it % 2 == 0 }
    private val isOdd: (Int) -> Boolean = { it % 2 != 0 }

    init {
        test("Method should return person with max age") {
            val actual = BaseFunctionality().findPersonWithMaxAge(persons)
            actual shouldBe Person("Bob", 21)
        }
        test("Method should return person with biggest age timed 2") {
            val actual = BaseFunctionality().findBiggestAgeTimesByValue(persons, 2)
            actual shouldBe 21
        }
        test("Method should calculate sum of input varargs") {
            val actual = BaseFunctionality().getSumInList(1, 2, 3)
            actual shouldBe 12
        }
        test("Method should filter list by odevity") {
            val actual = BaseFunctionality().filterListByPredicate(asList(1, 2, 3, 4, 5, 6), isEven)
            actual shouldBe asList(2, 4, 6)
        }
        test("Method should filter list by oddness") {
            val actual = BaseFunctionality().filterListByPredicate(asList(1, 2, 3, 4, 5, 6), isOdd)
            actual shouldBe asList(1, 3, 5)
        }
        test("Should create rectangle with given parameters") {
            val rect = Rectangle(41, 43)
            rect.height shouldBe 41
            rect.width shouldBe 43
            rect.isSquare shouldBe false
        }
        test("Should create rectangle") {
            val randomRect = createRandomRectangle()
            assert(randomRect is Rectangle)
        }
    }
}