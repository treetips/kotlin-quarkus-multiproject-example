package com.example.dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

/**
 * Request DTO
 *
 * @author treetips
 */
data class RequestDto(
    @field:NotNull(message = "{errors.api.validation.id.requires}")
    @field:Max(value = 5, message = "{errors.api.validation.id.max}")
    private val id: Int?,
    @field:NotBlank(message = "{errors.api.validation.name.requires}")
    @field:Size(max = 5, message = "{errors.api.validation.name.max}")
    private val name: String?,
) {
  val validId
    get() = id!!

  val validName
    get() = name!!
}
