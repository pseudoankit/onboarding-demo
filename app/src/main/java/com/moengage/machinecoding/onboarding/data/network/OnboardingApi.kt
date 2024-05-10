package com.moengage.machinecoding.onboarding.data.network

import android.content.Context
import com.moengage.machinecoding.network.OnboardingNetworkManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class OnboardingApi(
    private val context: Context,
    private val ioDispatcher: CoroutineContext
) {

    suspend fun getOnboardingQuestions(): ByteArray? = withContext(ioDispatcher) {
        OnboardingNetworkManager.getOnboardingQuestions(context)
    }
}
