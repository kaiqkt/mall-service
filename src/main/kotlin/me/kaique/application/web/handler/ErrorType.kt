package me.kaique.application.web.handler

enum class ErrorType(val message: String) {
   INTERNAL_SERVER_ERROR("Internal server error"),
   UNEXPECTED_PERSISTENCE_ERROR("Internal server error"),
   ACCOUNT_NOT_FOUND("Account not found"),
   ALREADY_EXIST_ERROR("Already exist error"),
   COMMUNICATION_CLIENT_ERROR("Communication client error"),
   STORE_CATEGORY_ERROR("Store category not exist error"),
   PRODUCT_CATEGORY_ERROR("Product category not exist error"),
}