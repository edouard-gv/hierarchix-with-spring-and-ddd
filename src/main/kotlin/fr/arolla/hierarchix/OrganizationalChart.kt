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
    val employees: MutableSet<Employee> = mutableSetOf()

    init {
        if (head.department != null) {
            throw BusinessException("${head.id} is already assigned to a department")
        }
        head.department = this
        this.employees.add(head)

    }

    fun assignEmployee(
        employee: Employee,
        manager: Employee,
        notificationService: NotificationService
    ) {

        employee.manager = manager
        employee.department = this
        this.employees.add(employee)

        validate(employee)

        notificationService.sendNotification(manager, "You have a new employee: ${employee.id}")
        notificationService.sendNotification(this.head, "A new employee has been assigned to you: ${employee.id}")
    }

    private fun validate(employee: Employee) {
        if (employee.manager!!.department != this) {
            throw BusinessException("${employee.manager!!.id} is not in the correct department")
        }
    }

    fun headcount(): Int {
        return employees.size
    }
}

class Employee(val id: String) {
    var department: Department? = null
    var manager: Employee? = null

}
