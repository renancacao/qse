package com.rcacao.qse.login.utils

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.view.View
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.rcacao.qse.login.R
import com.rcacao.qse.login.view.ui.LoginActivity
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

    fun openLoginActivity(activity: Activity, loginTextTitle: View, requestCode: Int) {
        val options: ActivityOptions =
            ActivityOptions.makeSceneTransitionAnimation(
                activity,
                loginTextTitle,
                activity.getString(R.string.login_transition_name)
            )
        val intent = Intent(activity, LoginActivity::class.java)
        activity.startActivityForResult(intent, requestCode, options.toBundle())
    }

    fun getAuthUiResponse(data: Intent?): IdpResponse? = IdpResponse.fromResultIntent(data)

    fun logout(context: Context) {
        AuthUI.getInstance().signOut(context)
    }
}
