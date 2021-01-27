package ru.fin.page

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import ru.fin.catchCaptcha
import ru.fin.waiter

class StartPaymentPage(val driver: WebDriver) {
    private val continuePayment: WebElement

    init {
        catchCaptcha(driver)
        continuePayment = waiter.until {
            driver.findElement(By.xpath("//div[@class='payment-summary']//button"))
        }
    }

    fun continuePayment(): PaymentOptionsPage {
        continuePayment.click()
        return PaymentOptionsPage(driver)
    }

}

class PaymentOptionsPage(driver: WebDriver) {
    val paymentOptions: WebElement

    init {
        catchCaptcha(driver)
        paymentOptions = waiter.until {
            driver.findElement(By.xpath("//article[@class=\"payment-methods\"]//header//h6"))
        }
    }
}