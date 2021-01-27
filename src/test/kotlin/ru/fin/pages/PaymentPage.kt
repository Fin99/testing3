package ru.fin.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import ru.fin.catchCaptcha
import ru.fin.waiter

class PaymentPage(driver: WebDriver) {
    val paymentOptions: WebElement

    init {
        catchCaptcha(driver)
        paymentOptions = waiter.until {
            driver.findElement(By.xpath("//article[@class=\"payment-methods\"]//header//h6"))
        }
    }
}