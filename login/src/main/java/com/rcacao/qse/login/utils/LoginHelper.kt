package com.rcacao.qse.login.utils

import android.content.Context
import android.content.Intent
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.rcacao.qse.R as AppR

class LoginHelper {

    fun getLoginIntent(): Intent {

        val providers: ArrayList<AuthUI.IdpConfig> = arrayListOf(
            //AuthUI.IdpConfig.GoogleBuilder().build(),
            //AuthUI.IdpConfig.FacebookBuilder().build(),
            AuthUI.IdpConfig.PhoneBuilder().build()
        )

        return AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setTheme(AppR.style.AppTheme)
            .build()
    }

    fun getLoginResponse(data: Intent?): IdpResponse? = IdpResponse.fromResultIntent(data)

    fun getUser(): FirebaseUser? = FirebaseAuth.getInstance().currentUser

    fun logout(context: Context) {
        AuthUI.getInstance().signOut(context)
    }

}
