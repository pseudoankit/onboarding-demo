package com.moengage.machinecoding.onboarding.domain.model


data class OnboardingQuestion(
    val questions: List<Item>
) {

    data class Item(
        val text: String,
        val keyboardType: Type,
        val nextBtnText: String = "",
        val answer: String = ""
    )

    enum class Type {
        String, Integer, Email
    }
}

