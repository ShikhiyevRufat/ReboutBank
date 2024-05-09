package com.example.rebolutbank.di

import com.example.rebolutbank.MySharedPrefecences
import com.example.rebolutbank.MySharedPreferencesInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferencesModule {

    @Binds
    @Singleton
    abstract fun bindPreferences(pref: MySharedPrefecences): MySharedPreferencesInterface
}