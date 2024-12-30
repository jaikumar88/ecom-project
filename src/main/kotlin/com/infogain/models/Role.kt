package com.infogain.models

import kotlinx.serialization.Serializable

@Serializable
data class Role (
    val id: Int?=0,
    val name: String,
    val description: String
)
