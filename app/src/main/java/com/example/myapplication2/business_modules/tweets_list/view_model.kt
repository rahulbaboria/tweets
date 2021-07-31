package com.example.myapplication2.business_modules.tweets_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import flick2know.fieldassist.core.interactor.UseCase
import flick2know.fieldassist.core.platform.view_model.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TweetsViewModel
@Inject
constructor(
    val fetchTweets: FetchTweets
) : BaseViewModel() {

    val tweets = MutableLiveData<List<TweetDisplay>>()

    fun fetchTweets() {
        ioScope.launch {
            fetchTweets(UseCase.None, this) {
                it.fold({
                    ::handleFailure
                }) {
                    viewModelScope.launch(Dispatchers.Main) {
                        tweets.postValue(it)
                    }
                }
            }
        }
    }

}