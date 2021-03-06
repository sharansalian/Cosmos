package com.example.cosmos.data

import com.example.cosmos.data.local.PhotosDao
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotoRepository @Inject constructor(
    private val photosDao: PhotosDao,
    private val photosLocalDataSource: PhotosDataSource
) {

    fun getPhotos() = photosLocalDataSource.observePhotos()

    fun getPhoto(id: Int) = photosDao.getPhoto(id)

    fun insertPhoto(photo: Photo) = runBlocking {
        photosDao.insertPhoto(photo)
    }

    fun clearPhotos() = runBlocking {
        photosDao.clearPhotos()
    }
}
