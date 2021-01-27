package ru.fin.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import ru.fin.tryFindElementOrNull
import ru.fin.waiter

class NewListPage(driver: WebDriver): PageWithMenu(driver) {
    val list: WebElement?

    init {
        list = tryFindElementOrNull {
            waiter.until {
                driver.findElement(By.xpath("//div[@class=\"name-cta-container\"]//h2"))
            }
        }
    }
}