package com.example.tpjetpackcompose.ui

import androidx.lifecycle.ViewModel
import com.example.tpjetpackcompose.data.sampleProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * Gère l'état principal de l'écran profil.
 * L'état modifiable reste privé, l'interface observe uniquement le StateFlow public.
 */
class ProfileViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState(profile = sampleProfile()))
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun onSearchQueryChange(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun onToggleFavoriteFilter() {
        _uiState.update { it.copy(showOnlyFavorites = !it.showOnlyFavorites) }
    }

    fun onFavoriteClick(projectId: Int) {
        _uiState.update { state ->
            val favorites = state.favoriteProjectIds
            val updated = if (projectId in favorites) favorites - projectId else favorites + projectId
            state.copy(favoriteProjectIds = updated)
        }
    }
}
