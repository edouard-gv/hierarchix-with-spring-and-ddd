package fr.arolla.hierarchix

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*

class DefaultServiceTest {

    private lateinit var defaultService: DefaultService
    private lateinit var mockOrganizationalChart: OrganizationalChart
    private lateinit var mockNotificationService: NotificationService

    @BeforeEach
    fun setup() {
        mockOrganizationalChart = mock()
        mockNotificationService = mock()
        defaultService = DefaultService(mockOrganizationalChart, mockNotificationService)
    }

    @Test
    fun `assignEmployee should assign employee to manager and department`() {
        val employeeId = "John Doe"
        val managerId = "Jane Smith"
        val headId = "Mark Newton"
        val departmentId = "Engineering"

        val employee = Employee(employeeId)
        val manager = Employee(managerId)
        val head = Employee(headId)
        val department = Department(departmentId, head)
        head.department = department
        employee.department = department
        manager.department = department

        whenever(mockOrganizationalChart.findEmployee(employeeId)).thenReturn(employee)
        whenever(mockOrganizationalChart.findEmployee(managerId)).thenReturn(manager)
        whenever(mockOrganizationalChart.findDepartment(departmentId)).thenReturn(department)

        defaultService.assignEmployee(employeeId, managerId, departmentId)

        assertEquals(manager, employee.manager)
        assertEquals(department, employee.department)

        verify(mockNotificationService).sendNotification(manager, "You have a new employee: ${employee.id}")
        verify(mockNotificationService).sendNotification(department.head, "A new employee has been assigned to you: ${employee.id}")
    }

    @Test
    fun `assignEmployee should throw exception when manager is not in the correct department`() {
        val researchEmployeeId = "John Doe"
        val engineeringManagerId = "Jane Smith"
        val researchHeadId = "Bobby Rattle"
        val engineeringHeadId = "Mark Newton"

        val researchDepartmentId = "Research"
        val engineeringDepartmentId = "Engineering"

        val researchEmployee = Employee(researchEmployeeId)
        val engineeringManager = Employee(engineeringManagerId)
        val researchHead = Employee(researchHeadId)
        val researchDepartment = Department(researchDepartmentId, researchHead)
        val engineeringHead = Employee(engineeringHeadId)
        val engineeringDepartment = Department(engineeringDepartmentId, engineeringHead)

        researchEmployee.department = researchDepartment
        engineeringManager.department = engineeringDepartment
        researchHead.department = researchDepartment
        engineeringHead.department = engineeringDepartment

        whenever(mockOrganizationalChart.findEmployee(researchEmployeeId)).thenReturn(researchEmployee)
        whenever(mockOrganizationalChart.findEmployee(engineeringManagerId)).thenReturn(engineeringManager)
        whenever(mockOrganizationalChart.findDepartment(researchDepartmentId)).thenReturn(researchDepartment)
        whenever(mockOrganizationalChart.findDepartment(engineeringDepartmentId)).thenReturn(engineeringDepartment)

        assertThrows<BusinessException> {
            defaultService.assignEmployee(researchEmployeeId, engineeringManagerId, researchDepartmentId)
        }
    }
}