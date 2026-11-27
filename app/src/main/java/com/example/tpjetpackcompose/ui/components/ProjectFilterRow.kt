package com.example.tpjetpackcompose.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tpjetpackcompose.R
import com.example.tpjetpackcompose.ui.theme.ProfilDeveloppeurTheme

/** Ligne affichant le nombre de projets visibles et le filtre « favoris uniquement ». */
@Composable
fun ProjectFilterRow(
    visibleCount: Int,
    showOnlyFavorites: Boolean,
    onToggleFavoriteFilter: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = pluralStringResource(R.plurals.visible_projects, visibleCount, visibleCount),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.weight(1f)
        )
        FilterChip(
            selected = showOnlyFavorites,
            onClick = onToggleFavoriteFilter,
            label = { Text(text = stringResource(R.string.filter_favorites)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = null,
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProjectFilterRowPreview() {
    ProfilDeveloppeurTheme {
        ProjectFilterRow(
            visibleCount = 2,
            showOnlyFavorites = true,
            onToggleFavoriteFilter = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}
