package fr.arolla.hierarchix

class OrganizationalChart {
    fun findEmployee(s: String): Employee {
        TODO("Not yet implemented")
    }

    fun findDepartment(departmentId: String): Department {
        TODO("Not yet implemented")
    }
}

class Department(val id: String, val head: Employee) {
    fun assignEmployee(
        employee: Employee,
        manager: Employee,
        notificationService: NotificationService
    ) {
        //TODO("Should be implemented in the aggregate validation")
        if (manager.department != this) {
            throw BusinessException("${manager.id} is not in the correct department")
        }

        employee.manager = manager
        employee.department = this

        notificationService.sendNotification(manager, "You have a new employee: ${employee.id}")
        notificationService.sendNotification(this.head, "A new employee has been assigned to you: ${employee.id}")
    }
}

class Employee(val id: String) {
    var department: Department? = null
    var manager: Employee? = null

}
