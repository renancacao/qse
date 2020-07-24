package com.rcacao.qse.splash.view.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.rcacao.qse.R
import com.rcacao.qse.core.view.FullScreenActivity
import com.rcacao.qse.login.utils.LoginHelper
import com.rcacao.qse.login.view.ui.LoginActivity
import com.rcacao.qse.splash.view.SplashUiEvent
import com.rcacao.qse.splash.view.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject

const val DELAY_TIME: Long = 2000

@AndroidEntryPoint
class SplashActivity : FullScreenActivity() {

    @Inject
    lateinit var loginHelper: LoginHelper

    private val viewModel: SplashViewModel by viewModels()
    private val loginRequestCode: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        subscribe()
        waitAndInit()
    }

    private fun subscribe() {
        viewModel.event.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let { handleEvent(it) }
        })
    }

    private fun handleEvent(splashUiEvent: SplashUiEvent) {
        when (splashUiEvent) {
            is SplashUiEvent.UserFound -> TODO()
            SplashUiEvent.UserNotFound -> openLoginActivity()
        }
    }

    private fun openLoginActivity() {
        loginHelper.openLoginActivity(this, splashTextTitle, loginRequestCode)
    }

    private fun waitAndInit() {
        Handler().postDelayed({
            viewModel.isLogged()
        }, DELAY_TIME)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == loginRequestCode) {
            if (resultCode == LoginActivity.USER_LOGGED) {
                onSuccessLogin()
            } else {
                finish()
            }
        }
    }

    private fun onSuccessLogin() {
        Toast.makeText(this, "Logou", Toast.LENGTH_LONG).show()
    }

}
