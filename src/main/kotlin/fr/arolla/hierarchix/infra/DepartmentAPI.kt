package fr.arolla.hierarchix.infra

import fr.arolla.hierarchix.Employee
import fr.arolla.hierarchix.NotificationService
import fr.arolla.hierarchix.OrganizationalChart
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class DepartmentAPI(val organizationalChart: OrganizationalChart, val notificationService: NotificationService) {

    @PostMapping("/assign")
    fun assignEmployee(@RequestBody data: AssignEmployeeRequest) {
        val employee: Employee = organizationalChart.findEmployee(data.employeeId)
        val department = organizationalChart.findDepartment(data.departmentId)
        val manager = organizationalChart.findEmployee(data.managerId)
        department.assignEmployee(employee, manager, notificationService)
    }

    class AssignEmployeeRequest(val employeeId: String, val managerId: String, val departmentId: String)

}