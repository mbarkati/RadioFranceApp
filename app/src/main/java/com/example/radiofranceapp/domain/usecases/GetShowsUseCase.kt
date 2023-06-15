package com.example.radiofranceapp.domain.usecases

import com.apollographql.apollo3.api.Optional
import com.example.radiofranceapp.common.Resource
import com.example.radiofranceapp.domain.model.Shows
import com.example.radiofranceapp.domain.repo.ShowRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetShowsUseCase @Inject constructor(
    private val showRepository: ShowRepository
) {

    operator fun invoke(
        station: String,
        limit: Optional<Int?>,
        after: Optional<String?>
    ): Flow<Resource<Shows>> = flow {
        try {
            emit(Resource.Loading())
            val shows = showRepository.getShows(station, limit, after)
            emit(Resource.Success(shows))
        } catch (e: Exception){
            emit(Resource.Error(e.message ?: "Remote error"))
        }
    }
}