package fr.arolla.hierarchix.infra

import fr.arolla.hierarchix.Department
import fr.arolla.hierarchix.Employee
import fr.arolla.hierarchix.OrganizationalChart
import org.springframework.stereotype.Repository

@Repository
class OrganizationalChartInMemoryImpl : OrganizationalChart {

    val departments = mutableMapOf<String, Department>()
    val employees = mutableMapOf<String, Employee>()

    init {
        val head = Employee("Barbara Song")
        val defaultDepartment = Department("Engineering", head)
        this.departments[defaultDepartment.id] = defaultDepartment
        this.employees[head.id] = head
    }

    override fun findEmployee(employeeId: String): Employee {
        return this.employees[employeeId] ?: Employee(employeeId)
    }

    override fun findDepartment(departmentId: String): Department {
        return this.departments[departmentId] ?: throw IllegalArgumentException("Department $departmentId not found")
    }
}