package fr.arolla.hierarchix

class DefaultService(val organizationalChart: OrganizationalChart, val notificationService: NotificationService) {

    fun assignEmployee(employeeId: String, managerId: String, departmentId: String) {
        val employee: Employee = organizationalChart.findEmployee(employeeId)
        val department = organizationalChart.findDepartment(departmentId)
        val manager = organizationalChart.findEmployee(managerId)
        //TODO("Should be implemented in the aggregate validation")
        if (manager.department != department) {
            throw BusinessException("${manager.id} is not in the correct department")
        }
        employee.manager = manager
        employee.department = department
        notificationService.sendNotification(manager, "You have a new employee: ${employee.id}")
        notificationService.sendNotification(department.head, "A new employee has been assigned to you: ${employee.id}")
    }
}