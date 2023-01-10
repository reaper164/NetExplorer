package com.reaper.netexplorer.presentation.dashboard

data class DashboardUiState(
    val isLoading : Boolean = false,
    val message : String? = "",
    val progress : Int? = 0
)
