package com.example.myapplication2

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AuthModule {

    @Provides
    @Singleton
    fun provideDbHelper(context: Context): DbHelper {
        return DbHelper(
            context = context,
            factory = null)
    }
}