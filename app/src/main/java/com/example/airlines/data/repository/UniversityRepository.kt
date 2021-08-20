package com.wallstreet.airline.data.repository

import com.wallstreet.airline.data.local.CharacterDao
import com.wallstreet.airline.data.remote.AppRemoteDataSource
import com.wallstreet.airline.utils.performGetOperation
import javax.inject.Inject

class UniversityRepository @Inject constructor(
    private val remoteDataSource: AppRemoteDataSource,
    private val localDataSource: CharacterDao
) {

    fun getCharacter(id: Int) = performGetOperation(
        databaseQuery = { localDataSource.getCharacter(id) },
        networkCall = { remoteDataSource.getUniversity(id) },
        saveCallResult = { localDataSource.insert(it) }
    )

    fun getCharacters(country: String) = performGetOperation(
        databaseQuery = { localDataSource.getAllCharacters() },
        networkCall = { remoteDataSource.getUniversities(country) },
        saveCallResult = { localDataSource.insertAll(it) }
    )
}