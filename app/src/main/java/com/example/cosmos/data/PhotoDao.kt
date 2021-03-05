package com.example.cosmos.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {

    @Query("SELECT * FROM photos ORDER BY id")
    fun getPhotos(): Flow<List<Photo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(photos: List<Photo>)

    @Query("SELECT * FROM photos WHERE id = :id")
    fun getPhoto(id: Int): Flow<Photo>
}