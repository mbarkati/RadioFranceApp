package com.example.radiofranceapp.domain.repo

import com.example.radiofranceapp.domain.model.SimpleBrand

interface BrandClient {
    suspend fun getBrands(): List<SimpleBrand>
}