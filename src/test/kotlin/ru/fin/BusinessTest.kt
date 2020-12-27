package ru.fin

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

fun createProjectTest(driver: WebDriver) {
    login(driver)

    //create project
    driver.findElement(By.xpath("//nav[contains(@class, \"fiverr-nav\")]//li[2]")).click()
    Thread.sleep(5000)

    driver.findElement(By.xpath("//section[@class=\"MODAL__modal-content-body-ucaa90899 modal-content-body rounded-corners\"]//button[@class=\"fit-icon close-btn fit-icon-clickable\"]"))
        .click()
    Thread.sleep(5000)

    driver.findElement(By.xpath("//div[@class=\"add-new-project\"]/button")).click()
    Thread.sleep(5000)
    driver.findElement(By.xpath("//div[@class=\"project-details-body\"]//input")).sendKeys("new project")
    driver.findElement(By.xpath("//div[@class=\"project-details-body\"]//textarea")).sendKeys("new description")
    driver.findElement(By.xpath("//div[@class=\"project-details-body\"]//button[2]")).click()
    Thread.sleep(5000)
    driver.findElement(By.xpath("//div[@class=\"project-successfully-created-modal\"]//button[1]")).click()
    Thread.sleep(5000)

    //delete project
    val oldSize = driver.findElements(By.xpath("//div[@class=\"projects-table\"]//div[@class=\"table-row\"]")).size

    driver.findElement(By.xpath("//div[@class=\"projects-table\"]//div[@class=\"table-row\" and .//strong/a[text()=\"new project\"]]//div[@class=\"on-body-popover\"]"))
        .click()
    Thread.sleep(5000)
    driver.findElement(By.xpath("//div[@id=\"popover-on-body\"]//div[7]/button"))
        .click()
    Thread.sleep(5000)
    driver.findElement(By.xpath("//div[@class=\"btns-container\"]/button[2]")).click()
    Thread.sleep(5000)

    assertEquals(
        oldSize - 1,
        driver.findElements(By.xpath("//div[@class=\"projects-table\"]//div[@class=\"table-row\"]")).size
    )
}

fun editCommandTest(driver: WebDriver) {
    login(driver)

    driver.findElement(By.xpath("//nav[contains(@class, \"fiverr-nav\")]//li[4]")).click()
    driver.findElement(By.xpath("//div[@class=\"invite-members-button\"]/button")).click()
    Thread.sleep(5000)

    driver.findElement(By.xpath("(//div[contains(@class, \"modal-body\")]//input)[1]")).sendKeys("imashkaman@gmail.com")
    Thread.sleep(5000)
    driver.findElement(By.xpath("//div[@class='user-combobox']//span[@class='orca-user-combobox-option-new-value']"))
        .click()
    driver.findElement(By.xpath("//div[@class=\"selectable-list\"]//span")).click()
    driver.findElement(By.xpath("//footer[@class=\"modal-footer\"]//button[2]")).click()


    Assertions.assertNotNull(
        driver.findElement(By.xpath("//section//div[@class=\"table-row pending\"]"))
    )
}

fun checkOrdersTest(driver: WebDriver) {
    login(driver)

    driver.findElement(By.xpath("//nav[contains(@class, \"fiverr-nav\")]//li[3]")).click()

    assertEquals(
        "No orders to track yet",
        driver.findElement(By.xpath("//main[@class=\"main-content\"]//h3")).text
    )
}