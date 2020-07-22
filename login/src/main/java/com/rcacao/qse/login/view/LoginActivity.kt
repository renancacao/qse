package com.rcacao.qse.login.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.firebase.ui.auth.IdpResponse
import com.google.android.material.snackbar.Snackbar
import com.rcacao.qse.login.R
import com.rcacao.qse.login.utils.LoginHelper
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : FullScreenActivity() {

    private val loginRequestCode: Int = 0
    private val loginHelper = LoginHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (loginHelper.getUser() != null) {
            onSuccessLogin()
        }

        btLogin.setOnClickListener { login() }
    }

    private fun login() {
        startActivityForResult(loginHelper.getLoginIntent(), loginRequestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == loginRequestCode) {
            val response = loginHelper.getLoginResponse(data)
            if (resultCode == Activity.RESULT_OK) {
                onSuccessLogin()
            } else {
                handleLoginError(response)
            }
        }
    }

    private fun handleLoginError(response: IdpResponse?) {
        if (response != null) {
            val message = response.error?.message ?: getString(R.string.login_generic_error)
            Snackbar.make(contextView, message, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun onSuccessLogin() {
        setResult(USER_LOGGED)
        finish()
    }

    companion object {
        const val USER_LOGGED: Int = 1
    }

}
