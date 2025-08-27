package com.example.myapplication.ui.theme


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repo.CharactersRepository
import com.example.myapplication.data.model.Character
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

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
                state = CharactersState(
                    isLoading = false,
                    total = resp.info.count,
                    list = resp.results
                )
            } catch (e: Exception) {
                state = state.copy(isLoading = false, error = e.message ?: "Error")
            }
        }
    }
}
