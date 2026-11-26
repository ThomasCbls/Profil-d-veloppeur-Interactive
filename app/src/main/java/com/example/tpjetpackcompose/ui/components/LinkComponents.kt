package com.example.tpjetpackcompose.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Link
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tpjetpackcompose.data.ProfessionalLink
import com.example.tpjetpackcompose.ui.theme.ProfilDeveloppeurTheme

/** Chip réutilisable représentant un lien professionnel (GitHub, LinkedIn...). */
@Composable
fun LinkChip(
    link: ProfessionalLink,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    AssistChip(
        onClick = onClick,
        label = {
            Text(
                text = link.label,
                style = MaterialTheme.typography.labelLarge
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Link,
                contentDescription = null,
                modifier = Modifier.size(AssistChipDefaults.IconSize)
            )
        },
        shape = MaterialTheme.shapes.extraLarge,
        colors = AssistChipDefaults.assistChipColors(
            leadingIconContentColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier
    )
}

/** Liste horizontale des liens professionnels, affichée avec une LazyRow. */
@Composable
fun LinksRow(
    links: List<ProfessionalLink>,
    onLinkClick: (ProfessionalLink) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = links,
            key = { link -> link.url }
        ) { link ->
            LinkChip(
                link = link,
                onClick = { onLinkClick(link) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LinkChipPreview() {
    ProfilDeveloppeurTheme {
        LinkChip(
            link = ProfessionalLink(label = "GitHub", url = "https://github.com/ThomasCbls"),
            onClick = {},
            modifier = Modifier.padding(8.dp)
        )
    }
}
