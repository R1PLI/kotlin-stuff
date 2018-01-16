package pageobject

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.Page
import org.openqa.selenium.By


class SearchPage(browser: Browser) : Page(browser) {
    var inputFieldLocator: String = "q"

    override val url: String?
        get() = "http://google.co.uk"

    fun inputValueInSearchField(query: String) :ResultPage{
        element(By.name(inputFieldLocator)).setValue(query).pressEnter()
        return ResultPage(browser)
    }
}
