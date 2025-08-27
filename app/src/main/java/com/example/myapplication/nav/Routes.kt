package com.example.myapplication.nav

import com.example.myapplication.data.model.Character
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class Route(val route: String) {
    data object List : Route("list")

    data object Detail : Route("detail/{character}") {
        private val charset = StandardCharsets.UTF_8.toString()
        fun pass(character: Character): String {
            val json = Json.encodeToString(character)
            val encoded = URLEncoder.encode(json, charset)
            return "detail/$encoded"
        }
        fun read(encoded: String): Character {
            val json = URLDecoder.decode(encoded, charset)
            return Json.decodeFromString(json)
        }
    }
}
