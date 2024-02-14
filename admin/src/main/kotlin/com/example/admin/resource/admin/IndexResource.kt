package com.example.admin.resource.admin

import com.example.admin.dto.DataDto
import io.quarkus.qute.CheckedTemplate
import io.quarkus.qute.TemplateInstance
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path

/**
 * # Admin Index Page
 *
 * @see https://ja.quarkus.io/guides/qute
 * @see https://ja.quarkus.io/guides/qute-reference#injecting-beans-directly-in-templates
 * @author treetips
 */
@Path("/admin")
class IndexResource {

  @CheckedTemplate(basePath = "admin")
  object Templates {
    @JvmStatic external fun index(item: DataDto? = null): TemplateInstance

    @JvmStatic external fun about(item: DataDto? = null): TemplateInstance
  }

  @GET
  fun index(): TemplateInstance =
      Templates.index(DataDto(pageTitle = "Qute Example", id = 1, name = "John Doe"))

  @GET @Path("/about") fun about(): TemplateInstance = Templates.about()
}
