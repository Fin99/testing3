package ru.fin.page

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import ru.fin.catchCaptcha
import ru.fin.waiter

class ProfilePage(val driver: WebDriver) {
    private val username: WebElement
    private val saveUsername: WebElement

    init {
        catchCaptcha(driver)
        username = waiter.until {
            driver.findElement(By.xpath("//input[@id=\"user_profile_attributes_name\"]"))
        }
        saveUsername = waiter.until {
            driver.findElement(By.xpath("//form[@class=\"edit_user\"]//input[@type=\"submit\"]"))
        }
    }

    fun setNewUsername(): ProfilePage {
        username.sendKeys(Keys.CONTROL.toString() + "A")
        username.sendKeys(Keys.DELETE)
        username.sendKeys("Fin")
        return this
    }

    fun saveUsername(): ProfilePage {
        saveUsername.click()
        return this
    }

    fun getSuccessfullyUpdatedMessageText(): String {
        return waiter.until {
            driver.findElement(By.xpath("//div[contains(@class, \"flash-message\")]/p"))
        }.text
    }
}