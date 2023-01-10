package com.reaper.netexplorer.presentation.routerscan

import com.reaper.netexplorer.model.Router

data class RouterscanUiState(
    val routerList : List<Router> = emptyList(),
    val isLoading : Boolean = false,
    val message : String? = "",
    val progress : Int? = 0
)
