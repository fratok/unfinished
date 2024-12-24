package com.example.myapplication2

import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.HasAndroidInjector

class Application: DaggerApplication(), HasAndroidInjector {
    private var appComponent : AppComponent? = null
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = buildComponent()
        return appComponent as AppComponent
    }

    private fun buildComponent(): AppComponent {
        val appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
        appComponent.inject(this)
        return appComponent
    }
    }
