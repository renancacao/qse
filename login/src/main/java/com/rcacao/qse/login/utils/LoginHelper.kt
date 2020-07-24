package com.rcacao.qse.login.utils

import android.content.Context
import android.content.Intent
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.rcacao.qse.login.R
import javax.inject.Inject

class LoginHelper @Inject constructor() {

    fun getAuthUiIntent(): Intent {

        val providers: ArrayList<AuthUI.IdpConfig> = arrayListOf(
            //AuthUI.IdpConfig.GoogleBuilder().build(),
            //AuthUI.IdpConfig.FacebookBuilder().build(),
            AuthUI.IdpConfig.PhoneBuilder().build()
        )

        return AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setTheme(R.style.AppTheme)
            .build()
    }

    fun getAuthUiResponse(data: Intent?): IdpResponse? = IdpResponse.fromResultIntent(data)

    fun logout(context: Context) {
        AuthUI.getInstance().signOut(context)
    }
}
