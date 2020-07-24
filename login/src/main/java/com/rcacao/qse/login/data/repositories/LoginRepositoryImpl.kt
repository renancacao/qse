package com.rcacao.qse.login.data.repositories

import com.rcacao.qse.core.UserNotFoundException
import com.rcacao.qse.core.data.DataResult
import com.rcacao.qse.login.data.services.LoginService
import com.rcacao.qse.login.data.model.User
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val loginService: LoginService) :
    LoginRepository {

    override fun getUser(): DataResult<User> {
        val currentUser = loginService.getUser()
        return if (currentUser != null) {
            DataResult.Success(currentUser)
        } else {
            DataResult.Error(UserNotFoundException())
        }
    }
}