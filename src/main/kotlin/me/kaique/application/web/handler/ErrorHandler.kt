package me.kaique.application.web.handler

import io.javalin.Javalin
import io.javalin.http.Context
import me.kaique.application.web.dto.ErrorResponse
import org.eclipse.jetty.http.HttpStatus
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ErrorHandler {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    fun configure(app: Javalin) {
        app.exception(Exception::class.java) { e , ctx ->
            handleException(e, ctx)
        }
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
                message = ErrorType.INTERNAL_SERVER_ERROR.message
            )
        )
    }
}