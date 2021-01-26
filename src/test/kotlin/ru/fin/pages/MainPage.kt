package ru.fin.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.WebDriverWait
import ru.fin.catchCaptcha

class MainPage(val driver: WebDriver) {
    private val avatar: WebElement

    init {
        catchCaptcha(driver)
        val webDriverWait = WebDriverWait(driver, 10)
        avatar = webDriverWait.until {
            driver.findElement(By.xpath("//*/li[@class=\"display-from-sm\"]//*/button[@class=\"nav-popover-items-toggler\"]"))
        }
    }

    fun openAvatarMenu(): AvatarElement {
        avatar.click()
        return AvatarElement(driver)
    }
}

class AvatarElement(val driver: WebDriver) {
    val logout: WebElement

    init {
        catchCaptcha(driver)
        val webDriverWait = WebDriverWait(driver, 10)
        logout = webDriverWait.until {
            driver.findElement(By.xpath("//*/a[@data-event-type=\"logout\"]"))
        }
    }
}