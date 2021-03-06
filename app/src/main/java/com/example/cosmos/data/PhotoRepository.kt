package com.example.cosmos.data

import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotoRepository @Inject constructor(private val photoDao: PhotoDao) {

    fun getPhotos() = photoDao.observePhotos()

    fun getPhoto(id: Int) = photoDao.getPhoto(id)

    fun insertPhoto(photo: Photo) = runBlocking {
        photoDao.insertPhoto(photo)
    }

    fun clearPhotos() = runBlocking {
        photoDao.clearPhotos()
    }
}
