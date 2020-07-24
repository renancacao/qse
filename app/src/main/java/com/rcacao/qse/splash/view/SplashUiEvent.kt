package com.rcacao.qse.splash.view

sealed class SplashUiEvent {
    object UserFound : SplashUiEvent()
    object UserNotFound : SplashUiEvent()
}
