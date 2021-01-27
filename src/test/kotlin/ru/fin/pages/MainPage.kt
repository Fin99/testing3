package ru.fin.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import ru.fin.waiter

class MainPage(driver: WebDriver): PageWithMenu(driver) {
    private val category: WebElement

    init {
        category = waiter.until {
            driver.findElement(By.xpath("//ul[contains(@class, \"categories\")]/li[1]/a"))
        }
    }

    fun openCategory(): CategoryPage {
        category.click()
        return CategoryPage(driver)
    }

}
