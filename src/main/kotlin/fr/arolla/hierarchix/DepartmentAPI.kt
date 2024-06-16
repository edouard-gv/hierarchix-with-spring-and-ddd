package fr.arolla.hierarchix

class DepartmentAPI(val organizationalChart: OrganizationalChart, val notificationService: NotificationService) {

    fun assignEmployee(employeeId: String, managerId: String, departmentId: String) {
        val employee: Employee = organizationalChart.findEmployee(employeeId)
        val department = organizationalChart.findDepartment(departmentId)
        val manager = organizationalChart.findEmployee(managerId)
        department.assignEmployee(employee, manager, notificationService)
    }
}