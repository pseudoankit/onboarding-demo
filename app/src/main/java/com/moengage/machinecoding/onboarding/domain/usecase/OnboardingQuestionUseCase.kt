package com.moengage.machinecoding.onboarding.domain.usecase

import com.moengage.machinecoding.core.NoDataFoundException
import com.moengage.machinecoding.core.result.Result
import com.moengage.machinecoding.core.result.safeApiCall
import com.moengage.machinecoding.onboarding.data.repository.OnboardingRepository
import com.moengage.machinecoding.onboarding.domain.model.OnboardingQuestion
import java.io.IOException

class OnboardingQuestionUseCase(
    private val onboardingRepository: OnboardingRepository
) {

    suspend operator fun invoke(): Result<OnboardingQuestion> {
        return safeApiCall {
            val questions = onboardingRepository.getQuestions().questions
            if (questions.isEmpty()) {
                return@safeApiCall Result.Error("No Data Found")
            }

            val updatedQuestions = questions.mapIndexed { idx, item ->
                item.copy(
                    nextBtnText = if (idx == questions.lastIndex) "Finish" else "Next"
                )
            }


            return@safeApiCall Result.Success(OnboardingQuestion(updatedQuestions))
        }
    }
}
