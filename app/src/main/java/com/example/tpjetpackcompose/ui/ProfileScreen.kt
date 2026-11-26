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
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tpjetpackcompose.data.DeveloperProfile
import com.example.tpjetpackcompose.ui.components.ContactSection
import com.example.tpjetpackcompose.ui.components.ExperienceItem
import com.example.tpjetpackcompose.ui.components.ProfileHeader
import com.example.tpjetpackcompose.ui.components.ProjectCard
import com.example.tpjetpackcompose.ui.components.SectionTitle
import com.example.tpjetpackcompose.ui.components.SkillsRow

/**
 * Écran principal du profil. Toute la page est une LazyColumn :
 * chaque section est un item, et les projets / expériences sont
 * affichés avec items() et un composable réutilisable.
 */
@Composable
fun ProfileScreen(
    profile: DeveloperProfile,
    onContactClick: () -> Unit,
    onDownloadCvClick: () -> Unit,
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

        item { SectionTitle(title = "Compétences", icon = Icons.Filled.Build, modifier = horizontalPadding) }
        item { SkillsRow(skills = profile.skills) }

        item { SectionTitle(title = "Projets", icon = Icons.Filled.FolderOpen, modifier = horizontalPadding) }
        items(
            items = profile.projects,
            key = { project -> "project-${project.id}" }
        ) { project ->
            ProjectCard(project = project, modifier = horizontalPadding)
        }

        item { SectionTitle(title = "Expériences", icon = Icons.Filled.Work, modifier = horizontalPadding) }
        items(
            items = profile.experiences,
            key = { experience -> "experience-${experience.id}" }
        ) { experience ->
            ExperienceItem(experience = experience, modifier = horizontalPadding)
        }

        item { SectionTitle(title = "Contact", icon = Icons.Filled.ContactMail, modifier = horizontalPadding) }
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
