package ru.fin

import org.junit.jupiter.api.Assertions
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import ru.fin.page.HomePage

fun loginTest(driver: WebDriver) {
    val avatarElement = HomePage(driver).openLoginForm().login().openAvatarMenu()

    Assertions.assertNotNull(avatarElement.logout.text)
}

fun loginSocialNetworkTest(driver: WebDriver) {
    HomePage(driver).openLoginForm().facebookLogin()

    waiter.until(ExpectedConditions.numberOfWindowsToBe(2))
}
