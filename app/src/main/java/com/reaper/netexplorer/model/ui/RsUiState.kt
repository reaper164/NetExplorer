package com.reaper.netexplorer.model.ui

import com.reaper.netexplorer.model.Router

data class RsUiState(
    val isLoading : Boolean = false,
    val message : String? = null,
    val newsItems : List<Router>? = null
) {
}