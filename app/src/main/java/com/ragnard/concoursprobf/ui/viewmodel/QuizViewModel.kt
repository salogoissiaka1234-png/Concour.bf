package com.ragnard.concoursprobf.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ragnard.concoursprobf.data.AppDatabase
import com.ragnard.concoursprobf.data.Question
import com.ragnard.concoursprobf.data.QuestionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class QuizUiState(
    val questions: List<Question> = emptyList(),
    val currentIndex: Int = 0,
    val selectedAnswerIndex: Int? = null,
    val score: Int = 0,
    val isFinished: Boolean = false,
    val isLoading: Boolean = true
) {
    val currentQuestion: Question?
        get() = questions.getOrNull(currentIndex)

    val progress: Float
        get() = if (questions.isEmpty()) 0f else (currentIndex).toFloat() / questions.size
}

class QuizViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = QuestionRepository(
        AppDatabase.getInstance(application).questionDao()
    )

    private val _uiState = MutableStateFlow(QuizUiState())
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.seedIfEmpty()
        }
    }

    fun startQuiz(limit: Int = 10) {
        viewModelScope.launch {
            _uiState.value = QuizUiState(isLoading = true)
            val questions = repository.getRandomQuiz(limit)
            _uiState.value = QuizUiState(questions = questions, isLoading = false)
        }
    }

    fun selectAnswer(index: Int) {
        val state = _uiState.value
        if (state.selectedAnswerIndex != null) return

        val isCorrect = state.currentQuestion?.correctIndex == index
        _uiState.value = state.copy(
            selectedAnswerIndex = index,
            score = if (isCorrect) state.score + 1 else state.score
        )
    }

    fun nextQuestion() {
        val state = _uiState.value
        val nextIndex = state.currentIndex + 1
        if (nextIndex >= state.questions.size) {
            _uiState.value = state.copy(isFinished = true)
        } else {
            _uiState.value = state.copy(
                currentIndex = nextIndex,
                selectedAnswerIndex = null
            )
        }
    }
}
