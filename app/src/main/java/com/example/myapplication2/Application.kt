package com.example.myapplication2

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App : DaggerApplication(), HasAndroidInjector {

    private var appComponent: AppComponent? = null

    @Inject
    lateinit var hasAndroidInjector: DispatchingAndroidInjector<Any>
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()

        appComponent?.inject(this)
        return appComponent as AppComponent
    }
}