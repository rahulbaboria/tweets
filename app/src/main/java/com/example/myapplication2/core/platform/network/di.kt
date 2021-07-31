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

import com.example.myapplication2.config.NetworkHandler
import com.example.myapplication2.config.RestApiNetwork
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import javax.inject.Singleton


@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit
            .Builder()
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(
               Api.base_url
            )
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideRestApiNetwork(
        restApiNetwork: RestApiNetworkImp
    ): RestApiNetwork = restApiNetwork

    @Provides
    @Singleton
    fun provideNetworkHandler(
        handler: NetworkHandlerImp
    ): NetworkHandler = handler

}

/***
 * handles empty body response.
 * */
internal class NullOnEmptyConverterFactory : Converter.Factory() {
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        val delegate: Converter<ResponseBody, *> =
            retrofit.nextResponseBodyConverter<Any>(this, type, annotations)
        return Converter<ResponseBody, Any> { body ->
            if (body.contentLength() == 0L) null else delegate.convert(body)
        }
    }
}
