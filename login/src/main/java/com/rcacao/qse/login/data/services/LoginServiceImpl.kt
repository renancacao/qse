package com.rcacao.qse.login.data.services

import com.google.firebase.auth.FirebaseAuth
import com.rcacao.qse.login.data.model.User
import javax.inject.Inject

class LoginServiceImpl @Inject constructor() : LoginService {
    override fun getUser(): User? {
        return FirebaseAuth.getInstance().currentUser?.let { User(it.providerId, it.phoneNumber) }
    }
}
