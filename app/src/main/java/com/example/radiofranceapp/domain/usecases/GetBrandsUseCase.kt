package com.example.radiofranceapp.domain.usecases

import com.example.radiofranceapp.common.Resource
import com.example.radiofranceapp.domain.repo.BrandClient
import com.example.radiofranceapp.domain.model.SimpleBrand
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBrandsUseCase @Inject constructor(
    private val brandClient: BrandClient
) {

    operator fun invoke(): Flow<Resource<List<SimpleBrand>>> = flow {
        try {
            emit(Resource.Loading())
            val brands = brandClient.getBrands()
            emit(Resource.Success(brands))
        } catch (e: Exception){
            emit(Resource.Error(e.message ?: "Remote error"))
        }
    }
}