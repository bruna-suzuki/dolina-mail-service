package com.dolina_mail_service.controllers

import com.dolina_mail_service.models.EmailModel
import com.dolina_mail_service.services.EmailService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/emailPurchase")
class EmailPurchaseController(private val emailService: EmailService) {

    @GetMapping
    fun getAllEmail(): ResponseEntity<List<EmailModel>> {
        val emails = emailService.getAllEmail()
        return ResponseEntity.status(HttpStatus.OK).body(emails)
    }
}