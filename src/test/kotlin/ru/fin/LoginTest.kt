package ru.fin

import org.junit.jupiter.api.Assertions
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

fun loginTest(driver: WebDriver) {
    login(driver)

    driver.findElement(By.xpath("//*/li[@class=\"display-from-sm\"]//*/button[@class=\"nav-popover-items-toggler\"]"))
        .click()
    Assertions.assertNotNull(driver.findElement(By.xpath("//*/a[@data-event-type=\"logout\"]")).text)
}

fun loginSocialNetworkTest(driver: WebDriver) {
    driver.findElement(By.xpath("//*/li[@class=\"header-login\"]/a")).click()
    driver.findElement(By.xpath("//*/button[@class=\"facebook-signing-button\"]")).click()

    Thread.sleep(5000)

    Assertions.assertEquals(2, driver.windowHandles.size)
}
