package ru.fin.page

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import ru.fin.waiter

class CategoryPage(driver: WebDriver): PageWithMenu(driver) {
    val subCategory: WebElement

    init {
        subCategory = waiter.until {
            driver.findElement(By.xpath("//div[@class=\"main-content\"]/a[1]"))
        }
    }

    fun openSubCategory(): SubCategoryPage {
        subCategory.click()
        return SubCategoryPage(driver)
    }
}

class SubCategoryPage(driver: WebDriver): PageWithMenu(driver) {
    val subCategoryName: WebElement

    init {
        subCategoryName = waiter.until {
            driver.findElement(By.xpath("//header[@class=\"subcategory-header\"]/div/h1"))
        }
    }
}


