package com.reaper.netexplorer.presentation.routerscan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reaper.domain.Resource
import com.reaper.domain.model.RouterscanParams
import com.reaper.domain.usecase.GetRoutersUseCase
import com.reaper.netexplorer.mappers.toRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class RouterScanViewModel @Inject constructor(
    private val getRoutersUseCase: GetRoutersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RouterscanUiState())
    val uiState: StateFlow<RouterscanUiState> = _uiState

    private var fetchJob: Job? = null

    fun discoveryRouters(routerscanParams: RouterscanParams) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch(Dispatchers.IO) {
            delay(200L)
            try {
                getRoutersUseCase(routerscanParams).onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _uiState.update { state ->
                                state.copy(
                                    routerList = result.data?.map { it.toRouter() } ?: emptyList(),
                                    isLoading = false,
                                    progress = 100
                                )
                            }
                        }
                        is Resource.Error -> {
                            _uiState.update { state ->
                                state.copy(
                                    isLoading = false,
                                    message = result.message,
                                    progress = null
                                )
                            }
                        }
                        is Resource.Loading -> {
                            _uiState.update { state ->
                                state.copy(
                                    routerList = result.data?.map { it.toRouter() } ?: emptyList(),
                                    isLoading = true
                                )
                            }
                        }
                        is Resource.Progress -> {
                            _uiState.update { state ->
                                state.copy(
                                    isLoading = true,
                                    progress = result.progress
                                )
                            }
                        }
                    }
                }.launchIn(this)

            } catch (ioe: IOException) {
                _uiState.update {
                    it.copy(message = ioe.message)
                }
            }
        }
    }


}