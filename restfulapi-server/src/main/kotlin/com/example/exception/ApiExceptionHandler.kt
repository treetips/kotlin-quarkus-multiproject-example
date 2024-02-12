package com.example.exception

import com.example.dto.ApiErrorResponseDto
import io.quarkus.runtime.util.ExceptionUtil
import jakarta.inject.Inject
import jakarta.ws.rs.WebApplicationException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.core.Response.Status
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider
import org.jboss.logging.Logger

/**
 * Api Exception Handler
 *
 * @author treetips
 */
@Provider
class ApiExceptionHandler : ExceptionMapper<Exception> {
  @Inject private lateinit var log: Logger

  override fun toResponse(e: Exception): Response {
    val trace = ExceptionUtil.generateStackTrace(e)
    return when (e) {
      is WebApplicationException -> {
        Response.fromResponse(e.response)
            .entity(
                ApiErrorResponseDto(
                    status = e.response.status, message = e.message!!, trace = trace))
            .build()
      }
      is IllegalArgumentException -> {
        Status.BAD_REQUEST.let {
          Response.status(it)
              .entity(
                  ApiErrorResponseDto(status = it.statusCode, message = e.message!!, trace = trace))
              .build()
        }
      }
      else -> {
        log.error(e.message, e)
        Status.INTERNAL_SERVER_ERROR.let {
          Response.serverError()
              .entity(
                  ApiErrorResponseDto(status = it.statusCode, message = e.message!!, trace = trace))
              .build()
        }
      }
    }
  }
}
