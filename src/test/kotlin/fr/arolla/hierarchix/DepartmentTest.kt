package fr.arolla.hierarchix

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*

class DepartmentTest {

    private lateinit var mockNotificationService: NotificationService

    @BeforeEach
    fun setup() {
        mockNotificationService = mock()
    }

    @Test
    fun `assignEmployee should assign employee to manager and department`() {

        val employee = Employee("John Doe")
        val manager = Employee("Jane Smith")
        val head = Employee("Mark Newton")
        val department = Department("Engineering", head)
        head.department = department
        employee.department = department
        manager.department = department

        department.assignEmployee(employee, manager, mockNotificationService)

        assertEquals(manager, employee.manager)
        assertEquals(department, employee.department)

        verify(mockNotificationService).sendNotification(manager, "You have a new employee: ${employee.id}")
        verify(mockNotificationService).sendNotification(department.head, "A new employee has been assigned to you: ${employee.id}")
    }

    @Test
    fun `assignEmployee should throw exception when manager is not in the correct department`() {

        val researchEmployee = Employee("John Doe")
        val engineeringManager = Employee("Jane Smith")
        val researchHead = Employee("Bobby Rattle")
        val researchDepartment = Department("Research", researchHead)
        val engineeringHead = Employee("Mark Newton")
        val engineeringDepartment = Department("Engineering", engineeringHead)

        researchEmployee.department = researchDepartment
        engineeringManager.department = engineeringDepartment
        researchHead.department = researchDepartment
        engineeringHead.department = engineeringDepartment

        assertThrows<BusinessException> {
            researchDepartment.assignEmployee(researchEmployee, engineeringManager, mockNotificationService)
        }
    }
}