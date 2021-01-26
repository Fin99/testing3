package ru.fin

import org.junit.jupiter.api.Assertions
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import ru.fin.pages.HomePage
import ru.fin.pages.MainPage

fun loginTest(driver: WebDriver) {
    HomePage(driver).openLoginElement().login()
    val avatarElement = MainPage(driver).openAvatarMenu()

    Assertions.assertNotNull(avatarElement.logout.text)
}

fun loginSocialNetworkTest(driver: WebDriver) {
    HomePage(driver).openLoginElement().facebookLogin()

    WebDriverWait(driver, 10).until(ExpectedConditions.numberOfWindowsToBe(2))
}
