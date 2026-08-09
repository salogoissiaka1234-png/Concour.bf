package com.ragnard.concoursprobf.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ragnard.concoursprobf.ui.screens.HomeScreen
import com.ragnard.concoursprobf.ui.screens.QuizScreen
import com.ragnard.concoursprobf.ui.screens.ResultScreen
import com.ragnard.concoursprobf.ui.viewmodel.QuizViewModel

private object Routes {
    const val HOME = "home"
    const val QUIZ = "quiz"
    const val RESULT = "result"
}

@Composable
fun AppNavGraph() {
    val navController: NavHostController = rememberNavController()
    val quizViewModel: QuizViewModel = viewModel()

    NavHost(navController = navController, startDestination = Routes.HOME) {

        composable(Routes.HOME) {
            HomeScreen(
                onStartQuiz = {
                    quizViewModel.startQuiz(limit = 10)
                    navController.navigate(Routes.QUIZ)
                }
            )
        }

        composable(Routes.QUIZ) {
            val state by quizViewModel.uiState.collectAsState()

            if (state.isFinished) {
                navController.navigate(Routes.RESULT) {
                    popUpTo(Routes.HOME)
                }
            } else {
                QuizScreen(
                    state = state,
                    onSelectAnswer = { quizViewModel.selectAnswer(it) },
                    onNext = { quizViewModel.nextQuestion() }
                )
            }
        }

        composable(Routes.RESULT) {
            val state by quizViewModel.uiState.collectAsState()
            ResultScreen(
                score = state.score,
                total = state.questions.size,
                onRestart = {
                    quizViewModel.startQuiz(limit = 10)
                    navController.navigate(Routes.QUIZ) {
                        popUpTo(Routes.HOME)
                    }
                }
            )
        }
    }
}
