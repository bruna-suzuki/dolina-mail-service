package com.dolina_mail_service.services

import com.dolina_mail_service.dtos.RequestEmailPurchaseDto
import com.dolina_mail_service.enums.StatusEmail
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
            text = "Caro ${payload.userName}, já estamos processando o seu pedido, e em breve, você irá receber muito c###." +
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
                    "Equipe Dolina" +
                    "\n" +
                    "\n" +
                    "PS: Esse dia parece que não acaba, estou com muito chocolate dourado, meu pacotinho perfeito." +
                    "\n" +
                    "Tamo junto, mAnO",
            sendDateEmail = LocalDateTime.now(),
            statusEmail = StatusEmail.SENT
        )
        try {
            val message = SimpleMailMessage().apply {   //apply
                setTo(emailModel.emailTo)
                from = emailModel.emailFrom
                subject = emailModel.subject
                text = emailModel.text
            }
            emailSender.send(message)

        } catch (e: Exception) {
            emailModel.statusEmail = StatusEmail.ERROR
        } finally {
            emailRepository.save(emailModel)
        }
    }
}
