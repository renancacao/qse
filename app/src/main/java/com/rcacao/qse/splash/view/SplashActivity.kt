package com.rcacao.qse.splash.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.rcacao.qse.R
import com.rcacao.qse.login.view.FullScreenActivity
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

}