package ru.fin.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import ru.fin.tryFindElementOrNull
import ru.fin.waiter

class TeamPage(driver: WebDriver) : PageWithMenu(driver) {
    private val inviteMember: WebElement
    val pendingMember: WebElement?

    init {
        inviteMember = waiter.until {
            driver.findElement(By.xpath("//div[@class=\"invite-members-button\"]/button"))
        }
        pendingMember = tryFindElementOrNull {
            waiter.until {
                driver.findElement(By.xpath("//section//div[@class=\"table-row pending\"]"))
            }
        }
    }

    fun openInviteMemberForm(): InviteMemberForm {
        inviteMember.click()
        return InviteMemberForm(driver)
    }
}

class InviteMemberForm(val driver: WebDriver) {
    private val email: WebElement
    private val project: WebElement
    private val inviteMember: WebElement

    init {
        email = waiter.until {
            driver.findElement(By.xpath("(//div[contains(@class, \"modal-body\")]//input)[1]"))
        }
        project = waiter.until {
            driver.findElement(By.xpath("//div[@class=\"selectable-list\"]//span"))
        }
        inviteMember = waiter.until {
            driver.findElement(By.xpath("//footer[@class=\"modal-footer\"]//button[2]"))
        }
    }

    fun setMemberEmail(): InviteMemberForm {
        email.sendKeys("imashka@gmail.com")
        waiter.until {
            driver.findElement(By.xpath("//div[@class='user-combobox']//span[@class='orca-user-combobox-option-new-value']"))
        }.click()
        return this
    }

    fun selectProject(): InviteMemberForm {
        project.click()
        return this
    }

    fun inviteMember(): TeamPage {
        inviteMember.click()
        return TeamPage(driver)
    }
}