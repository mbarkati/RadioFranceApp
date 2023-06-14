package com.example.radiofranceapp.di

import com.apollographql.apollo3.ApolloClient
import com.example.radiofranceapp.data.repo.ApolloBrandClient
import com.example.radiofranceapp.data.repo.ApolloShowClient
import com.example.radiofranceapp.domain.repo.BrandClient
import com.example.radiofranceapp.domain.repo.ShowClient
import com.example.radiofranceapp.domain.usecases.GetBrandsUseCase
import com.example.radiofranceapp.domain.usecases.GetShowsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://openapi.radiofrance.fr/v1/graphql?x-token=e59800c2-7e6d-4426-98b4-8a389c189569")
            .build()
    }

    @Provides
    @Singleton
    fun provideBrandClient(apolloClient: ApolloClient): BrandClient {
        return ApolloBrandClient(apolloClient)
    }

    @Provides
    @Singleton
    fun provideGetBrandsUseCase(brandClient: BrandClient): GetBrandsUseCase {
        return GetBrandsUseCase(brandClient)
    }

    @Provides
    @Singleton
    fun provideShowsClient(apolloClient: ApolloClient): ShowClient {
        return ApolloShowClient(apolloClient)
    }

    @Provides
    @Singleton
    fun provideGetShowsUseCase(showClient: ShowClient): GetShowsUseCase {
        return GetShowsUseCase(showClient)
    }


}