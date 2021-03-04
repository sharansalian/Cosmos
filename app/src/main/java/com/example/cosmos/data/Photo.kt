package com.example.cosmos.data

import androidx.room.Entity

@Entity
data class Photo(
    val copyright: String,
    val date: String,
    val explanation: String,
    val hdurl: String,
    val title: String,
    val url: String
)