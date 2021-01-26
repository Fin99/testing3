package ru.fin

import org.openqa.selenium.*
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.FluentWait
import java.time.Duration


fun searchService(driver: WebDriver) {
    search(driver, true)

    val oldWindow = driver.windowHandle
    driver.findElement(By.xpath("//div[contains(@class, \"content-row\")]//div[@class=\"gig-card-layout\"][1]//h3"))
        .click()

    driver.switchTo().window(driver.windowHandles.find { it.equals(oldWindow).not() })

    Thread.sleep(5000)
}

fun search(driver: WebDriver, isLogin: Boolean) {
    if (isLogin) {
        driver.findElement(By.xpath("//input[@type=\"search\"]"))
    } else {
        driver.findElement(By.xpath("//div[contains(@class, \"main-content\")]//div[contains(@class, \"search-bar-package\")]//input[@type=\"search\"]"))
    }.sendKeys("software testing")

    if (isLogin) {
        driver.findElement(By.xpath("//div[contains(@class, \"search-bar-package\")]//button"))
    } else {
        driver.findElement(By.xpath("//div[contains(@class, \"main-content\")]//div[contains(@class, \"search-bar-package\") and //input[@type=\"search\"]]//button"))
    }.click()

    Thread.sleep(5000)
}


fun login(driver: WebDriver) {
    driver.findElement(By.xpath("//*/li[@class=\"header-login\"]/a")).click()
    driver.findElement(By.xpath("//*/input[@id=\"login\"]")).sendKeys("imashkaman@gmail.com")
    driver.findElement(By.xpath("//*/input[@id=\"password\"]")).sendKeys("123qweQWE")
    driver.findElement(By.xpath("//*/div[@class=\"sign-in-form\"]/button[contains(@class, \"submit-button\")]"))
        .click()

    catchCaptcha(driver)
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
        .withTimeout(Duration.ofSeconds(5))
        .pollingEvery(Duration.ofSeconds(1))
        .ignoring(NoSuchElementException::class.java)
        .until {
            it.findElement(By.xpath("//h1[text()=\"One Small Step\"]"))
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
