package me.kaique.domain.exceptions

class AlreadyExistsException(message: String): DomainException(
    message = message
)