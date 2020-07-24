package com.rcacao.qse.login.view.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.firebase.ui.auth.IdpResponse
import com.google.android.material.snackbar.Snackbar
import com.rcacao.qse.core.view.FullScreenActivity
import com.rcacao.qse.login.R
import com.rcacao.qse.login.utils.LoginHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : FullScreenActivity() {

    private val loginRequestCode: Int = 0

    @Inject
    lateinit var loginHelper: LoginHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btLogin.setOnClickListener { login() }
    }

    private fun login() {
        startActivityForResult(loginHelper.getAuthUiIntent(), loginRequestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == loginRequestCode) {
            val response = loginHelper.getAuthUiResponse(data)
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

    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED)
        finish()
    }

    companion object {
        const val USER_LOGGED: Int = 1
    }

}
