package ru.fin.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import ru.fin.catchCaptcha
import ru.fin.waiter

class ServicePage(val driver: WebDriver) {
    val username: WebElement
    private val payment: WebElement
    private val like: WebElement
    private val list: WebElement
    private val report: WebElement

    init {
        catchCaptcha(driver)
        username = waiter.until {
            driver.findElement(By.xpath("//div[@class=\"profile-name\"]//a"))
        }
        payment = waiter.until {
            driver.findElement(By.xpath("//div[@class=\"payment-summary\"]//button"))
        }
        like = waiter.until {
            driver.findElement(By.xpath("//div[@class=\"top-nav\"]//button[contains(@class, \"icn-heart\")]"))
        }
        list = waiter.until {
            driver.findElement(By.xpath("(//div[@class=\"collect-wrapper\"]//button)[1]"))
        }
        report = waiter.until {
            driver.findElement(By.xpath("//button[contains(@class, \"btn-report-gig\")]"))
        }
    }

    fun startPayment(): PaymentPage {
        payment.click()
        return PaymentPage(driver)
    }

    fun isLiked(): Boolean = like.getAttribute("class").contains("collected")

    fun like(): ServicePage {
        like.click()
        return ServicePage(driver)
    }

    fun addToList(): ServicePage {
        list.click()
        waiter.until {
            driver.findElement(By.xpath("(//aside//div[@class=\"menu\"]//button)[1]"))
        }.click()
        return ServicePage(driver)
    }

    fun createReport(): ReportFirstForm {
        report.click()
        return ReportFirstForm(driver)
    }
}

class ReportFirstForm(val driver: WebDriver) {
    private val reportReason: WebElement
    private val next: WebElement

    init {
        reportReason = waiter.until {
            driver.findElement(By.xpath("//ul[@class=\"report-types-options\"]//li[1]//span"))
        }
        next = waiter.until {
            driver.findElement(By.xpath("//div[@class=\"report-gig-footer\"]/button"))
        }
    }

    fun selectReportReason(): ReportFirstForm {
        reportReason.click()
        return this
    }

    fun next(): ReportSecondForm {
        next.click()
        return ReportSecondForm(driver)
    }
}

class ReportSecondForm(val driver: WebDriver) {
    private val reportContent: WebElement
    private val referenceUrl: WebElement
    private val submit: WebElement

    init {
        reportContent = waiter.until {
            driver.findElement(By.xpath("//div[@class=\"content-types-options\"]/label[1]/span"))
        }
        referenceUrl = waiter.until {
            driver.findElement(By.xpath("//div[contains(@class, \"reference-url\")]//input"))
        }
        submit = waiter.until {
            driver.findElement(By.xpath("//div[@class=\"report-gig-footer\"]/button[2]"))
        }
    }

    fun selectReportContent(): ReportSecondForm {
        reportContent.click()
        return this
    }

    fun setReferenceUrl(): ReportSecondForm {
        referenceUrl.sendKeys("good url")
        return this
    }

    fun submit(): ReportResultForm {
        submit.click()
        return ReportResultForm(driver)
    }
}

class ReportResultForm(val driver: WebDriver) {
    val thanks: WebElement

    init {
        thanks = waiter.until {
            driver.findElement(By.xpath("//header[contains(@class, \"modal\")]/h2"))
        }
    }
}