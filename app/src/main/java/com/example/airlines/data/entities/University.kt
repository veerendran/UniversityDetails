package com.wallstreet.airline.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson

@Entity(tableName = "university")
data class University(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val country: String,
    val name: String,
    val domains: List<String>,
    val web_pages: List<String>
)

class Converters {

    @TypeConverter
    fun listToJson(value: List<String>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()
}