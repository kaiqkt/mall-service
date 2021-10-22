package me.kaique.application.web.handler

import io.javalin.Javalin
import io.javalin.http.Context
import me.kaique.application.web.dto.ErrorResponse
import me.kaique.domain.exceptions.AccountNotFoundException
import me.kaique.domain.exceptions.AlreadyExistsException
import me.kaique.domain.exceptions.CommunicationException
import me.kaique.domain.exceptions.InvalidStoreCategoryException
import me.kaique.resources.repositories.exceptions.UnexpectedPersistenceException
import org.eclipse.jetty.http.HttpStatus
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ErrorHandler {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    fun configure(app: Javalin) {
        app.exception(Exception::class.java) { e , ctx ->
            handleException(e, ctx)
        }
        app.exception(UnexpectedPersistenceException::class.java) { e, ctx ->
            handleException(e, ctx)
        }
        app.exception(AccountNotFoundException::class.java) { e, ctx ->
            handleException(e, ctx)
        }
        app.exception(AlreadyExistsException::class.java) { e, ctx ->
            handleException(e, ctx)
        }
        app.exception(CommunicationException::class.java) { e, ctx ->
            handleException(e, ctx)
        }
        app.exception(InvalidStoreCategoryException::class.java) { e, ctx ->
            handleException(e, ctx)
        }
    }

    private fun handleException(
        e: CommunicationException,
        ctx: Context
    ) {
        log.error("Communication error: ${ctx.method()} ${ctx.url()}")
        ctx.status(HttpStatus.INTERNAL_SERVER_ERROR_500)
        ctx.json(
            ErrorResponse(
                type = ErrorType.COMMUNICATION_CLIENT_ERROR,
                message = ErrorType.COMMUNICATION_CLIENT_ERROR.message,
                cause = e.message
            )
        )
    }

    private fun handleException(
        e: InvalidStoreCategoryException,
        ctx: Context
    ) {
        log.error("Invalid category store: ${ctx.method()} ${ctx.url()}")
        ctx.status(HttpStatus.BAD_REQUEST_400)
        ctx.json(
            ErrorResponse(
                type = ErrorType.STORE_CATEGORY_ERROR,
                message = ErrorType.STORE_CATEGORY_ERROR.message,
                cause = e.message
            )
        )
    }

    private fun handleException(
        e: AccountNotFoundException,
        ctx: Context
    ) {
        log.error("Account not found error: ${ctx.method()} ${ctx.url()}")
        ctx.status(HttpStatus.BAD_REQUEST_400)
        ctx.json(
            ErrorResponse(
                type = ErrorType.ACCOUNT_NOT_FOUND,
                message = ErrorType.ACCOUNT_NOT_FOUND.message,
                cause = e.message
            )
        )
    }

    private fun handleException(
        e: AlreadyExistsException,
        ctx: Context
    ) {
        log.error("Already exists or used found error: ${ctx.method()} ${ctx.url()}")
        ctx.status(HttpStatus.BAD_REQUEST_400)
        ctx.json(
            ErrorResponse(
                type = ErrorType.ALREADY_EXIST_ERROR,
                message = ErrorType.ALREADY_EXIST_ERROR.message,
                cause = e.message
            )
        )
    }

    private fun handleException(
        e: UnexpectedPersistenceException,
        ctx: Context
    ) {
        log.error("Unexpected Persistence Exception error: ${ctx.method()} ${ctx.url()}")
        ctx.status(HttpStatus.INTERNAL_SERVER_ERROR_500)
        ctx.json(
            ErrorResponse(
                type = ErrorType.UNEXPECTED_PERSISTENCE_ERROR,
                message = ErrorType.UNEXPECTED_PERSISTENCE_ERROR.message,
                cause = e.message
            )
        )
    }

    private fun handleException(
        e: Exception,
        ctx: Context
    ) {
        log.error("Exception error: ${ctx.method()} ${ctx.url()}")
        ctx.status(HttpStatus.INTERNAL_SERVER_ERROR_500)
        ctx.json(
            ErrorResponse(
                type = ErrorType.INTERNAL_SERVER_ERROR,
                message = ErrorType.INTERNAL_SERVER_ERROR.message,
                cause = e.message
            )
        )
    }
}