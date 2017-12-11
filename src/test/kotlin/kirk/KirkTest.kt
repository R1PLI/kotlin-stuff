package kirk

import com.automation.remarks.kirk.Kirk.Companion.drive
import com.automation.remarks.kirk.Kirk.Companion.open
import com.automation.remarks.kirk.conditions.text
import io.qameta.allure.*
import org.openqa.selenium.By
import org.testng.annotations.Test
import pageobject.SearchPage

@Feature("Junit 4 test")
class KirkTest {
    val url = "https://google.co.uk"

    @Test
    @Description("This test will always be green")
    @Severity(SeverityLevel.CRITICAL)
    fun baseSuccessfulTest() {
        drive {
            open(url)
            element(By.name("q")).setValue("Automation remarks").pressEnter()
            element(By.linkText("Заметки Автоматизатора")).click()
            element(".header-name").shouldHave(text("Заметки Автоматизатора"))
        }
    }

    @Test
    @Description("This test is flaky for testing purposes")
    @Flaky
    @Severity(SeverityLevel.NORMAL)
    fun baseFailedTest() {
        drive {
            open(url)
            element(By.name("q")).setValue("Automation remarks").pressEnter()
            element(By.linkText("Заметки Автоматизатора")).click()
            element(".header-name").shouldHave(text("Заметки Asdasdasdsasdasd"))
        }
    }

    @Test
    fun pageObjectTest() {
        open(::SearchPage) {
            inputValueInSearchField("Kirk")
                    .validateResultNumber(10)
        }
    }
}