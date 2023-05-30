package com.asquare.assignment.home.di

import com.asquare.assignment.home.api.client.CatApiInterface
import com.asquare.assignment.home.api.client.CatApiService
import com.asquare.assignment.home.api.client.ICatApi
import com.asquare.assignment.home.api.repo.CatsApiRepoImpl
import com.theentertainerme.productsbl.api.repo.ICatApiRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HomeModule {
    @Singleton
    @Provides
    fun provideHomeApi(apiInterface: CatApiInterface): ICatApi {
        return CatApiService(apiInterface)
    }

    @Provides
    @Singleton
    fun provideHomeApiRepo(api: ICatApi): ICatApiRepo {
        return CatsApiRepoImpl(api)
    }

    @Provides
    fun provideApiInterface(retrofit: Retrofit): CatApiInterface {
        return retrofit.create(CatApiInterface::class.java)
    }


}