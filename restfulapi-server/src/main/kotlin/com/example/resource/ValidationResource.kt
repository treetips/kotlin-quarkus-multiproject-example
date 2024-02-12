package com.example.resource

import com.example.dto.RequestDto
import jakarta.enterprise.context.ApplicationScoped
import jakarta.validation.Valid
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

/**
 * Validation Resource
 *
 * @author treetips
 */
@ApplicationScoped
@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
class ValidationResource {

  @GET
  @Path("/validation")
  @Consumes(MediaType.APPLICATION_JSON)
  fun validate(@Valid requestDto: RequestDto) = requestDto

  @GET @Path("/exception") fun exception(@Valid requestDto: RequestDto) = listOf(1)[99999]
}
