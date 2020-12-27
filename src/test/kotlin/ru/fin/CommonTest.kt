package ru.fin

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

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

    Thread.sleep(5000)
}