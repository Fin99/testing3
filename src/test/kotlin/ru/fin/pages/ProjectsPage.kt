package ru.fin.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import ru.fin.longWaiter
import ru.fin.tryFindElementOrNull
import ru.fin.waiter

class ProjectsPage(driver: WebDriver) : PageWithMenu(driver) {
    private val createProject: WebElement
    private val newProjectMenu: WebElement?

    init {
        tryFindElementOrNull {
            longWaiter.until {
                driver.findElement(By.xpath("//section[contains(@class,\"modal-content-body rounded-corners\")]//button[@class=\"fit-icon close-btn fit-icon-clickable\"]"))
            }
        }?.click()
        createProject = waiter.until {
            driver.findElement(By.xpath("//div[@class=\"add-new-project\"]/button"))
        }
        newProjectMenu = tryFindElementOrNull {
            waiter.until {
                driver.findElement(By.xpath("//div[@class=\"projects-table\"]//div[@class=\"table-row\" and .//strong/a[text()=\"new project\"]]//div[@class=\"on-body-popover\"]"))
            }
        }
    }

    fun createNewProject(): CreateProjectForm {
        createProject.click()
        return CreateProjectForm(driver)
    }

    fun openNewProjectMenu(): ProjectMenu {
        newProjectMenu?.click()
        return ProjectMenu(driver)
    }

    fun projectsCount(): Int =
        waiter.until {
            driver.findElements(By.xpath("//div[@class=\"projects-table\"]//div[@class=\"table-row\"]"))
        }.size
}


class CreateProjectForm(val driver: WebDriver) {
    private val projectName: WebElement
    private val projectDescription: WebElement
    private val createProject: WebElement

    init {
        projectName = waiter.until {
            driver.findElement(By.xpath("//div[@class=\"project-details-body\"]//input"))
        }
        projectDescription = waiter.until {
            driver.findElement(By.xpath("//div[@class=\"project-details-body\"]//textarea"))
        }
        createProject = waiter.until {
            driver.findElement(By.xpath("//div[@class=\"project-details-body\"]//button[2]"))
        }
    }

    fun setProjectName(): CreateProjectForm {
        projectName.sendKeys("new project")
        return this
    }

    fun setProjectDescription(): CreateProjectForm {
        projectDescription.sendKeys("new description")
        return this
    }

    fun createProject(): CreateProjectAlert {
        createProject.click()
        return CreateProjectAlert(driver)
    }
}

class ProjectMenu(val driver: WebDriver) {
    private val deleteProject: WebElement

    init {
        deleteProject = waiter.until {
            driver.findElement(By.xpath("//div[@id=\"popover-on-body\"]//button[4]"))
        }
    }

    fun deleteProject(): DeleteProjectAlert {
        deleteProject.click()
        return DeleteProjectAlert(driver)
    }
}

class DeleteProjectAlert(val driver: WebDriver) {
    private val submitDelete: WebElement

    init {
        submitDelete = waiter.until {
            driver.findElement(By.xpath("//div[@class=\"btns-container\"]/button[2]"))
        }
    }

    fun submitDelete(): ProjectsPage {
        submitDelete.click()
        return ProjectsPage(driver)
    }
}

class CreateProjectAlert(val driver: WebDriver) {
    private val submitCreate: WebElement

    init {
        submitCreate = waiter.until {
            driver.findElement(By.xpath("//div[@class=\"project-successfully-created-modal\"]//button[1]"))
        }
    }

    fun submitCreate(): ProjectsPage {
        submitCreate.click()
        return ProjectsPage(driver)
    }
}

