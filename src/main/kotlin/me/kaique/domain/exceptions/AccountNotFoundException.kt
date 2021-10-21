package me.kaique.domain.exceptions

class AccountNotFoundException(message: String): DomainException(
    message = message
)