package com.example.cosmos.data

import androidx.lifecycle.LiveData

/**
 * Main entry point for accessing photos data.
 */
interface PhotosDataSource {

    fun observePhotos(): LiveData<List<Photo>>

    suspend fun getPhotos(): List<Photo>

    suspend fun getPhoto(photoId: String): Photo

    fun observePhoto(photoId: String): LiveData<Photo>

    suspend fun insertPhoto(photo: Photo)

    suspend fun clearPhotos()


}
