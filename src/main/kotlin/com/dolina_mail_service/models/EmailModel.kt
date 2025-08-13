package com.dolina_mail_service.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "tb_email")
data class EmailModel(

    @Id
    val id: UUID = UUID.randomUUID(),

    val userEmail: String,

    val userName: String,

    val itemName: String,

    val address: String
)
