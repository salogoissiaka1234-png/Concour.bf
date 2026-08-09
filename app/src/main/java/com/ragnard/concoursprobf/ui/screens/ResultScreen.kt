package com.ragnard.concoursprobf.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ResultScreen(
    score: Int,
    total: Int,
    onRestart: () -> Unit
) {
    val percentage = if (total == 0) 0 else (score * 100 / total)
    val message = when {
        percentage >= 80 -> "Excellent ! Tu es prêt(e) pour le concours 💪"
        percentage >= 50 -> "Bien joué, continue à réviser les points faibles."
        else -> "Ne lâche rien, la répétition est la clé. Recommence !"
    }

    Box(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "$score / $total",
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "$percentage% de bonnes réponses",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Spacer(Modifier.height(32.dp))
            Button(onClick = onRestart, modifier = Modifier.fillMaxWidth()) {
                Text("Refaire un quiz")
            }
        }
    }
}
