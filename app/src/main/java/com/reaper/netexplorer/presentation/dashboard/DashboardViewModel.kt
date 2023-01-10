package com.reaper.netexplorer.presentation.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reaper.domain.Download
import com.reaper.domain.Resource
import com.reaper.domain.usecase.DownloadChrootUseCase
import com.reaper.domain.model.ChrootParams
import com.reaper.netexplorer.mappers.toRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val downloadChrootUseCase: DownloadChrootUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState

    private var job: Job? = null

    fun downloadChroot(chrootParams: ChrootParams) {
        job?.cancel()
        job = viewModelScope.launch {
            delay(200L)
            try {
                downloadChrootUseCase(chrootParams).onEach { result ->
                    when (result) {
                        is Download.Progress -> {
                            _uiState.update { state ->
                                state.copy(
                                    isLoading = true,
                                    progress = result.percent
                                )
                            }
                        }
                        is Download.Finished -> {
                            _uiState.update { state ->
                                state.copy(
                                    message = "Successfully downloaded chroot",
                                    isLoading = false,
                                    progress = 100
                                )
                            }
                        }
                        is Download.Error -> {
                            _uiState.update { state ->
                                state.copy(
                                    isLoading = false,
                                    progress = 0,
                                    message = result.message
                                )
                            }
                            Log.e("DashBoardViewModel", "DownloadError: ${result.message}")
                        }
                    }
                }.launchIn(this)

            } catch (e: IOException) {
                _uiState.update { state ->
                    state.copy(
                        message = "Exception while downloading chroot",
                        isLoading = false
                    )
                }

            }
        }
    }

    fun messageShown() {
        _uiState.update { state ->
            state.copy(message = null)
        }
    }


}