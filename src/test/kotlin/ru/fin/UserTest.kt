package ru.fin

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver

fun updateProfileTest(driver: WebDriver) {
    login(driver)

    driver.findElement(By.xpath("//nav//li[@class=\"display-from-sm\"]//button[//div[contains(@class, \"user-status\")]]"))
        .click()

    driver.findElement(By.xpath("//nav//li[@class=\"display-from-sm\"]//aside//li[3]/a")).click()
    driver.findElement(By.xpath("//input[@id=\"user_profile_attributes_name\"]"))
        .sendKeys(Keys.CONTROL.toString() + "A")
    driver.findElement(By.xpath("//input[@id=\"user_profile_attributes_name\"]")).sendKeys(Keys.DELETE)
    driver.findElement(By.xpath("//input[@id=\"user_profile_attributes_name\"]")).sendKeys("Fin")
    driver.findElement(By.xpath("//form[@class=\"edit_user\"]//input[@type=\"submit\"]")).click()


    Assertions.assertEquals(
        "Settings successfully updated.",
        driver.findElement(By.xpath("//div[contains(@class, \"flash-message\")]/p")).text
    )
}

fun createListTest(driver: WebDriver) {
    login(driver)

    driver.findElement(By.xpath("//nav[contains(@class, \"fiverr-nav\")]//li[1]")).click()
    driver.findElement(By.xpath("//section[@class=\"actions\"]/button")).click()
    Thread.sleep(5000)

    //create list
    driver.findElement(By.xpath("//div[@class=\"modal-body\"]//input[1]")).sendKeys("new list")
    driver.findElement(By.xpath("//div[@class=\"modal-body\"]//textarea")).sendKeys("new description")
    driver.findElement(By.xpath("//div[@class=\"modal-actions\"]//button[2]")).click()
    Thread.sleep(5000)

    Assertions.assertEquals(
        "new list",
        driver.findElement(By.xpath("//div[@class=\"name-cta-container\"]//h2")).text
    )

    //delete
    driver.findElement(By.xpath("//nav[contains(@class, \"fiverr-nav\")]//li[1]")).click()
    Thread.sleep(5000)

    val oldSize = driver.findElements(By.xpath("//div[@class=\"collection-card\"]")).size

    driver.findElement(By.xpath("//div[@class=\"collection-card\" and .//p[text()=\"new list\"]]//footer//button"))
        .click()
    Thread.sleep(5000)
    driver.findElement(By.xpath("//div[@class=\"collection-card\" and .//p[text()=\"new list\"]]//footer//aside//li[4]"))
        .click()
    Thread.sleep(5000)
    driver.findElement(By.xpath("//div[@class=\"modal-actions\"]/button[2]")).click()
    Thread.sleep(5000)
    assertEquals(
        oldSize - 1,
        driver.findElements(By.xpath("//div[@class=\"collection-card\"]")).size
    )
}