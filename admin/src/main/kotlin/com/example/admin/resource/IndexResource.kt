package com.example.admin.resource

import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import java.net.URI

/**
 * # Index Page
 *
 * @author treetips
 */
@Path("/")
@Consumes(MediaType.TEXT_HTML)
class IndexResource {

  @GET fun index(): Response = Response.temporaryRedirect(URI("/admin")).build()
}
