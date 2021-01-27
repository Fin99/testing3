package ru.fin.page

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import ru.fin.catchCaptcha
import ru.fin.waiter

class SearchPage(val driver: WebDriver) {
    val resultQuery: WebElement
    val username: WebElement
    private val description: WebElement

    init {
        catchCaptcha(driver)
        resultQuery = waiter.until {
            driver.findElement(By.xpath("//div[@class=\"search-header\"]/span"))
        }
        username = waiter.until {
            driver.findElement(By.xpath("//div[contains(@class, \"content-row\")]//div[@class=\"gig-card-layout\"][1]//div[@class=\"seller-name\"]//a"))
        }
        description = waiter.until {
            driver.findElement(By.xpath("//div[contains(@class, \"content-row\")]//div[@class=\"gig-card-layout\"][1]//h3"))
        }
    }

    fun openService(): ServicePage {
        val oldWindow = driver.windowHandle
        description.click()
        waiter.until(ExpectedConditions.numberOfWindowsToBe(2))
        driver.switchTo().window(driver.windowHandles.find { (it == oldWindow).not() })
        return ServicePage(driver)
    }

    fun openAnonymousService(): AnonymousServicePage {
        val oldWindow = driver.windowHandle
        description.click()
        waiter.until(ExpectedConditions.numberOfWindowsToBe(2))
        driver.switchTo().window(driver.windowHandles.find { (it == oldWindow).not() })
        return AnonymousServicePage(driver)
    }
}