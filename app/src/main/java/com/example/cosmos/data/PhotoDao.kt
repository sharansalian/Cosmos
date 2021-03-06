package com.example.cosmos.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {

    @Query("SELECT * FROM photos ORDER BY id")
    fun observePhotos(): LiveData<List<Photo>>

    @Query("SELECT * FROM photos")
    suspend fun getPhotos(): List<Photo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(photos: List<Photo>)

    @Query("SELECT * FROM photos WHERE id = :id")
    fun getPhoto(id: Int): Flow<Photo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoto(photo: Photo)

    @Query("DELETE FROM photos")
    suspend fun clearPhotos()
}
