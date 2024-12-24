package com.example.myapplication2

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TestModule {
    @ContributesAndroidInjector
    abstract fun bindTestFragment(): TestFragment
    @Binds
    abstract fun bindTestViewModel(viewModel: TestViewModel): ViewModel
}