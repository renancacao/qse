package com.rcacao.qse.main.view.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rcacao.qse.core.data.DataResult
import com.rcacao.qse.core.data.Event
import com.rcacao.qse.login.domain.GetUserUseCase
import com.rcacao.qse.main.view.MainUiEvent
import kotlinx.coroutines.launch
import javax.inject.Inject

open class MainViewModel @ViewModelInject @Inject constructor(private val getUserUseCase: GetUserUseCase) :
    ViewModel() {

    private val mutableEvent = MutableLiveData<Event<MainUiEvent>>()
    val event: LiveData<Event<MainUiEvent>>
        get() = mutableEvent

    init {
        isLogged()
    }

    private fun isLogged() {
        viewModelScope.launch {
            when (getUserUseCase()) {
                is DataResult.Success -> mutableEvent.value = Event(MainUiEvent.UserFound)
                is DataResult.Error -> mutableEvent.value = Event(MainUiEvent.UserNotFound)
            }
        }
    }
}
