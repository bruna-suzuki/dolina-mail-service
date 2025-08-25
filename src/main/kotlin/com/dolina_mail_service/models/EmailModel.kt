package com.dolina_mail_service.models

import com.dolina_mail_service.enums.StatusEmailEnum
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "tb_email")
data class EmailModel(

    @Id
    val id: UUID? = UUID.randomUUID(),

    val emailFrom: String? = null,

    val emailTo: String? = null,

    val subject: String? = null,

    @Column(columnDefinition = "TEXT")
    val text: String? = null,

    val sendDateEmail: LocalDateTime? = null,

    @Enumerated(EnumType.STRING)
    var statusEmail: StatusEmailEnum? = null
)