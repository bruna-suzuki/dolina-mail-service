package com.dolina_mail_service.repositories

import com.dolina_mail_service.models.EmailModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface EmailRepository : JpaRepository<EmailModel, UUID> {
}