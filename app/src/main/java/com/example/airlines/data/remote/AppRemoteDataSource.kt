package com.wallstreet.airline.data.remote

import javax.inject.Inject

class AppRemoteDataSource @Inject constructor(
    private val universityService: UniversityService
): BaseDataSource() {

    suspend fun getUniversities(country : String) = getResult { universityService.getAllCharacters(country) }
    suspend fun getUniversity(id: Int) = getResult { universityService.getCharacter(id) }
}