/*
 * Copyright 2020 GT_APP.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.flick2know.fieldassist.config.network

import android.content.SharedPreferences
import com.example.myapplication2.MyApplication
import com.example.myapplication2.config.NetworkHandler
import com.example.myapplication2.config.RestApiNetwork
import com.example.myapplication2.core.platform.extensions.networkInfo
import com.example.myapplication2.core.platform.notify.Message
import flick2know.fieldassist.core.functional.Either
import retrofit2.Call
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

class RestApiNetworkImp
@Inject
constructor(
    private val networkHandler: NetworkHandler
) : RestApiNetwork {
    override fun <Input, Output> request(
        call: Call<Input>,
        transform: (Input) -> Output,
        default: Input
    ): Either<Message, Output> {
        return when (networkHandler.isConnected()) {
            true -> try {
                val response = call.execute()
                val errorBody = response.errorBody()?.string() ?: "Bad request"
                when (response.code()) {
                    401,
                    201,
                    400,
                    402,
                    404 -> Either.Left(Message.RestApiError(errorBody, response.code()))

                    204 -> Either.Right(transform((default)))
                    200 -> Either.Right(transform((response.body() ?: default)))

                    else -> Either.Left(Message.RestApiError(errorBody, response.code()))
                }
            } catch (exception: Throwable) {
                Timber.e(exception.message, exception.stackTrace.toString())
                Either.Left(Message.RestApiError(exception.message ?: "", code = -1))
            }

            false -> Either.Left(Message.NoInternetNet)
        }
    }
}

@Singleton
class NetworkHandlerImp
@Inject
constructor(
    private val myApplication: MyApplication
) : NetworkHandler {
    override fun isConnected() = myApplication.networkInfo?.isConnected ?: false
}



