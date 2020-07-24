package com.rcacao.qse.di

import com.rcacao.qse.login.utils.LoginHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
object HelpersModule {

    @Provides
    fun provideLoginHelper(): LoginHelper = LoginHelper()

}
