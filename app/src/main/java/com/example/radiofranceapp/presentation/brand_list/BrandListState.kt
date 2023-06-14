package com.example.radiofranceapp.presentation.brand_list

import com.example.radiofranceapp.domain.model.SimpleBrand

data class BrandListState(
    val isLoading: Boolean = false,
    val brands: List<SimpleBrand> = emptyList(),
    val error: String = ""

)