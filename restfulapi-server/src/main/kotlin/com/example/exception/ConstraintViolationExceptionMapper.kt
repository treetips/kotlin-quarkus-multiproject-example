package com.example.exception

import com.example.dto.ApiDetailErrorResponseDto
import com.example.dto.ApiErrorResponseDto
import com.example.share.service.MessageService
import jakarta.inject.Inject
import jakarta.validation.ConstraintViolationException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.core.Response.Status
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider
import org.jboss.logging.Logger

/**
 * Constraint Violation Exception Handler
 *
 * @author treetips
 */
@Provider
class ConstraintViolationExceptionMapper : ExceptionMapper<ConstraintViolationException> {
  @Inject private lateinit var log: Logger
  @Inject private lateinit var messageService: MessageService

  override fun toResponse(e: ConstraintViolationException): Response {
    log.warn(e.message, e)
    val apiDetailErrorResponseDto =
        ApiDetailErrorResponseDto().apply {
          e.constraintViolations.forEach { constraintViolation ->
            constraintViolation.propertyPath.toString().let {
              if (it.contains("requestDto.id")) {
                this.id = constraintViolation.message
              } else if (it.contains("requestDto.name")) {
                this.name = constraintViolation.message
              }
            }
          }
        }

    return ApiErrorResponseDto(
            status = Status.BAD_REQUEST.statusCode,
            message = messageService.getMessage(messageKey = "errors.api.validation"),
            details = apiDetailErrorResponseDto)
        .let { Response.status(Response.Status.BAD_REQUEST).entity(it).build() }
  }
}
