package com.dolina_mail_service.dtos

data class RequestEmailPurchase(
    val userEmail: String,
    val userName: String,
    val itemName: String,
    val address: String
)
