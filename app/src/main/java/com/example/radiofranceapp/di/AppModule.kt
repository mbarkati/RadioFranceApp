package com.example.radiofranceapp.di

import com.apollographql.apollo3.ApolloClient
import com.example.radiofranceapp.common.Constants.BASE_URL
import com.example.radiofranceapp.common.Constants.X_TOKEN
import com.example.radiofranceapp.data.repo.BrandRepositoryImpl
import com.example.radiofranceapp.data.repo.ShowRepositoryImpl
import com.example.radiofranceapp.domain.repo.BrandRepository
import com.example.radiofranceapp.domain.repo.ShowRepository
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
            .serverUrl(BASE_URL+"graphql?x-token="+X_TOKEN)
            .build()
    }

    @Provides
    @Singleton
    fun provideBrandClient(apolloClient: ApolloClient): BrandRepository {
        return BrandRepositoryImpl(apolloClient)
    }

    @Provides
    @Singleton
    fun provideGetBrandsUseCase(brandRepository: BrandRepository): GetBrandsUseCase {
        return GetBrandsUseCase(brandRepository)
    }

    @Provides
    @Singleton
    fun provideShowsClient(apolloClient: ApolloClient): ShowRepository {
        return ShowRepositoryImpl(apolloClient)
    }

    @Provides
    @Singleton
    fun provideGetShowsUseCase(showRepository: ShowRepository): GetShowsUseCase {
        return GetShowsUseCase(showRepository)
    }


}