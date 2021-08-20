package com.wallstreet.airline.data.remote

import com.wallstreet.airline.data.entities.University
import retrofit2.Response
import retrofit2.http.*


interface UniversityService {

    @GET("search")
    suspend fun getAllCharacters(@Query("country") country: String) : Response<ArrayList<University>>

    @GET("employees/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Response<ArrayList<University>>
}