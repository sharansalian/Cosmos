package com.example.cosmos.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photos")
data class Photo(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val date: String,
    val explanation: String,
    val hdurl: String,
    val title: String,
    val url: String,
    val copyright: String? = ""
)