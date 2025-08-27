package com.example.myapplication.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(val info: Info, val results: List<Character>)

@Serializable
data class Info(val count: Int, val pages: Int, val next: String? = null, val prev: String? = null)

@Serializable
data class NamedUrl(val name: String, val url: String)

@Serializable
data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: NamedUrl,
    val location: NamedUrl,
    val image: String,
    val url: String,
    val created: String
)
