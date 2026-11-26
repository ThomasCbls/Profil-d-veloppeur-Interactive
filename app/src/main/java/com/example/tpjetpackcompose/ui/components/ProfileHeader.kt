package com.example.tpjetpackcompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tpjetpackcompose.R
import com.example.tpjetpackcompose.ui.theme.ProfilDeveloppeurTheme

/**
 * En-tête du profil : avatar avec badge superposé, nom, rôle et description,
 * le tout posé sur une Surface teintée aux couleurs du thème.
 */
@Composable
fun ProfileHeader(
    name: String,
    role: String,
    description: String,
    isAvailable: Boolean,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileAvatar(isAvailable = isAvailable)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = name,
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )
            Text(
                text = role,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            if (isAvailable) {
                Spacer(modifier = Modifier.height(12.dp))
                AvailabilityRow()
            }
        }
    }
}

/** Box : l'image de l'avatar avec un badge "disponible" superposé en bas à droite. */
@Composable
fun ProfileAvatar(
    isAvailable: Boolean,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.avatar_placeholder),
            contentDescription = stringResource(R.string.avatar_content_description),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(112.dp)
                .clip(CircleShape)
                .border(3.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
        if (isAvailable) {
            Surface(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(30.dp),
                shape = CircleShape,
                color = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary,
                shadowElevation = 2.dp
            ) {
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = stringResource(R.string.available_badge_description),
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}

@Composable
private fun AvailabilityRow(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Surface(
            modifier = Modifier.size(8.dp),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.secondary
        ) {}
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = stringResource(R.string.open_to_opportunities),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileHeaderPreview() {
    ProfilDeveloppeurTheme {
        ProfileHeader(
            name = "Thomas Camboulas",
            role = "Développeur Android junior",
            description = "Étudiant passionné par le développement mobile.",
            isAvailable = true,
            modifier = Modifier.padding(16.dp)
        )
    }
}
