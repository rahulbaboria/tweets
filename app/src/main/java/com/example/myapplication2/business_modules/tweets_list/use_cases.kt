package com.example.myapplication2.business_modules.tweets_list

import com.example.myapplication2.core.platform.notify.Message
import com.example.myapplication2.repo.tweets.TweetsNetwork
import flick2know.fieldassist.core.functional.Either
import flick2know.fieldassist.core.interactor.UseCase
import javax.inject.Inject


class FetchTweets
@Inject
constructor(
    private val tweetsNetwork: TweetsNetwork,
) : UseCase<UseCase.None, List<TweetDisplay>>() {

    override suspend fun run(params: UseCase.None): Either<Message, List<TweetDisplay>> {
        return tweetsNetwork.fetchTweets()
            .fold({ it }) {
                it.map { it.display() }
            }
    }
}