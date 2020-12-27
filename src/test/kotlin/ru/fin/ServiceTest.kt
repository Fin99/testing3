package ru.fin

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

fun paymentTest(driver: WebDriver) {
    login(driver)
    searchService(driver)
    driver.findElement(By.xpath("//aside[contains(@class, \"poly-stick\")]//form[1]//footer/button[@type=\"submit\"]"))
        .click()
    Thread.sleep(5000)
    driver.findElement(By.xpath("//div[@class=\"payment-summary\"]//button")).click()
    Thread.sleep(5000)


    Assertions.assertEquals(
        "Payment Options",
        driver.findElement(By.xpath("//article[@class=\"payment-methods\"]//header//h6")).text
    )
}

fun likeTest(driver: WebDriver) {
    login(driver)
    searchService(driver)
    val like =
        driver.findElement(By.xpath("//div[@class=\"top-nav\"]//button[contains(@class, \"icn-heart\")]"))
    val liked = like.getAttribute("class").contains("collected")
    like.click()
    Thread.sleep(5000)

    assertNotEquals(
        liked,
        driver.findElement(By.xpath("//div[@class=\"top-nav\"]//button[contains(@class, \"icn-heart\")]"))
            .getAttribute("class").contains("collected")
    )
}

fun addToListTest(driver: WebDriver) {
    login(driver)
    searchService(driver)
    driver.findElement(By.xpath("(//div[@class=\"collect-wrapper\"]//button)[1]")).click()

    val liked =
        driver.findElement(By.xpath("//div[@class=\"top-nav\"]//button[contains(@class, \"icn-heart\")]"))
            .getAttribute("class").contains("collected")
    driver.findElement(By.xpath("(//aside//div[@class=\"menu\"]//button)[1]")).click()

    Thread.sleep(5000)

    assertNotEquals(
        liked,
        driver.findElement(By.xpath("//div[@class=\"top-nav\"]//button[contains(@class, \"icn-heart\")]"))
            .getAttribute("class").contains("collected")
    )
}

fun reportTest(driver: WebDriver) {
    login(driver)
    searchService(driver)
    driver.findElement(By.xpath("//button[contains(@class, \"btn-report-gig\")]")).click()
    Thread.sleep(5000)
    driver.findElement(By.xpath("//ul[@class=\"report-types-options\"]//li[1]//span")).click()
    Thread.sleep(5000)
    driver.findElement(By.xpath("//div[@class=\"report-gig-footer\"]/button")).click()
    Thread.sleep(5000)
    driver.findElement(By.xpath("//div[@class=\"content-types-options\"]/label[1]/span")).click()
    driver.findElement(By.xpath("//div[contains(@class, \"reference-url\")]//input")).sendKeys("good url")
    driver.findElement(By.xpath("//div[@class=\"report-gig-footer\"]/button[2]")).click()
    Thread.sleep(5000)


    Assertions.assertEquals(
        "Thank You For Reporting",
        driver.findElement(By.xpath("//header[contains(@class, \"modal\")]/h2")).text
    )
}