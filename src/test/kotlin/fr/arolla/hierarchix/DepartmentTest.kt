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
        val head = Employee("Mark Newton")
        val department = Department("Engineering", head)

        department.assignEmployee(employee, head, mockNotificationService)

        assertEquals(head, employee.manager)
        assertEquals(department, employee.department)
        assertEquals(2, department.headcount())

        verify(mockNotificationService).sendNotification(head, "You have a new employee: ${employee.id}")
        verify(mockNotificationService).sendNotification(department.head, "A new employee has been assigned to you: ${employee.id}")
    }

    @Test
    fun `assignEmployee should throw exception when manager is not in the correct department`() {

        val researchEmployee = Employee("John Doe")
        val researchHead = Employee("Bobby Rattle")
        val researchDepartment = Department("Research", researchHead)
        val engineeringHead = Employee("Mark Newton")
        val engineeringDepartment = Department("Engineering", engineeringHead)

        assertThrows<BusinessException> {
            researchDepartment.assignEmployee(researchEmployee, engineeringHead, mockNotificationService)
        }
    }

    @Test
    fun `empty department should at least have one employee head`() {
        val researchHead = Employee("Bobby Rattle")
        val researchDepartment = Department("Research", researchHead)

        assertEquals(researchHead, researchDepartment.head)
        assertEquals(1, researchDepartment.headcount())
        assertEquals(setOf(researchHead), researchDepartment.employees)
    }

    @Test
    fun `you cannot set as head someone of another department`() {
        val researchEmployee = Employee("John Doe")
        val researchDepartment = Department("Research", researchEmployee)

        assertThrows<BusinessException> {
            val engineeringDepartment = Department("Engineering", researchEmployee)
        }
    }
}