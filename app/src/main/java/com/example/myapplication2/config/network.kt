package com.example.myapplication2.config

import com.example.myapplication2.core.platform.notify.Message
import flick2know.fieldassist.core.functional.Either
import retrofit2.Call

/**
 * a class that facilitate http/https REST api requests
 * */
interface RestApiNetwork {
    /**
     * @purpose Make a REST Api call.
     *
     * @param call is a REST Api [Call] which returns [Input] type as response.
     * @param transform is a func,
     * which transform the [call] response of [Input] type to [Output] type.
     * @param default is a default response of [Input] type,
     * in case response is null but request [call] is successful.
     * @return a object of type [Either] ,[Message] or [Output]
     * @author vishal
     */
    fun <Input, Output> request(
        call: Call<Input>,
        transform: (Input) -> Output,
        default: Input
    ): Either<Message, Output>
}

interface NetworkHandler {
    fun isConnected(): Boolean
}
