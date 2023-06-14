package com.example.radiofranceapp.domain.repo

import com.apollographql.apollo3.api.Optional
import com.example.radiofranceapp.domain.model.Shows

interface ShowClient {
    suspend fun getShows(
        station: String, limit: Optional<Int?>, after: Optional<String?>
    ): Shows

}