package com.example.cosmos.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cosmos.data.Photo

@Dao
interface PhotosDao {

    @Query("SELECT * FROM photos ORDER BY id")
    fun observePhotos(): LiveData<List<Photo>>

    @Query("SELECT * FROM photos")
    suspend fun getPhotos(): List<Photo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(photos: List<Photo>)

    @Query("SELECT * FROM photos WHERE id = :id")
    fun getPhoto(id: String): Photo

    @Query("SELECT * FROM photos WHERE id = :id")
    fun observePhoto(id: String): LiveData<Photo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoto(photo: Photo)

    @Query("DELETE FROM photos")
    suspend fun clearPhotos()
}
