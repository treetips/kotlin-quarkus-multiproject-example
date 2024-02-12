package com.example.dto

/**
 * Api Error Response DTO
 *
 * @author treetips
 */
data class ApiErrorResponseDto(
    val status: Int,
    val message: String,
    var trace: String? = null,
    var details: ApiDetailErrorResponseDto? = null
)
