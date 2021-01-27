package ru.fin.page

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import ru.fin.tryFindElementOrNull
import ru.fin.waiter

class MyListsPage(driver: WebDriver) : PageWithMenu(driver) {
    private val createList: WebElement
    private val newListMenu: WebElement?

    init {
        createList = waiter.until {
            driver.findElement(By.xpath("//section[@class=\"actions\"]/button"))
        }
        newListMenu = tryFindElementOrNull {
            waiter.until {
                driver.findElement(By.xpath("//div[@class=\"collection-card\" and .//p[text()=\"new list\"]]//footer//button"))
            }
        }
    }

    fun openCreateListForm(): CreateListForm {
        createList.click()
        return CreateListForm(driver)
    }

    fun newListMenu(): ListMenu {
        newListMenu?.click()
        return ListMenu(driver)
    }

    fun listsCount(): Int =
        waiter.until {
            driver.findElements(By.xpath("//div[@class=\"collection-card\"]"))
        }.size
}

class CreateListForm(val driver: WebDriver) {
    private val listName: WebElement
    private val listDescription: WebElement
    private val createList: WebElement

    init {
        listName = waiter.until {
            driver.findElement(By.xpath("//div[@class=\"modal-body\"]//input[1]"))
        }
        listDescription = waiter.until {
            driver.findElement(By.xpath("//div[@class=\"modal-body\"]//textarea"))
        }
        createList = waiter.until {
            driver.findElement(By.xpath("//div[@class=\"modal-actions\"]//button[2]"))
        }
    }

    fun setListName(): CreateListForm {
        listName.sendKeys("new list")
        return this
    }

    fun setListDescription(): CreateListForm {
        listDescription.sendKeys("new description")
        return this
    }

    fun createList(): NewListPage {
        createList.click()
        return NewListPage(driver)
    }
}

class ListMenu(val driver: WebDriver) {
    private val deleteList: WebElement

    init {
        deleteList = waiter.until {
            driver.findElement(By.xpath("//div[@class=\"collection-card\" and .//p[text()=\"new list\"]]//footer//aside//li[4]"))
        }
    }

    fun deleteList(): DeleteListAlert {
        deleteList.click()
        return DeleteListAlert(driver)
    }
}

class DeleteListAlert(val driver: WebDriver) {
    private val submitDelete: WebElement

    init {
        submitDelete = waiter.until {
            driver.findElement(By.xpath("//div[@class=\"modal-actions\"]/button[2]"))
        }
    }

    fun submitDelete(): MyListsPage {
        submitDelete.click()
        return MyListsPage(driver)
    }
}

