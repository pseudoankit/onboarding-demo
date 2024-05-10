package com.moengage.machinecoding.core.result


sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>

    data class Error<T>(val message: String) : Result<T>
}
