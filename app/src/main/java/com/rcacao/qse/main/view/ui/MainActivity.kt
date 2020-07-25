package com.rcacao.qse.main.view.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavInflater
import androidx.navigation.fragment.NavHostFragment
import com.rcacao.qse.R
import com.rcacao.qse.main.view.MainUiEvent
import com.rcacao.qse.main.view.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var navGraph: NavGraph
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)
        initiateNavigationComponents()
        subscribe()
    }

    private fun initiateNavigationComponents() {
        val navHostFragment: NavHostFragment = nav_host_fragment as NavHostFragment
        val graphInflater: NavInflater = navHostFragment.navController.navInflater
        navGraph = graphInflater.inflate(R.navigation.nav_graph)
        navController = navHostFragment.navController
    }

    private fun subscribe() {
        viewModel.event.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let { handleEvent(it) }
        })
    }

    private fun handleEvent(mainUiEvent: MainUiEvent) {
        when (mainUiEvent) {
            is MainUiEvent.UserFound -> startMainFlow()
            MainUiEvent.UserNotFound -> navigateToLogin()
        }
    }

    private fun navigateToLogin() {
        navGraph.startDestination = R.id.loginFragment
        navController.graph = navGraph
    }

    private fun startMainFlow() {
        navGraph.startDestination = R.id.blankFragment
        navController.graph = navGraph
    }
}
