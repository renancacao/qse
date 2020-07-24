package com.rcacao.qse.login.data.services

import com.rcacao.qse.login.data.model.User

interface LoginService {
    fun getUser(): User?
}
