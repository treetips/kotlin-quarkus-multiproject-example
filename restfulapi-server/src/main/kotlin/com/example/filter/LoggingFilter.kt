package com.example.filter

import jakarta.inject.Inject
import jakarta.ws.rs.container.ContainerRequestContext
import jakarta.ws.rs.container.ContainerRequestFilter
import jakarta.ws.rs.ext.Provider
import org.jboss.logging.Logger

/**
 * Logging Filter
 *
 * @author treetips
 */
@Provider
class LoggingFilter : ContainerRequestFilter {
  @Inject private lateinit var log: Logger

  override fun filter(requestContext: ContainerRequestContext) {
    log.infov("URL = {0}", requestContext.uriInfo.requestUri.toString())
  }
}
