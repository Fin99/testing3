package ru.fin.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import ru.fin.catchCaptcha
import ru.fin.waiter

class HomePage(val driver: WebDriver) {
    private val signIn: WebElement
    private val searchInput: WebElement
    private val searchSubmit: WebElement
    val language: WebElement
    val currency: WebElement

    init {
        catchCaptcha(driver)
        signIn = waiter.until {
            driver.findElement(By.xpath("//*/li[@class=\"header-login\"]/a"))
        }
        language = waiter.until {
            driver.findElement(By.xpath("//*/div[@id=\"LocaleSettings-component\"]//*/div[contains(@class, \"language-selection-title\")]/span[@class=\"title-label\"]"))
        }
        currency = waiter.until {
            driver.findElement(By.xpath("//*/div[@id=\"LocaleSettings-component\"]//*/div[@class=\"locale-button-title\"]"))
        }
        searchInput = waiter.until {
            driver.findElement(By.xpath("//div[contains(@class, \"main-content\")]//div[contains(@class, \"search-bar-package\")]//input[@type=\"search\"]"))
        }
        searchSubmit = waiter.until {
            driver.findElement(By.xpath("//div[contains(@class, \"main-content\")]//div[contains(@class, \"search-bar-package\") and //input[@type=\"search\"]]//button"))
        }
    }

    fun openLoginForm(): LoginForm {
        signIn.click()
        return LoginForm(driver)
    }

    fun openLanguageForm(): LanguageForm {
        language.click()
        return LanguageForm(driver)
    }

    fun openCurrencyForm(): CurrencyForm {
        currency.click()
        return CurrencyForm(driver)
    }

    fun search(): SearchPage {
        searchInput.sendKeys("software testing")
        searchSubmit.click()
        return SearchPage(driver)
    }
}

class LoginForm(val driver: WebDriver) {
    private val loginInput: WebElement
    private val passwordInput: WebElement
    private val signInButton: WebElement
    private val facebookSignInButton: WebElement

    init {
        loginInput = waiter.until {
            driver.findElement(By.xpath("//*/input[@id=\"login\"]"))
        }
        passwordInput = waiter.until {
            driver.findElement(By.xpath("//*/input[@id=\"password\"]"))
        }
        signInButton = waiter.until {
            driver.findElement(By.xpath("//*/div[@class=\"sign-in-form\"]/button[contains(@class, \"submit-button\")]"))
        }
        facebookSignInButton = waiter.until {
            driver.findElement(By.xpath("//*/button[@class=\"facebook-signing-button\"]"))
        }
    }

    fun login(): MainPage {
        loginInput.sendKeys("imashkaman@gmail.com")
        passwordInput.sendKeys("123qweQWE")
        signInButton.click()
        return MainPage(driver)
    }

    fun facebookLogin(): LoginForm {
        facebookSignInButton.click()
        return this
    }
}

class LanguageForm(val driver: WebDriver) {
    private val deutsch: WebElement
    private val english: WebElement

    init {
        deutsch = waiter.until {
            driver.findElement(By.xpath("//*/div[@id=\"LocaleSettings-component\"]//*/p[text()=\"Deutsch\"]"))
        }
        english = waiter.until {
            driver.findElement(By.xpath("//*/div[@id=\"LocaleSettings-component\"]//*/p[text()=\"English\"]"))
        }
    }

    fun selectDeutsch(): HomePage {
        deutsch.click()
        return HomePage(driver)
    }

    fun selectEnglish(): HomePage {
        english.click()
        return HomePage(driver)
    }
}

class CurrencyForm(val driver: WebDriver) {
    private val usd: WebElement
    private val eur: WebElement

    init {
        usd = waiter.until {
            driver.findElement(By.xpath("//*/div[@id=\"LocaleSettings-component\"]//*/p[text()=\"United States Dollar (USD) - \$\"]"))
        }
        eur = waiter.until {
            driver.findElement(By.xpath("//*/div[@id=\"LocaleSettings-component\"]//*/p[text()=\"Euro (EUR) - €\"]"))
        }
    }

    fun selectUSD(): HomePage {
        usd.click()
        return HomePage(driver)
    }

    fun selectEUR(): HomePage {
        eur.click()
        return HomePage(driver)
    }
}