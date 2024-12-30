package com.infogain.models

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val name: String,
    val description: String
);