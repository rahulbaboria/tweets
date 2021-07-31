package com.example.myapplication2.business_modules.tweets_list

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import flick2know.fieldassist.core.annotation.PerActivity
import flick2know.fieldassist.core.annotation.PerFragment
import flick2know.fieldassist.core.platform.view_model.ViewModelKey


@Module
abstract class TweetsModule {

    @Binds
    @IntoMap
    @ViewModelKey(TweetsViewModel::class)
    abstract fun bindsTweetsViewModel(
        tweetsViewModel: TweetsViewModel
    ): ViewModel

    @PerActivity
    @ContributesAndroidInjector
    internal abstract fun bindsTweetsListActivity(): TweetsListActivity?

}
