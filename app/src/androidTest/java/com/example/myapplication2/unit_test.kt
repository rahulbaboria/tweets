package com.example.myapplication2

import org.junit.Rule

abstract class UnitTest {

    @Suppress("LeakingThis")
    @Rule
    @JvmField val injectMocks = InjectMocksRule.create(this@UnitTest)

    fun fail(message: String): Nothing = throw AssertionError(message)
}