package ru.fin

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.Dimension
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

class ChromeTest {
    private lateinit var driver: WebDriver

    companion object {
        @BeforeAll
        @JvmStatic
        fun initProperty() {
            System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe")
        }
    }

    @BeforeEach
    fun createDriver() {
        driver = ChromeDriver()
        driver.manage().window().size = Dimension(1200, 1200)
        driver["https://www.fiverr.com/"]

        catchCaptcha(driver)
    }

    @AfterEach
    fun closeDriver() {
        driver.quit()
    }

    @Test
    fun chromeLoginTest() {
        loginTest(driver)
    }

    @Test
    fun chromeLoginSocialNetworkTest() {
        loginSocialNetworkTest(driver)
    }


    @Test
    fun chromeChangeLanguageTest() {
        changeLanguageTest(driver)
    }

    @Test
    fun chromeChangeCurrencyTest() {
        changeCurrencyTest(driver)
    }

    @Test
    fun chromeSearchTest() {
        searchTest(driver)
    }

    @Test
    fun chromeCategoriesTest() {
        categoriesTest(driver)
    }

    @Test
    fun chromeServiceTest() {
        serviceTest(driver)
    }

    @Test
    fun chromePaymentTest() {
        paymentTest(driver)
    }

    @Test
    fun chromeLikeTest() {
        likeTest(driver)
    }

    @Test
    fun chromeAddToListTest() {
        addToListTest(driver)
    }

    @Test
    fun chromeReportTest() {
        reportTest(driver)
    }

    @Test
    fun chromeUpdateProfileTest() {
        updateProfileTest(driver)
    }

    @Test
    fun chromeCreateListTest() {
        createListTest(driver)
    }

    @Test
    fun chromeCreateProjectTest() {
        createProjectTest(driver)
    }

    @Test
    fun chromeEditCommandTest() {
        editCommandTest(driver)
    }

    @Test
    fun chromeCheckOrdersTest() {
        checkOrdersTest(driver)
    }
}