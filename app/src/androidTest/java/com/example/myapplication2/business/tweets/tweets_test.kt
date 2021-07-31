package com.example.myapplication2

import com.example.myapplication2.business_modules.tweets_list.FetchTweets
import com.example.myapplication2.repo.tweets.Tweet
import com.example.myapplication2.repo.tweets.TweetsNetwork
import flick2know.fieldassist.core.functional.Either
import flick2know.fieldassist.core.interactor.UseCase
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class TweetsUseCaseTest : UnitTest() {

    private lateinit var fetchTweets : FetchTweets

    @MockK
    private lateinit var tweetsNetwork : TweetsNetwork

    @Before
    fun setUp() {
        fetchTweets = FetchTweets(tweetsNetwork)

        every { tweetsNetwork.fetchTweets() } returns Either.Right(listOf(Tweet.empty()))
    }

    @Test
    fun `should get data from network`() {
        runBlocking {
            fetchTweets.run(UseCase.None)
        }
        verify(exactly = 1) { tweetsNetwork.fetchTweets() }
    }
}