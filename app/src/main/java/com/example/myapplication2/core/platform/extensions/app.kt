package com.example.myapplication2.core.platform.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.View
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import timber.log.Timber
import java.lang.reflect.Type

val Context.networkInfo: NetworkInfo?
    get() = (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo


/***
 * gson conversations
 * */
fun gson(): Gson {
    return GsonBuilder().serializeNulls().create()
}

fun <T> T.toJson(): String = gson().toJson(this)

inline fun <reified T> String.fromJson(): T? = Gson().fromJson(this, T::class.java)

inline fun <reified T> String.fromTypeToken(typeToken: Type): T? =
    GsonBuilder().create().fromJson<T>(this, typeToken)


fun Bundle.log() {
    Timber.d(logString())
}

fun Bundle.logString(): String {
    var string = ""
    for (key in keySet())
        string = "\n\n $string key = $key \n\n  ${get(key)}\n\n"
    return string
}

// View visiblity gone
fun View.gone() {
    visibility = View.GONE
}

// View visiblity visible
fun View.visible() {
    visibility = View.VISIBLE
}

// View visiblity invisible
fun View.invisible() {
    visibility = View.INVISIBLE
}
