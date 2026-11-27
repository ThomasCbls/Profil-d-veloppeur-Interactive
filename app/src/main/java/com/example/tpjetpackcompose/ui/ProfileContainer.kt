package com.example.tpjetpackcompose.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * Fait le lien entre le ViewModel et Compose :
 * récupère le ViewModel, collecte le StateFlow et transmet état + callbacks à l'écran.
 */
@Composable
fun ProfileContainer(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    ProfileScreen(
        profile = uiState.profile,
        visibleProjects = uiState.visibleProjects,
        searchQuery = uiState.searchQuery,
        onSearchQueryChange = viewModel::onSearchQueryChange,
        showOnlyFavorites = uiState.showOnlyFavorites,
        onToggleFavoriteFilter = viewModel::onToggleFavoriteFilter,
        favoriteProjectIds = uiState.favoriteProjectIds,
        onFavoriteClick = viewModel::onFavoriteClick,
        onContactClick = {
            // Pas de vraie action : ouvrirait une app mail plus tard
        },
        onDownloadCvClick = {
            // Pas de vraie action : téléchargerait le CV plus tard
        },
        onLinkClick = {
            // Pas de vraie action : ouvrirait le lien dans le navigateur plus tard
        },
        modifier = modifier
    )
}
