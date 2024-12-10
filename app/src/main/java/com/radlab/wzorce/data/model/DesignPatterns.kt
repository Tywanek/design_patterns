package com.radlab.wzorce.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DesignPatterns(
    @Json(name = "design_patterns") val designPatterns: DesignPatternsData
)

@JsonClass(generateAdapter = true)
data class DesignPatternsData(
    val creational: List<Pattern>,
    val structural: List<Pattern>,
    val behavioral: List<Pattern>
)

@JsonClass(generateAdapter = true)
data class Pattern(
    val name: String,
    val description: String
)
