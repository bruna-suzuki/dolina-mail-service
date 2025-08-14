package com.dolina_mail_service.dtos

data class RequestEmailPurchaseDto(
    val userEmail: String,
    val userName: String,
    val itemName: String,
    val address: String
)
