package com.example.myapplication.data.repo

import com.example.myapplication.data.model.ApiResponse
import com.example.myapplication.data.remote.Network
import io.ktor.client.call.*
import io.ktor.client.request.*

class CharactersRepository {
    private val client = Network.client
    suspend fun fetchOnce(): ApiResponse =
        client.get("https://rickandmortyapi.com/api/character").body()
}
