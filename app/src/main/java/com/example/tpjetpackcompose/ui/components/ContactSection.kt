package com.example.tpjetpackcompose.ui.components

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.EventAvailable
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tpjetpackcompose.R
import com.example.tpjetpackcompose.data.ContactInfo
import com.example.tpjetpackcompose.ui.theme.ProfilDeveloppeurTheme

/**
 * Zone de contact : informations structurées en lecture seule
 * et actions utilisateur transmises par callbacks.
 */
@Composable
fun ContactSection(
    contact: ContactInfo,
    onContactClick: () -> Unit,
    onDownloadCvClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        // État local d'interface : simple affichage/masquage, inutile de le remonter au ViewModel.
        var showDetails by rememberSaveable { mutableStateOf(false) }

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TextButton(
                onClick = { showDetails = !showDetails },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = if (showDetails) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(
                        if (showDetails) R.string.contact_hide else R.string.contact_show
                    )
                )
            }

            AnimatedVisibility(visible = showDetails) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    ReadOnlyInfoField(label = stringResource(R.string.field_email), value = contact.email, icon = Icons.Filled.Email)
                    ReadOnlyInfoField(label = stringResource(R.string.field_location), value = contact.location, icon = Icons.Filled.LocationOn)
                    ReadOnlyInfoField(label = stringResource(R.string.field_availability), value = contact.availability, icon = Icons.Filled.EventAvailable)
                }
            }

            Row(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = onContactClick,
                    modifier = Modifier
                        .weight(1f)
                        .heightIn(min = 48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Send,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = stringResource(R.string.action_contact))
                }
                Spacer(modifier = Modifier.width(12.dp))
                OutlinedButton(
                    onClick = onDownloadCvClick,
                    modifier = Modifier
                        .weight(1f)
                        .heightIn(min = 48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Description,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = stringResource(R.string.action_download_cv))
                }
            }
        }
    }
}

/** OutlinedTextField en lecture seule pour afficher une information structurée. */
@Composable
fun ReadOnlyInfoField(
    label: String,
    value: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = {},
        readOnly = true,
        singleLine = true,
        label = { Text(text = label) },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        },
        shape = MaterialTheme.shapes.small,
        modifier = modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
private fun ContactSectionPreview() {
    ProfilDeveloppeurTheme {
        ContactSection(
            contact = ContactInfo(
                email = "thomas.camboulas@example.com",
                location = "France",
                availability = "Stage dès maintenant"
            ),
            onContactClick = {},
            onDownloadCvClick = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(
    name = "ContactSection - Thème sombre",
    showBackground = true,
    backgroundColor = 0xFF121318,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun ContactSectionDarkPreview() {
    ProfilDeveloppeurTheme(darkTheme = true) {
        ContactSection(
            contact = ContactInfo(
                email = "thomas.camboulas@example.com",
                location = "France",
                availability = "Stage dès maintenant"
            ),
            onContactClick = {},
            onDownloadCvClick = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}
