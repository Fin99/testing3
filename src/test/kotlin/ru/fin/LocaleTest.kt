package ru.fin

import org.junit.jupiter.api.Assertions
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver


fun changeLanguageTest(driver: WebDriver) {
    var languages =
        driver.findElement(By.xpath("//*/div[@id=\"LocaleSettings-component\"]//*/div[contains(@class, \"language-selection-title\")]/span[@class=\"title-label\"]"))

    Assertions.assertEquals("English", languages.text)
    languages.click()
    driver.findElement(By.xpath("//*/div[@id=\"LocaleSettings-component\"]//*/p[text()=\"Deutsch\"]")).click()

    Thread.sleep(5000)

    languages =
        driver.findElement(By.xpath("//*/div[@id=\"LocaleSettings-component\"]//*/div[contains(@class, \"language-selection-title\")]/span[@class=\"title-label\"]"))
    Assertions.assertEquals("Deutsch", languages.text)
}

fun changeCurrencyTest(driver: WebDriver) {
    var currency =
        driver.findElement(By.xpath("//*/div[@id=\"LocaleSettings-component\"]//*/div[@class=\"locale-button-title\"]"))

    Assertions.assertEquals("\$USD", currency.text)
    currency.click()
    driver.findElement(By.xpath("//*/div[@id=\"LocaleSettings-component\"]//*/p[text()=\"Euro (EUR) - €\"]"))
        .click()

    Thread.sleep(5000)

    currency =
        driver.findElement(By.xpath("//*/div[@id=\"LocaleSettings-component\"]//*/div[@class=\"locale-button-title\"]"))
    Assertions.assertEquals("€EUR", currency.text)
}