package ru.fin.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import ru.fin.waiter

class OrdersPage(driver: WebDriver) : PageWithMenu(driver) {
    val noOrders: WebElement

    init {
        noOrders = waiter.until {
            driver.findElement(By.xpath("//main[@class=\"main-content\"]//h3"))
        }
    }
}