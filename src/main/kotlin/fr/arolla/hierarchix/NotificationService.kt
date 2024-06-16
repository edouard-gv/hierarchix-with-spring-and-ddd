package fr.arolla.hierarchix

interface NotificationService {
    fun sendNotification(employee: Employee, message: String)
}
