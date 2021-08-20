package com.wallstreet.airline.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wallstreet.airline.data.entities.University

@Dao
interface CharacterDao {

    @Query("SELECT * FROM university ORDER BY id DESC")
    fun getAllCharacters() : LiveData<List<University>>

    @Query("SELECT * FROM university WHERE id = :id")
    fun getCharacter(id: Int): LiveData<University>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(universities: List<University>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(university: List<University>)

    @Query("DELETE FROM university")
    suspend fun deleteAll()
}