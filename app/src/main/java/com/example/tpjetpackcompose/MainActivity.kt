package com.example.tpjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.tpjetpackcompose.data.sampleProfile
import com.example.tpjetpackcompose.ui.ProfileScreen
import com.example.tpjetpackcompose.ui.theme.ProfilDeveloppeurTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfilDeveloppeurTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProfileScreen(
                        profile = sampleProfile(),
                        onContactClick = {
                            // Pas de vraie action : ouvrirait une app mail plus tard
                        },
                        onDownloadCvClick = {
                            // Pas de vraie action : téléchargerait le CV plus tard
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
