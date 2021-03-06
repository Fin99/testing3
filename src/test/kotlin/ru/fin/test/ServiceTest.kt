package ru.fin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.openqa.selenium.WebDriver
import ru.fin.page.HomePage

fun paymentTest(driver: WebDriver) {
    val paymentOptionsPage = HomePage(driver).openLoginForm().login()
        .search().openService()
        .startPayment().continuePayment()

    assertEquals("Payment Options", paymentOptionsPage.paymentOptions.text)
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