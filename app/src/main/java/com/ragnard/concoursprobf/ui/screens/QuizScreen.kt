package com.ragnard.concoursprobf.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ragnard.concoursprobf.ui.viewmodel.QuizUiState

@Composable
fun QuizScreen(
    state: QuizUiState,
    onSelectAnswer: (Int) -> Unit,
    onNext: () -> Unit
) {
    val question = state.currentQuestion

    if (state.isLoading || question == null) {
        Box(Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {

        LinearProgressIndicator(
            progress = { state.progress },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Question ${state.currentIndex + 1} / ${state.questions.size}  •  Score: ${state.score}",
            style = MaterialTheme.typography.labelMedium
        )

        Spacer(Modifier.height(16.dp))
        Text(
            text = question.theme,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = question.statement,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(Modifier.height(24.dp))

        question.choices.forEachIndexed { index, choice ->
            val isSelected = state.selectedAnswerIndex == index
            val isCorrectChoice = index == question.correctIndex
            val hasAnswered = state.selectedAnswerIndex != null

            val containerColor = when {
                !hasAnswered -> MaterialTheme.colorScheme.surface
                isCorrectChoice -> Color(0xFFBEE7C4)
                isSelected && !isCorrectChoice -> Color(0xFFF3B7B7)
                else -> MaterialTheme.colorScheme.surface
            }

            Surface(
                onClick = { onSelectAnswer(index) },
                modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
                shape = RoundedCornerShape(12.dp),
                color = containerColor,
                tonalElevation = 2.dp
            ) {
                Text(
                    text = choice,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        if (state.selectedAnswerIndex != null && question.explanation != null) {
            Spacer(Modifier.height(12.dp))
            Text(
                text = question.explanation,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(Modifier.weight(1f))

        if (state.selectedAnswerIndex != null) {
            Button(
                onClick = onNext,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (state.currentIndex + 1 == state.questions.size) "Voir le résultat" else "Question suivante")
            }
        }
    }
}
