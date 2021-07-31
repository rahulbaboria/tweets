package com.example.myapplication2.repo.tweets

import com.example.myapplication2.config.RestApiNetwork
import com.example.myapplication2.core.platform.notify.Message
import com.flick2know.fieldassist.repositories.call_preparation.network.TweetsService
import flick2know.fieldassist.core.functional.Either
import java.lang.Exception
import javax.inject.Inject

interface TweetsNetwork {
    fun fetchTweets(): Either<Message, List<Tweet>>

    class TweetsNetworkImp
    @Inject
    constructor(
        private val restApiNetwork: RestApiNetwork,
        private val service: TweetsService
    ) : TweetsNetwork {
        override fun fetchTweets(): Either<Message, List<Tweet>> {
            return try {
                restApiNetwork.request(
                   service.fetchTweets(),
                    { it },
                    ArrayList()
                )
            } catch (e: Exception) {
                Either.Left(Message.RestApiError(e.message ?: "Error fetching tweets",401))
            }
        }
    }
}