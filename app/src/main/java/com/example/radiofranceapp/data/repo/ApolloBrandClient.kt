package com.example.radiofranceapp.data.repo

import com.apollographql.apollo3.ApolloClient
import com.example.BrandsQuery
import com.example.radiofranceapp.data.mappers.toSimpBrand
import com.example.radiofranceapp.domain.repo.BrandClient
import com.example.radiofranceapp.domain.model.SimpleBrand

class ApolloBrandClient(
    private val apolloClient: ApolloClient
): BrandClient {
    override suspend fun getBrands(): List<SimpleBrand> {
        return apolloClient
            .query(BrandsQuery())
            .execute()
            .data
            ?.brands
            ?.map { it!!.toSimpBrand() }
            ?: emptyList()
    }
}