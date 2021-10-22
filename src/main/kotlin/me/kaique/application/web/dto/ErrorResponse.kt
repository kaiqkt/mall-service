package me.kaique.application.web.dto

import me.kaique.application.web.handler.ErrorType

data class ErrorResponse(
    val type: ErrorType,
    val message: String,
    val cause: String?
)