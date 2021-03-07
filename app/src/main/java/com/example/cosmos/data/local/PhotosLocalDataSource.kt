package com.example.cosmos.data.local

import androidx.lifecycle.LiveData
import com.example.cosmos.data.Photo
import com.example.cosmos.data.PhotosDataSource

class PhotosLocalDataSource(
    private val photosDao: PhotosDao
) : PhotosDataSource {

    override fun observePhotos(): LiveData<List<Photo>> {
        return photosDao.observePhotos()
    }

    override suspend fun getPhotos(): List<Photo> {
        return photosDao.getPhotos()
    }

    override suspend fun getPhoto(photoId: String): Photo {
        return photosDao.getPhoto(photoId)
    }

    override fun observePhoto(photoId: String): LiveData<Photo> {
        return photosDao.observePhoto(photoId)
    }

    override suspend fun insertPhoto(photo: Photo) {
       return photosDao.insertPhoto(photo)
    }

    override suspend fun clearPhotos() {
        return photosDao.clearPhotos()
    }
}