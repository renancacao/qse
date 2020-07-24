package com.rcacao.qse.login.data

import com.rcacao.qse.core.data.DataResult
import com.rcacao.qse.login.data.model.User

interface LoginRepository {
    fun getUser(): DataResult<User>
}