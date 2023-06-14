package com.example.radiofranceapp.presentation.show_list

import com.example.radiofranceapp.domain.model.Shows

data class ShowListState(
    val isLoading: Boolean = false,
    val shows: Shows? = null,
    val error: String? = null
)