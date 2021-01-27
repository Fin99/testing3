package ru.fin

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.openqa.selenium.WebDriver
import ru.fin.page.HomePage

fun createProjectTest(driver: WebDriver) {
    var projects = HomePage(driver).openLoginForm().login()
        .openProjectsPage()

    val startProjectCount = projects.projectsCount()
    projects = projects.createNewProject()
        .setProjectName().setProjectDescription().createProject().submitCreate()

    val projectCountWithNewProject = projects.projectsCount()

    assertEquals(startProjectCount + 1, projectCountWithNewProject)

    val endProjectCount = projects.openNewProjectMenu().deleteProject().submitDelete().projectsCount()

    assertEquals(endProjectCount + 1, projectCountWithNewProject)
}

fun editCommandTest(driver: WebDriver) {
    val teamWithPendingMember = HomePage(driver).openLoginForm().login()
        .openTeamPage().openInviteMemberForm()
        .setMemberEmail().selectProject().inviteMember()

    Assertions.assertNotNull(teamWithPendingMember.pendingMember)
}

fun checkOrdersTest(driver: WebDriver) {
    val ordersPage = HomePage(driver).openLoginForm().login()
        .openOrdersPage()

    assertEquals("No orders to track yet", ordersPage.noOrders.text)
}