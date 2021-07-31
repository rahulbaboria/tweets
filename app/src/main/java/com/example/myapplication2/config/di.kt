package com.example.myapplication2.config

import com.example.myapplication2.MyApplication
import com.flick2know.fieldassist.config.network.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import flick2know.fieldassist.core.platform.view_model.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        /*app modules*/
        AndroidInjectionModule::class, AppModule::class,

        /*view model module*/
        ViewModelModule::class,

        /*activity handler modules*/
        ActivityModule::class,

        /*network*/
        NetworkModule::class,
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MyApplication): Builder

        fun build(): AppComponent
    }

    fun inject(application: MyApplication)
}