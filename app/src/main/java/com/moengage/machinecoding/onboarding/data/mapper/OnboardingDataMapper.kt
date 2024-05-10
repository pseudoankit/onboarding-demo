package com.moengage.machinecoding.onboarding.data.mapper

import com.moengage.machinecoding.onboarding.data.model.OnboardingNetworkResponse
import com.moengage.machinecoding.onboarding.domain.model.OnboardingQuestion

internal fun OnboardingNetworkResponse.mapToDomain(): OnboardingQuestion {
    return OnboardingQuestion(
        questions = questions.map {
            OnboardingQuestion.Item(
                text = it.text,
                keyboardType = when (it.type) {
                    "integer" -> OnboardingQuestion.Type.Integer
                    "email" -> OnboardingQuestion.Type.Email
                    else -> OnboardingQuestion.Type.String
                }
            )
        }
    )
}