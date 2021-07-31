package com.example.myapplication2.core.platform.extensions

import android.os.Bundle
import android.text.InputType
import android.text.method.DigitsKeyListener
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import timber.log.Timber
import java.lang.reflect.Type

/*Exhaustive*/
val <T> T.exhaustive: T
    get() = this

/***
 * view model
 * */
inline fun <reified T : ViewModel> viewModelInstance(
    activity: FragmentActivity,
    factory: ViewModelProvider.Factory,
    body: T.() -> Unit
): T {
    val vm = ViewModelProvider(activity, factory)[T::class.java]
    vm.body()
    return vm
}

inline fun <T : ViewModel> viewModelInstance(
    activity: FragmentActivity,
    factory: ViewModelProvider.Factory,
    clazz: Class<T>,
    body: T.() -> Unit
): T {
    val vm = ViewModelProvider(activity, factory)[clazz]
    vm.body()
    return vm
}

inline fun <reified T : ViewModel> observers(vm: T, body: T.() -> Unit) {
    vm.body()
}

