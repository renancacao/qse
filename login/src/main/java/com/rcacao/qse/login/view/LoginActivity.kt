package com.rcacao.qse.login.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.IdpResponse
import com.google.android.material.snackbar.Snackbar
import com.rcacao.qse.login.R
import com.rcacao.qse.login.utils.LoginHelper
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val loginRequestCode: Int = 0
    private val loginHelper = LoginHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btLogin.setOnClickListener { login() }

    }

    fun login() {
        startActivityForResult(loginHelper.getLoginIntent(), loginRequestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == loginRequestCode) {
            val response = loginHelper.getLoginResponse(data)
            if (resultCode == Activity.RESULT_OK) {
                successLogin()
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

    private fun successLogin() {
        val user = loginHelper.getUser()
        Toast.makeText(this, "logado " + user.toString(), Toast.LENGTH_LONG).show()
    }
}
