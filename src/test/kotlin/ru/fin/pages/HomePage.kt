package ru.fin.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.WebDriverWait
import ru.fin.catchCaptcha

class HomePage(val driver: WebDriver) {
    private val signIn: WebElement

    init {
        catchCaptcha(driver)
        val webDriverWait = WebDriverWait(driver, 10)
        signIn = webDriverWait.until {
            driver.findElement(By.xpath("//*/li[@class=\"header-login\"]/a"))
        }
    }

    fun openLoginElement(): LoginElement {
        signIn.click()
        return LoginElement(driver)
    }
}

class LoginElement(val driver: WebDriver) {
    private val loginInput: WebElement
    private val passwordInput: WebElement
    private val signInButton: WebElement
    private val facebookSignInButton: WebElement

    init {
        val webDriverWait = WebDriverWait(driver, 10)
        loginInput = webDriverWait.until {
            driver.findElement(By.xpath("//*/input[@id=\"login\"]"))
        }
        passwordInput = webDriverWait.until {
            driver.findElement(By.xpath("//*/input[@id=\"password\"]"))
        }
        signInButton = webDriverWait.until {
            driver.findElement(By.xpath("//*/div[@class=\"sign-in-form\"]/button[contains(@class, \"submit-button\")]"))
        }
        facebookSignInButton = webDriverWait.until {
            driver.findElement(By.xpath("//*/button[@class=\"facebook-signing-button\"]"))
        }
    }

    fun login() {
        loginInput.sendKeys("imashkaman@gmail.com")
        passwordInput.sendKeys("123qweQWE")
        signInButton.click()
    }

    fun facebookLogin() {
        facebookSignInButton.click()
    }

}