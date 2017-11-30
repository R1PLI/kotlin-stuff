package kirk

import com.automation.remarks.kirk.Kirk.Companion.drive
import com.automation.remarks.kirk.conditions.text
import io.qameta.allure.Feature
import io.qameta.allure.Severity
import io.qameta.allure.SeverityLevel
import org.openqa.selenium.By
import org.testng.annotations.Test

@Feature("Junit 4 test")
class KirkTest {
    var url = "https://google.co.uk"

    @Test
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
    @Severity(SeverityLevel.NORMAL)
    fun baseFailedTest() {
        drive {
            open(url)
            element(By.name("q")).setValue("Automation remarks").pressEnter()
            element(By.linkText("Заметки Автоматизатора")).click()
            element(".header-name").shouldHave(text("Заметки Asdasdasdsasdasd"))
        }
    }
}