package ru.fin

import org.junit.jupiter.api.Assertions.assertEquals
import org.openqa.selenium.WebDriver
import ru.fin.page.HomePage


fun changeLanguageTest(driver: WebDriver) {
    var homePage = HomePage(driver)
    assertEquals("English", homePage.language.text)
    homePage = homePage.openLanguageForm().selectDeutsch()
    assertEquals("Deutsch", homePage.language.text)
    homePage = homePage.openLanguageForm().selectEnglish()
    assertEquals("English", homePage.language.text)
}

fun changeCurrencyTest(driver: WebDriver) {
    var homePage = HomePage(driver)
    assertEquals("\$USD", homePage.currency.text)
    homePage = homePage.openCurrencyForm().selectEUR()
    assertEquals("€EUR", homePage.currency.text)
    homePage = homePage.openCurrencyForm().selectUSD()
    assertEquals("\$USD", homePage.currency.text)
}