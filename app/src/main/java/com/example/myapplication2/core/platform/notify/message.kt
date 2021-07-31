package com.example.myapplication2.core.platform.notify

sealed class Message(
    val msg: String,
    val retry: (() -> Unit)? = null
) {
    //net
    object NoInternetNet : Message(Messages.NO_INTERNET)
    object NetConnected : Message(Messages.INTERNET_CONNECTED)

    class RestApiError(msg: String, code : Int) : Message(msg = msg)

}