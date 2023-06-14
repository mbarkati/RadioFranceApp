package com.example.radiofranceapp.presentation.show_list

import com.example.radiofranceapp.domain.model.Shows
import com.example.radiofranceapp.domain.model.SimpleBrand

data class ShowListState(
    val isLoading: Boolean = false,
    val shows: Shows? = null,
    val error: String = ""
)