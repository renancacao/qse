package com.rcacao.qse.login.view.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.rcacao.qse.login.R
import com.rcacao.qse.login.utils.LoginHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val loginRequestCode: Int = 0

    @Inject
    lateinit var loginHelper: LoginHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_login, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btLogin.setOnClickListener { login() }
    }

    private fun login() {
        startActivityForResult(loginHelper.getAuthUiIntent(), loginRequestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == loginRequestCode) {
            if (resultCode == Activity.RESULT_OK) {
                onSuccessLogin()
            } else {
                showErrorMessage(loginHelper.getErrorMessage(data))
            }
        }
    }

    private fun showErrorMessage(errorMessage: String?) {
        val message = errorMessage ?: getString(R.string.login_generic_error)
        Snackbar.make(contextView, message, Snackbar.LENGTH_LONG).show()
    }

    private fun onSuccessLogin() {
        TODO()
    }


}
