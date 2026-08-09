package com.ragnard.concoursprobf.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onStartQuiz: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "ConcoursPro BF",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Culture Générale • Psychotechnique",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onStartQuiz,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Démarrer un quiz")
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "20 questions aléatoires • Culture générale + Psychotechnique",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
