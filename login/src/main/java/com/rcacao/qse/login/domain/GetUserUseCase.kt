package com.rcacao.qse.login.domain

import com.rcacao.qse.core.data.DataResult
import com.rcacao.qse.login.data.repositories.LoginRepository
import com.rcacao.qse.login.data.model.User
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val loginRepository: LoginRepository) {
    operator fun invoke(): DataResult<User> = loginRepository.getUser()
}