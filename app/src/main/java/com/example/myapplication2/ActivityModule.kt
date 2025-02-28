package com.example.myapplication2

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeTestFragment(): TestFragment

    @ContributesAndroidInjector
    abstract fun contributeItemsFragment2(): ItemsFragment2

    @ContributesAndroidInjector
    abstract fun contributeAuthFragment(): AuthFragment


    @Binds
    abstract fun bindsTestViewModule(viewModel: TestViewModel)  : ViewModel
}