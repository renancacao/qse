package com.rcacao.qse.splash.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.rcacao.qse.R
import com.rcacao.qse.core.view.FullScreenActivity
import com.rcacao.qse.login.view.LoginActivity

class SplashActivity : FullScreenActivity() {

    private val loginRequestCode: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        callLoginActivity()
    }

    private fun callLoginActivity() {
        Handler().postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivityForResult(intent, loginRequestCode)
        }, 2000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == loginRequestCode && resultCode == LoginActivity.USER_LOGGED) {
            onSuccessLogin()
        }
        finish()
    }

    private fun onSuccessLogin() {
        Toast.makeText(this, "Logou", Toast.LENGTH_LONG).show()
    }

}