package com.example.radiofranceapp.data.repo

import com.apollographql.apollo3.ApolloClient
import com.example.BrandsQuery
import com.example.radiofranceapp.data.mappers.toSimpleBrand
import com.example.radiofranceapp.domain.repo.BrandRepository
import com.example.radiofranceapp.domain.model.SimpleBrand

class BrandRepositoryImpl(
    private val apolloClient: ApolloClient
): BrandRepository {
    override suspend fun getBrands(): List<SimpleBrand> {
        return apolloClient
            .query(BrandsQuery())
            .execute()
            .data
            ?.brands
            ?.map { it!!.toSimpleBrand() }
            ?: emptyList()
    }
}