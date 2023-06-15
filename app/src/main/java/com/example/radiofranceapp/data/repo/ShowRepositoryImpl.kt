package com.example.radiofranceapp.data.repo

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.example.ShowsQuery
import com.example.radiofranceapp.data.mappers.toShows
import com.example.radiofranceapp.domain.model.Shows
import com.example.radiofranceapp.domain.repo.ShowRepository
import com.example.type.StationsEnum

class ShowRepositoryImpl(
    private val apolloClient: ApolloClient
): ShowRepository {

    override suspend fun getShows(
        station: String, limit: Optional<Int?>, after: Optional<String?>
    ): Shows {
        return apolloClient
            .query(ShowsQuery(StationsEnum.valueOf(station), limit, after))
            .execute()
            .data?.shows?.toShows() ?: Shows(emptyList())
    }
}