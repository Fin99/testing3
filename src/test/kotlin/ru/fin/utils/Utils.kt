package ru.fin

import org.openqa.selenium.*
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.FluentWait
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration


lateinit var waiter: WebDriverWait
lateinit var longWaiter: WebDriverWait

fun initWaiter(driver: WebDriver) {
    waiter = WebDriverWait(driver, 3)
    longWaiter = WebDriverWait(driver, 10)
}

fun tryFindElementOrNull(find: () -> WebElement): WebElement? =
    try {
        find.invoke()
    } catch (e: TimeoutException) {
        null
    }

fun catchCaptcha(driver: WebDriver) {
    val captcha = findCaptcha(driver)
    captcha?.let {
        println("captcha found")
        waitCaptcha(driver, captcha)
    } ?: println("captcha not found")
}

private fun findCaptcha(driver: WebDriver): WebElement? = try {
    FluentWait(driver)
        .withTimeout(Duration.ofSeconds(3))
        .pollingEvery(Duration.ofSeconds(1))
        .ignoring(NoSuchElementException::class.java)
        .until {
            it.findElement(By.xpath("//h1[text()=\"One Small Step\"]|//p[text()=\"One Small Step\"]"))
        }
} catch (captchaNotFound: TimeoutException) {
    null
}

private fun waitCaptcha(driver: WebDriver, captcha: WebElement?) {
    println("wait captcha")
    FluentWait(driver)
        .withTimeout(Duration.ofSeconds(600))
        .pollingEvery(Duration.ofSeconds(1))
        .until(ExpectedConditions.stalenessOf(captcha))
    println("captcha completed")
}
