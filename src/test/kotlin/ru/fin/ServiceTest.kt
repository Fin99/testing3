package ru.fin

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import ru.fin.pages.HomePage

fun paymentTest(driver: WebDriver) {
    val startPayment = HomePage(driver).openLoginForm().login()
        .search().openService()
        .startPayment()

    assertEquals("Payment Options", startPayment.paymentOptions.text)
}

fun likeTest(driver: WebDriver) {
    var service = HomePage(driver).openLoginForm().login()
        .search().openService()

    val isLiked = service.isLiked()

    service = service.like()

    assertNotEquals(isLiked, service.isLiked())
}

fun addToListTest(driver: WebDriver) {
    var service = HomePage(driver).openLoginForm().login()
        .search().openService()

    val isLiked = service.isLiked()

    service = service.addToList()

    assertNotEquals(isLiked, service.isLiked())
}

fun reportTest(driver: WebDriver) {
    val resultForm = HomePage(driver).openLoginForm().login()
        .search().openService().createReport()
        .selectReportReason().next()
        .selectReportContent().setReferenceUrl().submit()
    assertEquals("Thank You For Reporting", resultForm.thanks.text)
}