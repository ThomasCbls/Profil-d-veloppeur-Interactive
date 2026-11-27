package com.example.tpjetpackcompose.ui

import com.example.tpjetpackcompose.data.DeveloperProfile
import com.example.tpjetpackcompose.data.Project

/**
 * État principal de l'écran profil, publié par le ProfileViewModel.
 */
data class ProfileUiState(
    val profile: DeveloperProfile,
    val searchQuery: String = "",
    val showOnlyFavorites: Boolean = false,
    val favoriteProjectIds: Set<Int> = emptySet()
) {
    /** Liste des projets réellement affichés (recherche + filtre favoris). */
    val visibleProjects: List<Project>
        get() = filterProjects(profile.projects, searchQuery, showOnlyFavorites, favoriteProjectIds)
}

/** Filtre les projets par titre, description ou technologie, et par favoris. */
fun filterProjects(
    projects: List<Project>,
    searchQuery: String,
    showOnlyFavorites: Boolean,
    favoriteProjectIds: Set<Int>
): List<Project> {
    val query = searchQuery.trim()
    return projects.filter { project ->
        val matchesSearch = query.isEmpty() ||
            project.title.contains(query, ignoreCase = true) ||
            project.description.contains(query, ignoreCase = true) ||
            project.technologies.any { it.contains(query, ignoreCase = true) }
        val matchesFavorite = !showOnlyFavorites || project.id in favoriteProjectIds
        matchesSearch && matchesFavorite
    }
}
