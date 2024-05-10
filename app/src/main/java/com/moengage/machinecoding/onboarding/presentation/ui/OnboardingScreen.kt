package com.moengage.machinecoding.onboarding.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import com.moengage.machinecoding.onboarding.domain.model.OnboardingQuestion
import com.moengage.machinecoding.onboarding.presentation.viewmodel.OnboardingViewModel

@Composable
internal fun OnboardingScreen() {
    val viewModel: OnboardingViewModel? = null        // init
    val state = viewModel!!.state

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        when (state) {
            is OnboardingViewModel.State.Error -> {}
            OnboardingViewModel.State.Loading -> {}
            is OnboardingViewModel.State.ShowQuestion -> {
                PlotQuestion(
                    question = state.question,
                    onAnswerChanged = viewModel::onAnswerChanged,
                    onNext = viewModel::onNext
                )
            }
        }
    }
}

@Composable
private fun PlotQuestion(
    question: OnboardingQuestion.Item,
    onAnswerChanged: (String) -> Unit,
    onNext: () -> Unit
) {
    Column {
        Text(text = question.text)
        TextField(
            value = question.answer,
            onValueChange = onAnswerChanged,
            keyboardOptions = KeyboardOptions(
                keyboardType = remember(question.keyboardType) {
                    when (question.keyboardType) {
                        OnboardingQuestion.Type.String -> KeyboardType.Text
                        OnboardingQuestion.Type.Integer -> KeyboardType.Number
                        OnboardingQuestion.Type.Email -> KeyboardType.Email
                    }
                }
            )
        )
        Button(onClick = onNext) {
            Text(text = question.nextBtnText)
        }
    }
}