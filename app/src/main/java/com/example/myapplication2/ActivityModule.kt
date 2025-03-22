package com.example.myapplication2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ActivityModule {
//    @ContributesAndroidInjector
//    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeTestFragment(): TestFragment

    @ContributesAndroidInjector
    abstract fun contributeItemsFragment(): ItemsFragment

    @ContributesAndroidInjector
    abstract fun contributeItemFragment(): ItemFragment


    @ContributesAndroidInjector
    abstract fun contributeAuthFragment(): AuthFragment

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(TestViewModel::class)
    abstract fun bindsTestViewModule(viewModel: TestViewModel): ViewModel
}