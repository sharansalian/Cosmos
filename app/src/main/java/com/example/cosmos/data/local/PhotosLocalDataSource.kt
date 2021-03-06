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
}