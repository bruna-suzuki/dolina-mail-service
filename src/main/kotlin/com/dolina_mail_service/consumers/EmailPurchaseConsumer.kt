package com.dolina_mail_service.consumers

import com.dolina_mail_service.dtos.RequestEmailPurchase
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class EmailPurchaseConsumer {

    @RabbitListener(queues = arrayOf("\${broker.queue.purchase.email}"))
    fun sendingPurchaseEmail(@Payload payload: RequestEmailPurchase) {
        println("Para: ${payload.userEmail}" +
                "Olá ${payload.userName}, sua compra ${payload.itemName} foi realizada com sucesso. Seu item chegará em poucos dias no endereço ${payload.address}.")
    }
}