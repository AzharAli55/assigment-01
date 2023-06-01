package com.asquare.assignment.home.di

import com.asquare.assignment.home.data.remote.CatApiInterface
import com.asquare.assignment.home.data.repository.CatsApiRepoImpl
import com.asquare.assignment.home.domain.remote.ICatApiRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HomeModule {

    @Provides
    @Singleton
    fun provideHomeApiRepo(apiInterface: CatApiInterface): ICatApiRepo {
        return CatsApiRepoImpl(apiInterface)
    }

    @Provides
    fun provideApiInterface(retrofit: Retrofit): CatApiInterface {
        return retrofit.create(CatApiInterface::class.java)
    }


}