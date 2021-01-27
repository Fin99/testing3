package ru.fin

import org.junit.jupiter.api.Assertions.assertEquals
import org.openqa.selenium.WebDriver
import ru.fin.page.HomePage

fun updateProfileTest(driver: WebDriver) {
    val profilePage = HomePage(driver).openLoginForm().login()
        .openAvatarMenu().openProfilePage()
        .setNewUsername().saveUsername()

    catchCaptcha(driver)

    assertEquals("Settings successfully updated.", profilePage.getSuccessfullyUpdatedMessageText())
}

fun createListTest(driver: WebDriver) {
    val newListPage = HomePage(driver).openLoginForm().login()
        .openMyListsPage().openCreateListForm()
        .setListName().setListDescription().createList()

    assertEquals("new list", newListPage.list!!.text)

    var myLists = newListPage.openMyListsPage()
    val oldListsSize = myLists.listsCount()
    myLists = myLists.newListMenu().deleteList().submitDelete()

    assertEquals(oldListsSize - 1, myLists.listsCount())
}