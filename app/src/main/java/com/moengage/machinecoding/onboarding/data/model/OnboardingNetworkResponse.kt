package com.moengage.machinecoding.onboarding.data.model

import com.google.gson.Gson

data class OnboardingNetworkResponse(
    val questions: List<Item>
) {

    data class Item(
        val text: String,
        val type: String
    )
}
