package com.example.myapplication2.core.platform

import androidx.lifecycle.MutableLiveData
import com.example.myapplication2.AndroidTest
import com.example.myapplication2.core.platform.notify.Message
import flick2know.fieldassist.core.platform.view_model.BaseViewModel
import org.junit.Test

class BaseViewModelTest : AndroidTest() {

    @Test
    fun `should handle failure by updating live data`() {
        val viewModel = MyViewModel()

        viewModel.handleError(Message.NoInternetNet)

        val failure = viewModel.viewModelAppAlerts
        val error = viewModel.viewModelAppAlerts.value

        failure shouldBeInstanceOf MutableLiveData::class.java
        error shouldBeInstanceOf Message::class.java
    }

    private class MyViewModel : BaseViewModel() {
        fun handleError(failure: Message) = handleFailure(failure)
    }
}