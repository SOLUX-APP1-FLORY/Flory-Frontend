package com.solux.flory.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solux.flory.domain.usecase.GetCheckLoginUseCase
import com.solux.flory.domain.usecase.GetUserAccessTokenUseCase
import com.solux.flory.domain.usecase.PostLoginUseCase
import com.solux.flory.domain.usecase.SaveCheckLoginUseCase
import com.solux.flory.domain.usecase.SaveUserAccessTokenUseCase
import com.solux.flory.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val postLoginUseCase: PostLoginUseCase,
    private val saveUserAccessTokenUseCase: SaveUserAccessTokenUseCase,
    private val getUserAccessTokenUseCase: GetUserAccessTokenUseCase,
    private val getCheckLoginUseCase: GetCheckLoginUseCase,
    private val saveCheckLoginUseCase: SaveCheckLoginUseCase
) : ViewModel() {
    private val _postLoginState = MutableStateFlow<UiState<String>>(UiState.Empty)
    val postLoginState: StateFlow<UiState<String>> = _postLoginState

    fun postLogin(uid: String, password: String) = viewModelScope.launch {
        _postLoginState.emit(UiState.Loading)
        postLoginUseCase(uid, password).fold(
            {
                if (it != null) _postLoginState.emit(UiState.Success(it)) else _postLoginState.emit(
                    UiState.Failure("400")
                )
            },
            { _postLoginState.emit(UiState.Failure(it.message.toString())) }
        )
    }

    fun getUserAccessToken() = getUserAccessTokenUseCase()

    fun saveUserAccessToken(accessToken: String) {
        viewModelScope.launch {
            saveUserAccessTokenUseCase(accessToken)
        }
    }

    fun getCheckLogin() = getCheckLoginUseCase()

    fun saveCheckLogin(checkLogin: Boolean) {
        viewModelScope.launch {
            saveCheckLoginUseCase(checkLogin)
        }
    }
}