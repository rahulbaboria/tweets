package com.example.myapplication2.core.platform.extensions

import android.os.Message
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/*life cycle*/
fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, Observer(body))

fun <L : LiveData<Message>> LifecycleOwner.failure(liveData: L, body: (Message?) -> Unit) =
    liveData.observe(this, Observer(body))