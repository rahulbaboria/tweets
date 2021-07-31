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
package flick2know.fieldassist.core.interactor

import com.example.myapplication2.core.platform.notify.Message
import flick2know.fieldassist.core.functional.Either
import kotlinx.coroutines.*

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This abstraction represents an execution unit for different use cases (this means than any use
 * case in the application should implement this contract).
 *
 * By convention each [UseCase] implementation will execute its job in a background thread
 * (kotlin coroutine) and will post the result in the respective thread.
 */
abstract class UseCase<in Params, out Type> where Type : Any {

    abstract suspend fun run(params: Params): Either<Message, Type>

    operator fun invoke(
        params: Params,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        onResult: (Either<Message, Type>) -> Any = {}
    ) {
        GlobalScope.launch(dispatcher) {
            onResult(run(params))
        }
    }

    suspend operator fun invoke(
        params: Params,
        tag: String,// differentiate from another function above
        onResult: (Either<Message, Type>) -> Any = {}
    ) {
        onResult(run(params))
    }

    suspend operator fun <T> invoke(
        params: Params,
        onResult: (Either<Message, Type>) -> T
    ): T {
        return onResult(run(params))
    }

    operator fun invoke(
        params: Params,
        scope: CoroutineScope,
        onResult: (Either<Message, Type>) -> Any = {}
    ) {
        scope.launch { onResult(run(params)) }
    }


    /**
     * @purpose when no parameter is required to run a use case,
    provide this object as a run parameter*/
    object None
}



