package com.rcacao.qse.splash.view.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rcacao.qse.core.data.DataResult
import com.rcacao.qse.core.data.Event
import com.rcacao.qse.login.domain.GetUserUseCase
import com.rcacao.qse.splash.view.SplashUiEvent
import kotlinx.coroutines.launch
import javax.inject.Inject

open class SplashViewModel @ViewModelInject @Inject constructor(private val getUserUseCase: GetUserUseCase) :
    ViewModel() {

    private val mutableEvent = MutableLiveData<Event<SplashUiEvent>>()
    val event: LiveData<Event<SplashUiEvent>>
        get() = mutableEvent

    fun isLogged() {
        viewModelScope.launch {
            when (getUserUseCase()) {
                is DataResult.Success -> mutableEvent.value = Event(SplashUiEvent.UserFound)
                is DataResult.Error -> mutableEvent.value = Event(SplashUiEvent.UserNotFound)
            }
        }
    }
}