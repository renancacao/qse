package com.rcacao.qse.login.domain

import com.rcacao.qse.core.data.DataResult
import com.rcacao.qse.login.data.LoginRepositoryImpl
import com.rcacao.qse.login.data.model.User

class GetUserUseCase(private val loginRepository: LoginRepositoryImpl) {
    fun invoke(): DataResult<User> = loginRepository.getUser()
}