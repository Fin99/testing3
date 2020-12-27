package ru.fin

import org.junit.jupiter.api.Assertions.assertEquals
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

fun searchTest(driver: WebDriver) {
    search(driver, false)

    assertEquals(
        "Results for \"software testing\"",
        driver.findElement(By.xpath("//div[@class=\"search-header\"]/span")).text
    )
}

fun categoriesTest(driver: WebDriver) {
    login(driver)

    driver.findElement(By.xpath("//ul[contains(@class, \"categories\")]/li[1]/p")).click()
    Thread.sleep(1000)
    driver.findElement(By.xpath("//ul[contains(@class, \"categories\")]/li[1]//a[@class=\"explore-all-link\"]"))
        .click()

    Thread.sleep(5000)
    val subcategory = driver.findElement(By.xpath("//div[@class=\"main-content\"]/a[1]"))
    val subcategoryName = subcategory.text
    subcategory.click()

    Thread.sleep(5000)

    assertEquals(
        subcategoryName,
        driver.findElement(By.xpath("//header[@class=\"subcategory-header\"]/div/h1")).text
    )
}

fun serviceTest(driver: WebDriver) {
    search(driver, false)
    val nickname =
        driver.findElement(By.xpath("//div[contains(@class, \"content-row\")]//div[@class=\"gig-card-layout\"][1]//div[@class=\"seller-name\"]//a")).text

    val oldWindow = driver.windowHandle
    driver.findElement(By.xpath("//div[contains(@class, \"content-row\")]//div[@class=\"gig-card-layout\"][1]//h3"))
        .click()

    Thread.sleep(5000)

    driver.switchTo().window(driver.windowHandles.find { it.equals(oldWindow).not() })

    assertEquals(nickname, driver.findElement(By.xpath("//div[@class=\"profile-name\"]//a")).text)
}