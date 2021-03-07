package com.example.cosmos.data

import androidx.lifecycle.LiveData

interface PhotosRepository {
    fun observePhotos(): LiveData<List<Photo>>

    suspend fun getPhotos(): List<Photo>

    suspend fun getPhoto(id: String)

    fun observePhoto(photoId: String): LiveData<Photo>

    fun insertPhoto(photo: Photo)

    fun clearPhotos()

}