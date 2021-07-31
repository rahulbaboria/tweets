package com.example.myapplication2


import android.app.Application
import com.example.myapplication2.config.AppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import javax.inject.Inject

open class MyApplication : Application(), HasAndroidInjector {
    companion object {

    }
/*

    private val appComponent: AppComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }
*/

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>



    override fun onCreate() {
        super.onCreate()
        //this.injectMembers()
        //Initialize NetworkLiveData to get internet connectivity
        //InternetCallbacks.init(this)

    }


    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    //private fun injectMembers() = appComponent.inject(this)

    private fun initializeLeakDetection() {
        //if (BuildConfig.DEBUG) LeakCanary().config()
    }
}

