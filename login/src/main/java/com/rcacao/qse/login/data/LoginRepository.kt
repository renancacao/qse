package com.rcacao.qse.login.data

import com.google.firebase.auth.FirebaseAuth
import com.rcacao.qse.core.UserNotFoundException
import com.rcacao.qse.core.data.DataResult
import com.rcacao.qse.login.data.model.User

class LoginRepository {

    fun getUser(): DataResult<User> {
        FirebaseAuth.getInstance().currentUser?.apply {
            return DataResult.Success(User(this.providerId, this.phoneNumber))
        }
        return DataResult.Error(UserNotFoundException())
    }
}