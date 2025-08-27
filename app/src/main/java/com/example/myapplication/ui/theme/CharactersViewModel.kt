package com.example.myapplication.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.Character
import com.example.myapplication.data.repo.CharactersRepository
import kotlinx.coroutines.launch

data class CharactersState(
    val isLoading: Boolean = true,
    val total: Int = 0,
    val list: List<Character> = emptyList(),
    val error: String? = null
)

class CharactersViewModel : ViewModel() {
    private val repo = CharactersRepository()
    var state by mutableStateOf(CharactersState())
        private set

    init { load() }

    fun load() {
        viewModelScope.launch {
            try {
                state = state.copy(isLoading = true, error = null)
                val resp = repo.fetchOnce()
                state = CharactersState(false, resp.info.count, resp.results, null)
            } catch (e: Exception) {
                state = state.copy(isLoading = false, error = e.message ?: "Error")
            }
        }
    }
}
