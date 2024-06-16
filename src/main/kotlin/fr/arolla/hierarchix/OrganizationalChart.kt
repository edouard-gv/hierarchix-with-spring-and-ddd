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
}

class Employee(val id: String) {
    var department: Department? = null
    var manager: Employee? = null

}
