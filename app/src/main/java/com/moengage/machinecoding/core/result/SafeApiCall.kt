package com.moengage.machinecoding.core.result

import com.moengage.machinecoding.core.NoDataFoundException
import java.io.IOException

inline fun <T> safeApiCall(
    execute: () -> Result<T>
): Result<T> {
    return try {
        execute()
    } catch (e: NoDataFoundException) {
        // use string res
        Result.Error("No Data Found")
    } catch (e: IOException) {
        Result.Error("No Internet Connection")
    } catch (e: Exception) {
        Result.Error("Something went wrong")
    }
}