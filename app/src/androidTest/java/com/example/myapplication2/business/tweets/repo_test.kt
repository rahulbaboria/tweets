package com.example.myapplication2.business.tweets

import com.example.myapplication2.UnitTest
import com.example.myapplication2.config.NetworkHandler
import com.example.myapplication2.config.RestApiNetwork
import com.example.myapplication2.repo.tweets.Tweet
import com.example.myapplication2.repo.tweets.TweetsNetwork
import com.flick2know.fieldassist.repositories.call_preparation.network.TweetsService
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Response

class TweetsNetworkTest : UnitTest() {

    private lateinit var networkImp: TweetsNetwork.TweetsNetworkImp

    @MockK
    private lateinit var networkHandler: NetworkHandler

    @MockK
    private lateinit var restAPiNetwork: RestApiNetwork

    @MockK
    private lateinit var service: TweetsService

    @MockK private lateinit var tweetsCall : Call<List<Tweet>>

    @MockK private lateinit var tweetsResponse: Response<List<Tweet>>


    @Before
    fun setUp() {
        networkImp = TweetsNetwork.TweetsNetworkImp(restAPiNetwork, service)
    }

    @Test
    fun `should return empty list by default`() {
        every { networkHandler.isConnected() } returns true
        every { tweetsCall.execute() } returns tweetsResponse
        every { service.fetchTweets() } returns tweetsCall

        verify(exactly = 1) { service.fetchTweets() }
    }
}