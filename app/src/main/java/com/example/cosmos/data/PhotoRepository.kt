package com.example.cosmos.data

import com.example.cosmos.data.local.PhotosDao
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotoRepository @Inject constructor(private val photosDao: PhotosDao) {

    fun getPhotos() = photosDao.observePhotos()

    fun getPhoto(id: Int) = photosDao.getPhoto(id)

    fun insertPhoto(photo: Photo) = runBlocking {
        photosDao.insertPhoto(photo)
    }

    fun clearPhotos() = runBlocking {
        photosDao.clearPhotos()
    }
}
