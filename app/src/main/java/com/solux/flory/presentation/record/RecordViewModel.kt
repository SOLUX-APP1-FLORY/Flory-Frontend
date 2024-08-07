package com.solux.flory.presentation.record

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solux.flory.domain.entity.ProfileUserEntity
import com.solux.flory.domain.usecase.GetProfileUseCase
import com.solux.flory.domain.usecase.PostDiaryUseCase
import com.solux.flory.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val postDiaryUseCase: PostDiaryUseCase,
    private val getProfileUseCase: GetProfileUseCase
) : ViewModel() {
    private val _postDiaryState = MutableStateFlow<UiState<String>>(UiState.Empty)
    val postDiaryState: StateFlow<UiState<String>> = _postDiaryState

    private val _getProfileState = MutableStateFlow<UiState<ProfileUserEntity>>(UiState.Empty)
    val getProfileState: StateFlow<UiState<ProfileUserEntity>> = _getProfileState

    private val _isFlowerSelected = MutableLiveData<Boolean>()
    val isFlowerSelected: LiveData<Boolean> = _isFlowerSelected

    init {
        getProfile()
    }

    private fun getProfile() = viewModelScope.launch {
        _getProfileState.emit(UiState.Loading)
        getProfileUseCase().fold(
            {
                _getProfileState.emit(UiState.Success(it))
            },
            { _getProfileState.emit(UiState.Failure(it.message.toString())) }
        )
    }

    fun postDiary(flowerName: String, title: String, content: String) = viewModelScope.launch {
        _postDiaryState.emit(UiState.Loading)
        postDiaryUseCase(flowerName, title, content).fold(
            {
                if (it != null) _postDiaryState.emit(UiState.Success(it)) else _postDiaryState.emit(
                    UiState.Failure("400")
                )
            },
            { _postDiaryState.emit(UiState.Failure(it.message.toString())) }
        )
    }

    fun setFlowerSelected(isSelected: Boolean) {
        _isFlowerSelected.value = isSelected
    }
}