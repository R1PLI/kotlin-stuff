package pageobject.pudgeobject

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.Page
import org.openqa.selenium.By


class MainPage(browser: Browser) : Page(browser) {
    private val loginFieldBySelector: By = By.name("Login")
    private val pwdFieldBySelector: By = By.name("Password")
    private val loginButtonBySelector: By = By.cssSelector(".login-block__submit-but > button")
    private val namingLinkBySelector: By = By.cssSelector("#login-block > div > div > span")
    private val logoutLinkBySelector: By = By.cssSelector("#login-block > div > div > a")

    override val url: String?
        get() = "https://www.ukr.net/"

    private fun enterLogin(username: String) = element(loginFieldBySelector).setValue(username)

    private fun enterPwd(password: String) = element(pwdFieldBySelector).setValue(password)

    private fun clickLoginButton() = element(loginButtonBySelector).click()

    fun getEmailAddressTextElement() = element(namingLinkBySelector)

    fun getLogoutLinkElement() = element(logoutLinkBySelector)

    fun loginInMailbox(username: String, pwd: String) {
        enterLogin(username)
        enterPwd(pwd)
        clickLoginButton()
    }
}