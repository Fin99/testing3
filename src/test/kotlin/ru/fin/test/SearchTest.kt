package ru.fin

import org.junit.jupiter.api.Assertions.assertEquals
import org.openqa.selenium.WebDriver
import ru.fin.page.HomePage

fun searchTest(driver: WebDriver) {
    val searchPage = HomePage(driver).search()

    assertEquals("Results for \"software testing\"", searchPage.resultQuery.text)
}

fun categoriesTest(driver: WebDriver) {
    val category = HomePage(driver).openLoginForm().login()
        .openCategory()
    val subCategoryName = category.subCategory.text

    assertEquals(subCategoryName, category.openSubCategory().subCategoryName.text)
}

fun serviceTest(driver: WebDriver) {
    val search = HomePage(driver).search()
    val username = search.username.text

    assertEquals(username, search.openAnonymousService().username.text)
}