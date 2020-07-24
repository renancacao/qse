package com.rcacao.qse.splash.view.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rcacao.qse.core.data.Event
import com.rcacao.qse.login.domain.GetUserUseCase
import com.rcacao.qse.splash.view.SplashUiEvent

class SplashViewModel @ViewModelInject constructor(private val getUserUseCase: GetUserUseCase) :
    ViewModel() {

    private val mutableEvent =  MutableLiveData<Event<SplashUiEvent>>()
    val event: LiveData<Event<SplashUiEvent>>
        get() = mutableEvent


    fun hasUser() {
        TODO("Not yet implemented")
    }
}