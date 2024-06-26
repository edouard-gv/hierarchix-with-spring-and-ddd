package fr.arolla.hierarchix

interface OrganizationalChart {
    fun findEmployee(employeeId: String): Employee
    fun findDepartment(departmentId: String): Department
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

        if (employee.department != null && employee.department != this) {
            throw BusinessException("${employee.id} is already assigned to another department")
        }

        employee.manager = manager
        employee.department = this
        this.employees.add(employee)

        validate()

        notificationService.sendNotification(manager, "You have a new employee: ${employee.id}")
        notificationService.sendNotification(this.head, "A new employee has been assigned to you: ${employee.id}")
    }

    private fun validate() {
        //check that all employees except head have their manager in the department
        employees.filter{ it != this.head }.forEach {
            if (it.manager!!.department != this) {
                throw BusinessException("${it.manager!!.id} is not in the ${this.id} department")
            }
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
