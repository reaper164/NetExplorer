package com.reaper.netexplorer.presentation.routerscan

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reaper.domain.repository.RouterScanRepository
import com.reaper.netexplorer.model.ui.RsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class RouterScanViewModel @Inject constructor(
    private val repository: RouterScanRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RsUiState())
    val uiState: StateFlow<RsUiState> = _uiState.asStateFlow()

    private var fetchJob: Job? = null

    fun discoveryRouters(category: String) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            try {
                /*_uiState.update {
                    it.copy(isLoading = )
                }*/

                val newsItems = repository.scanRouters()

            } catch (ioe: IOException) {
                // Handle the error and notify the UI when appropriate.
                _uiState.update {
                    it.copy(message = ioe.message)
                }
            }
        }
    }


}