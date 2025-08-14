package com.dolina_mail_service.consumers

import com.dolina_mail_service.dtos.RequestEmailPurchaseDto
import com.dolina_mail_service.services.EmailService
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class EmailPurchaseConsumer(private var emailService: EmailService) {

    @RabbitListener(queues = arrayOf("\${broker.queue.purchase.email}"))
    fun sendingPurchaseEmail(@Payload payload: RequestEmailPurchaseDto) {
        emailService.sendEmail(payload)
    }
}