/*
 * Copyright 2020 GT_APP.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.myapplication2.config

import android.app.Activity
import android.app.AlarmManager
import android.app.DownloadManager
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.Resources
import android.location.LocationManager
import android.telephony.TelephonyManager
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication2.MainActivity
import com.example.myapplication2.MyApplication
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import flick2know.fieldassist.core.annotation.PerActivity
import flick2know.fieldassist.core.annotation.PerFragment
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesContext(
        app: MyApplication
    ): Context = app

    @Provides
    @Singleton
    fun providesPackageInfo(
        app: MyApplication
    ): PackageInfo = app.packageManager.getPackageInfo(
        app.packageName,
        PackageManager.GET_PERMISSIONS or PackageManager.GET_ACTIVITIES
    )

    @Provides
    @Singleton
    fun providesAlarmManager(
        app: MyApplication
    ): AlarmManager = app.getSystemService(Activity.ALARM_SERVICE) as AlarmManager

    @Provides
    @Singleton
    fun providesNotificationManager(
        app: MyApplication
    ): NotificationManager =
        app.getSystemService(Activity.NOTIFICATION_SERVICE) as NotificationManager

    @Provides
    @Singleton
    fun providesTelephonyManager(
        app: MyApplication
    ): TelephonyManager = app.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

    @Provides
    @Singleton
    fun providesLocationManager(
        app: MyApplication
    ): LocationManager = app.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    @Provides
    @Singleton
    fun providesDownloadManager(
        app: MyApplication
    ): DownloadManager = app.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

    @Provides
    @Singleton
    fun resources(
        application: MyApplication
    ): Resources = application.resources
}

@Module
abstract class ActivityModule {
    @PerActivity
    @ContributesAndroidInjector
    internal abstract fun bindsMainActivity(): MainActivity?
}



@GlideModule
class GlideModule : AppGlideModule() {
    override fun applyOptions(
        context: Context,
        builder: GlideBuilder
    ) { // Glide default Bitmap Format is set to RGB_565 since it
// consumed just 50% memory footprint compared to ARGB_8888.
// Increase memory usage for quality with:
        builder.setDefaultRequestOptions(RequestOptions().format(DecodeFormat.PREFER_ARGB_8888))
    }
}