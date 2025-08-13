package com.dolina_mail_service.consumers

import com.dolina_mail_service.dtos.DeleteUserMessageDto
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class DeleteEmailConsumer {

    @RabbitListener(queues = ["\${broker.queue.delete.email.name}"])
    fun listenDeleteQueue(payload: DeleteUserMessageDto) {
        println(payload)
    }
}