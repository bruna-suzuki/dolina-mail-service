package com.dolina_mail_service.services

import com.dolina_mail_service.dtos.RequestEmailPurchaseDto
import com.dolina_mail_service.enums.StatusEmailEnum
import com.dolina_mail_service.models.EmailModel
import com.dolina_mail_service.repositories.EmailRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class EmailService(
    private val emailSender: JavaMailSender,
    private val emailRepository: EmailRepository) {

    @Value(value = "\${spring.mail.username}")
    lateinit var emailFrom: String

    @Transactional
    fun sendEmail(payload: RequestEmailPurchaseDto) {
        val emailModel = EmailModel(
            emailFrom = emailFrom,
            emailTo = payload.userEmail,
            subject = "Confirmação do seu pedido!",
            text = "Caro ${payload.userName}, já estamos processando o seu pedido. Em breve, chegará no seu endereço." +
                    "\n" +
                    "\n" +
                    "Resumo da compra:" +
                    "\n" +
                    "Produto: ${payload.itemName}." +
                    "\n" +
                    "Endereço de entrega:" +
                    " ${payload.address}" +
                    "\n" +
                    "\n" +
                    "Obrigado por comprar com a gente!" +
                    "\n" +
                    "\n" +
                    "Equipe Dolina",
            sendDateEmail = LocalDateTime.now(),
            statusEmail = StatusEmailEnum.SENT
        )
        runCatching {
            SimpleMailMessage().apply {   /////ESTUDAR
                setTo(emailModel.emailTo)
                from = emailModel.emailFrom
                subject = emailModel.subject
                text = emailModel.text
            }.also { emailSender.send(it)
                emailModel.statusEmail = StatusEmailEnum.SENT ///ADICIONADO
            }

        }.onFailure {
            emailModel.statusEmail = StatusEmailEnum.ERROR
        }.also {
            emailRepository.save(emailModel)
        }
    }

    fun getAllEmail(): MutableList<EmailModel> =
        emailRepository.findAll()
}
