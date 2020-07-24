package com.rcacao.qse.di

import com.rcacao.qse.login.data.services.LoginService
import com.rcacao.qse.login.data.services.LoginServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
abstract class ServicesModules {

    @Binds
    abstract fun bindLoginService(loginService: LoginServiceImpl): LoginService
}
