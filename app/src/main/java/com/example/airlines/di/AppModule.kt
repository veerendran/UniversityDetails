package com.wallstreet.airline.di

import android.content.Context
import com.wallstreet.airline.data.local.AppDatabase
import com.wallstreet.airline.data.local.CharacterDao
import com.wallstreet.airline.data.remote.AppRemoteDataSource
import com.wallstreet.airline.data.remote.UniversityService
import com.wallstreet.airline.data.repository.UniversityRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("http://universities.hipolabs.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideCharacterService(retrofit: Retrofit): UniversityService = retrofit.create(UniversityService::class.java)

    @Singleton
    @Provides
    fun provideCharacterRemoteDataSource(universityService: UniversityService) = AppRemoteDataSource(universityService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: AppDatabase) = db.characterDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: AppRemoteDataSource,
                          localDataSource: CharacterDao) =
        UniversityRepository(remoteDataSource, localDataSource)
}