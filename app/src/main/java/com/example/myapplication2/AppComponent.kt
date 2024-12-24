package com.example.myapplication2

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Component(modules = [TestModule])
interface AppComponent: AndroidInjector<DaggerApplication> {
    fun inject(application: Application)
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application): Builder
        fun builder(): AppComponent

    }
}
