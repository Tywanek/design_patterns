package com.radlab.wzorce.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radlab.wzorce.data.helper.JsonAssetsHelper
import com.radlab.wzorce.data.model.DesignPatternsData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class UiState {
    data object Loading : UiState()
    data class Success(val designPatterns: DesignPatternsData) : UiState()
    data object Error : UiState()
}

@HiltViewModel
class MainViewModel @Inject constructor(
    jsonAssetsHelper: JsonAssetsHelper<DesignPatternsData>
) : ViewModel(), JsonAssetsHelper<DesignPatternsData> by jsonAssetsHelper {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    fun loadPatterns() {
        viewModelScope.launch {
            _uiState.value =
                designPatterns().fold(
                    onSuccess = { UiState.Success(it) },
                    onFailure = { UiState.Error })
        }
    }
}
