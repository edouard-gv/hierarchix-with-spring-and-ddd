package fr.arolla.hierarchix

class NotificationService {
    fun sendNotification(employee: Employee, message: String) {
        println("Sending notification to ${employee.id}: $message")
    }

}
