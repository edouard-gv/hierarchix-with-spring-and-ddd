package fr.arolla.hierarchix.infra

import fr.arolla.hierarchix.Employee
import fr.arolla.hierarchix.NotificationService
import org.springframework.stereotype.Repository

@Repository
class NotificationServiceFakeImpl : NotificationService {
    override fun sendNotification(employee: Employee, message: String) {
        println("Sending notification to ${employee.id}: $message")
    }
}