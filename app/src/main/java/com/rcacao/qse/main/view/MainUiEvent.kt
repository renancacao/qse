package com.rcacao.qse.main.view

sealed class MainUiEvent {
    object UserFound : MainUiEvent()
    object UserNotFound : MainUiEvent()
}
