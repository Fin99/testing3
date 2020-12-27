package ru.fin

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.Dimension
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver

class FirefoxTest {
    private lateinit var driver: WebDriver

    companion object {
        @BeforeAll
        @JvmStatic
        fun initProperty() {
            System.setProperty("webdriver.gecko.driver", "C:/geckodriver.exe")
        }
    }

    @BeforeEach
    fun createDriver() {
        driver = FirefoxDriver()
        driver.manage().window().size = Dimension(1200, 1200)
        driver["https://www.fiverr.com/"]
    }

    @AfterEach
    fun closeDriver() {
        driver.quit()
    }

    @Test
    fun firefoxLoginTest() {
        loginTest(driver)
    }

    @Test
    fun firefoxLoginSocialNetworkTest() {
        loginSocialNetworkTest(driver)
    }


    @Test
    fun firefoxChangeLanguageTest() {
        changeLanguageTest(driver)
    }

    @Test
    fun firefoxChangeCurrencyTest() {
        changeCurrencyTest(driver)
    }

    @Test
    fun firefoxSearchTest() {
        searchTest(driver)
    }

    @Test
    fun firefoxCategoriesTest() {
        categoriesTest(driver)
    }

    @Test
    fun firefoxServiceTest() {
        serviceTest(driver)
    }

    @Test
    fun firefoxPaymentTest() {
        paymentTest(driver)
    }

    @Test
    fun firefoxLikeTest() {
        likeTest(driver)
    }

    @Test
    fun firefoxAddToListTest() {
        addToListTest(driver)
    }

    @Test
    fun firefoxReportTest() {
        reportTest(driver)
    }

    @Test
    fun firefoxUpdateProfileTest() {
        updateProfileTest(driver)
    }

    @Test
    fun firefoxCreateListTest() {
        createListTest(driver)
    }

    @Test
    fun firefoxCreateProjectTest() {
        createProjectTest(driver)
    }

    @Test
    fun firefoxEditCommandTest() {
        editCommandTest(driver)
    }

    @Test
    fun firefoxCheckOrdersTest() {
        checkOrdersTest(driver)
    }
}