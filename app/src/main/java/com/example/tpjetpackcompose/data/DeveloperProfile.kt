package com.example.tpjetpackcompose.data

/**
 * Données locales du profil, écrites directement dans le code Kotlin
 * (pas d'API, pas de base de données).
 */
data class DeveloperProfile(
    val name: String,
    val role: String,
    val description: String,
    val isAvailable: Boolean,
    val skills: List<Skill>,
    val projects: List<Project>,
    val experiences: List<Experience>,
    val contact: ContactInfo
)

data class Skill(
    val name: String,
    val level: SkillLevel
)

enum class SkillLevel(val label: String) {
    BEGINNER("Débutant"),
    INTERMEDIATE("Intermédiaire"),
    ADVANCED("Avancé")
}

data class Project(
    val id: Int,
    val title: String,
    val description: String,
    val technologies: List<String>
)

data class Experience(
    val id: Int,
    val position: String,
    val company: String,
    val period: String
)

data class ContactInfo(
    val email: String,
    val location: String,
    val availability: String
)

fun sampleProfile(): DeveloperProfile = DeveloperProfile(
    name = "Thomas Camboulas",
    role = "Développeur Android junior",
    description = "Étudiant passionné par le développement mobile, je conçois des " +
        "applications Android modernes avec Kotlin et Jetpack Compose. " +
        "Je recherche un stage pour mettre mes compétences en pratique.",
    isAvailable = true,
    skills = listOf(
        Skill("Kotlin", SkillLevel.ADVANCED),
        Skill("Jetpack Compose", SkillLevel.INTERMEDIATE),
        Skill("Material 3", SkillLevel.INTERMEDIATE),
        Skill("Git", SkillLevel.ADVANCED),
        Skill("Java", SkillLevel.INTERMEDIATE),
        Skill("SQL", SkillLevel.BEGINNER)
    ),
    projects = listOf(
        Project(
            id = 1,
            title = "Product Explorer",
            description = "Catalogue de produits avec écran d'accueil, liste et fiche détail.",
            technologies = listOf("Kotlin", "Compose", "LazyColumn")
        ),
        Project(
            id = 2,
            title = "Profil développeur",
            description = "Fiche de profil construite avec des composables réutilisables.",
            technologies = listOf("Compose", "Material 3")
        ),
        Project(
            id = 3,
            title = "WatchList",
            description = "Application pour suivre les films et séries à regarder.",
            technologies = listOf("Kotlin", "Android")
        )
    ),
    experiences = listOf(
        Experience(
            id = 1,
            position = "Projets Android en formation",
            company = "Cours Jetpack Compose",
            period = "2026"
        ),
        Experience(
            id = 2,
            position = "Développeur web (projet tutoré)",
            company = "École",
            period = "2025"
        )
    ),
    contact = ContactInfo(
        email = "thomas.camboulas@example.com",
        location = "France",
        availability = "Stage dès maintenant"
    )
)
