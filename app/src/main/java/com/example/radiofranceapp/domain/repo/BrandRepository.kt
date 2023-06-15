package com.example.radiofranceapp.domain.repo

import com.example.radiofranceapp.domain.model.SimpleBrand

interface BrandRepository {
    suspend fun getBrands(): List<SimpleBrand>
}