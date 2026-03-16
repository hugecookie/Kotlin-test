package org.example.model

data class Space(
    val name: String,
    val type: SpaceType,
    val widthMm: Int,
    val depthMm: Int,
    val heightMm: Int
)
