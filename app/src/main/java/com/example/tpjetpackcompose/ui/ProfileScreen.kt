package com.example.tpjetpackcompose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.ContactMail
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tpjetpackcompose.R
import com.example.tpjetpackcompose.data.DeveloperProfile
import com.example.tpjetpackcompose.data.ProfessionalLink
import com.example.tpjetpackcompose.data.Project
import com.example.tpjetpackcompose.data.sampleProfile
import com.example.tpjetpackcompose.ui.components.ContactSection
import com.example.tpjetpackcompose.ui.components.ExperienceItem
import com.example.tpjetpackcompose.ui.components.LinksRow
import com.example.tpjetpackcompose.ui.components.ProfileHeader
import com.example.tpjetpackcompose.ui.components.ProjectCard
import com.example.tpjetpackcompose.ui.components.ProjectFilterRow
import com.example.tpjetpackcompose.ui.components.ProjectSearchBar
import com.example.tpjetpackcompose.ui.components.SectionTitle
import com.example.tpjetpackcompose.ui.components.SkillsRow
import com.example.tpjetpackcompose.ui.theme.ProfilDeveloppeurTheme

/**
 * Écran principal du profil, stateless : il reçoit les valeurs à afficher
 * et remonte les actions de l'utilisateur via des callbacks.
 */
@Composable
fun ProfileScreen(
    profile: DeveloperProfile,
    visibleProjects: List<Project>,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    showOnlyFavorites: Boolean,
    onToggleFavoriteFilter: () -> Unit,
    favoriteProjectIds: Set<Int>,
    onFavoriteClick: (Int) -> Unit,
    onContactClick: () -> Unit,
    onDownloadCvClick: () -> Unit,
    onLinkClick: (ProfessionalLink) -> Unit,
    modifier: Modifier = Modifier
) {
    val horizontalPadding = Modifier.padding(horizontal = 16.dp)

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            ProfileHeader(
                name = profile.name,
                role = profile.role,
                description = profile.description,
                isAvailable = profile.isAvailable,
                modifier = horizontalPadding
            )
        }

        item { SectionTitle(title = stringResource(R.string.section_skills), icon = Icons.Filled.Build, modifier = horizontalPadding) }
        item { SkillsRow(skills = profile.skills) }

        item { SectionTitle(title = stringResource(R.string.section_projects), icon = Icons.Filled.FolderOpen, modifier = horizontalPadding) }
        item {
            ProjectSearchBar(
                query = searchQuery,
                onQueryChange = onSearchQueryChange,
                modifier = horizontalPadding
            )
        }
        item {
            ProjectFilterRow(
                visibleCount = visibleProjects.size,
                showOnlyFavorites = showOnlyFavorites,
                onToggleFavoriteFilter = onToggleFavoriteFilter,
                modifier = horizontalPadding
            )
        }
        if (visibleProjects.isEmpty()) {
            item {
                Text(
                    text = stringResource(R.string.no_project_found),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = horizontalPadding
                )
            }
        }
        items(
            items = visibleProjects,
            key = { project -> "project-${project.id}" }
        ) { project ->
            ProjectCard(
                project = project,
                isFavorite = project.id in favoriteProjectIds,
                onFavoriteClick = { onFavoriteClick(project.id) },
                modifier = horizontalPadding
            )
        }

        item { SectionTitle(title = stringResource(R.string.section_experiences), icon = Icons.Filled.Work, modifier = horizontalPadding) }
        items(
            items = profile.experiences,
            key = { experience -> "experience-${experience.id}" }
        ) { experience ->
            ExperienceItem(experience = experience, modifier = horizontalPadding)
        }

        item { SectionTitle(title = stringResource(R.string.section_links), icon = Icons.Filled.Link, modifier = horizontalPadding) }
        item { LinksRow(links = profile.links, onLinkClick = onLinkClick) }

        item { SectionTitle(title = stringResource(R.string.section_contact), icon = Icons.Filled.ContactMail, modifier = horizontalPadding) }
        item {
            ContactSection(
                contact = profile.contact,
                onContactClick = onContactClick,
                onDownloadCvClick = onDownloadCvClick,
                modifier = horizontalPadding
            )
        }
    }
}

@Preview(
    name = "1 - Recherche vide, aucun favori",
    showBackground = true,
    heightDp = 1600
)
@Composable
fun ProfileScreenDefaultPreview() {
    val state = ProfileUiState(profile = sampleProfile())
    ProfilDeveloppeurTheme(darkTheme = false) {
        ProfileScreen(
            profile = state.profile,
            visibleProjects = state.visibleProjects,
            searchQuery = state.searchQuery,
            onSearchQueryChange = {},
            showOnlyFavorites = state.showOnlyFavorites,
            onToggleFavoriteFilter = {},
            favoriteProjectIds = state.favoriteProjectIds,
            onFavoriteClick = {},
            onContactClick = {},
            onDownloadCvClick = {},
            onLinkClick = {}
        )
    }
}

@Preview(
    name = "2 - Recherche active, filtre favoris (sombre)",
    showBackground = true,
    backgroundColor = 0xFF121318,
    heightDp = 1600
)
@Composable
fun ProfileScreenFavoritesPreview() {
    val state = ProfileUiState(
        profile = sampleProfile(),
        searchQuery = "Kotlin",
        showOnlyFavorites = true,
        favoriteProjectIds = setOf(1, 3, 4)
    )
    ProfilDeveloppeurTheme(darkTheme = true) {
        ProfileScreen(
            profile = state.profile,
            visibleProjects = state.visibleProjects,
            searchQuery = state.searchQuery,
            onSearchQueryChange = {},
            showOnlyFavorites = state.showOnlyFavorites,
            onToggleFavoriteFilter = {},
            favoriteProjectIds = state.favoriteProjectIds,
            onFavoriteClick = {},
            onContactClick = {},
            onDownloadCvClick = {},
            onLinkClick = {}
        )
    }
}
