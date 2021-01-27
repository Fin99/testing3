package ru.fin.page

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import ru.fin.catchCaptcha
import ru.fin.waiter

abstract class PageWithMenu(val driver: WebDriver) {
    private val avatar: WebElement
    private val lists: WebElement
    private val orders: WebElement
    private val projects: WebElement
    private val team: WebElement
    private val searchInput: WebElement
    private val searchButton: WebElement

    init {
        catchCaptcha(driver)
        avatar = waiter.until {
            driver.findElement(By.xpath("//*/li[@class=\"display-from-sm\"]//*/button[@class=\"nav-popover-items-toggler\"]"))
        }
        lists = waiter.until {
            driver.findElement(By.xpath("//nav[contains(@class, \"fiverr-nav\")]//li[1]"))
        }
        orders = waiter.until {
            driver.findElement(By.xpath("//nav[contains(@class, \"fiverr-nav\")]//li[3]"))
        }
        projects = waiter.until {
            driver.findElement(By.xpath("//nav[contains(@class, \"fiverr-nav\")]//li[2]"))
        }
        team = waiter.until {
            driver.findElement(By.xpath("//nav[contains(@class, \"fiverr-nav\")]//li[4]"))
        }
        searchInput = waiter.until {
            driver.findElement(By.xpath("//input[@type=\"search\"]"))
        }
        searchButton = waiter.until {
            driver.findElement(By.xpath("//div[contains(@class, \"search-bar-package\")]//button"))
        }
    }

    fun openAvatarMenu(): AvatarMenu {
        avatar.click()
        return AvatarMenu(driver)
    }

    fun openMyListsPage(): MyListsPage {
        lists.click()
        return MyListsPage(driver)
    }

    fun openOrdersPage(): OrdersPage {
        orders.click()
        return OrdersPage(driver)
    }

    fun openProjectsPage(): ProjectsPage {
        projects.click()
        return ProjectsPage(driver)
    }

    fun openTeamPage(): TeamPage {
        team.click()
        return TeamPage(driver)
    }

    fun search(): SearchPage {
        searchInput.sendKeys("software testing")
        searchButton.click()
        return SearchPage(driver)
    }
}

class AvatarMenu(val driver: WebDriver) {
    val profilePage: WebElement
    val logout: WebElement

    init {
        logout = waiter.until {
            driver.findElement(By.xpath("//*/a[@data-event-type=\"logout\"]"))
        }
        profilePage = waiter.until {
            driver.findElement(By.xpath("//nav//li[@class=\"display-from-sm\"]//aside//li[3]/a"))
        }
    }

    fun openProfilePage(): ProfilePage {
        profilePage.click()
        return ProfilePage(driver)
    }
}