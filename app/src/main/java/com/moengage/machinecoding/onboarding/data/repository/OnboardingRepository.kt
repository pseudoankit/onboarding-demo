package com.moengage.machinecoding.onboarding.data.repository

import com.google.gson.Gson
import com.moengage.machinecoding.core.NoDataFoundException
import com.moengage.machinecoding.network.OnboardingNetworkManager
import com.moengage.machinecoding.onboarding.data.mapper.mapToDomain
import com.moengage.machinecoding.onboarding.data.model.OnboardingNetworkResponse
import com.moengage.machinecoding.onboarding.data.network.OnboardingApi
import com.moengage.machinecoding.onboarding.domain.model.OnboardingQuestion

class OnboardingRepository(
    private val onboardingApi: OnboardingApi
) {

    suspend fun getQuestions(): OnboardingQuestion {
        val response = onboardingApi.getOnboardingQuestions() ?: throw NoDataFoundException
        val responseModel =
            Gson().fromJson(response.toString(), OnboardingNetworkResponse::class.java)

        return responseModel.mapToDomain()
    }
}