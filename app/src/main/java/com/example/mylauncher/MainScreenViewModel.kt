package com.example.mylauncher

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mylauncher.data.AppsRepository
import com.example.mylauncher.model.App
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UiState(
    val apps: List<App> = emptyList(),
)

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val appsRepository: AppsRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> get() = _uiState

    init {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(apps = appsRepository.getAllApps())
        }
    }
}