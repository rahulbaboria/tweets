package com.example.myapplication2

import androidx.appcompat.app.AppCompatActivity
import kotlin.reflect.KClass

infix fun KClass<out AppCompatActivity>.shouldNavigateTo(nextActivity: KClass<out AppCompatActivity>): () -> Unit = {
    val originActivity = Robolectric.buildActivity(this.java).get()
    val shadowActivity = Shadows.shadowOf(originActivity)
    val nextIntent = shadowActivity.peekNextStartedActivity()

    nextIntent.component?.className shouldEqual nextActivity.java.canonicalName
}