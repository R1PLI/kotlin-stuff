package pageobject

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.Page
import com.automation.remarks.kirk.conditions.size


class ResultPage(browser: Browser) : Page(browser) {
    var searchResultListLocator: String = ".srg > .g"

    fun validateResultNumber(resultNumber: Int) =
            all(searchResultListLocator).shouldHave(size(resultNumber))
}
