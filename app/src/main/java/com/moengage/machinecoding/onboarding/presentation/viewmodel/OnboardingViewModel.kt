package com.moengage.machinecoding.onboarding.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moengage.machinecoding.core.result.Result
import com.moengage.machinecoding.onboarding.domain.model.OnboardingQuestion
import com.moengage.machinecoding.onboarding.domain.usecase.OnboardingQuestionUseCase
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val onboardingQuestionUseCase: OnboardingQuestionUseCase
) : ViewModel() {

    private lateinit var allOnboardingQuestion: MutableList<OnboardingQuestion.Item>
    private var activeIndex: Int = 0

    var state: State by mutableStateOf(State.Loading)
        private set

    init {
        fetchQuestions()
    }

    private fun fetchQuestions() {
        viewModelScope.launch {
            when (val questionResult = onboardingQuestionUseCase()) {
                is Result.Error -> {
                    state = State.Error(
                        message = questionResult.message
                    )
                }

                is Result.Success -> {
                    allOnboardingQuestion = questionResult.data.questions.toMutableList()
                    activeIndex = 0
                    state = State.ShowQuestion(
                        allOnboardingQuestion[activeIndex]
                    )

                }
            }

        }
    }

    fun onAnswerChanged(value: String) {
        if (state is State.ShowQuestion) {
            val item = (state as State.ShowQuestion)
            state = item.copy(
                question = item.question.copy(
                    answer = value
                )
            )
        }

    }

    fun onNext() {
        if (state is State.ShowQuestion) {
            val item = state as State.ShowQuestion
            allOnboardingQuestion[activeIndex] = item.question
            state = item.copy(
                question = allOnboardingQuestion[++activeIndex]
            )
        }
    }

    sealed interface State {
        data class ShowQuestion(val question: OnboardingQuestion.Item) : State
        data class Error(val message: String) : State
        object Loading : State
    }
}