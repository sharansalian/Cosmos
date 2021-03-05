package com.example.cosmos.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotoRepository @Inject constructor(private val photoDao: PhotoDao) {

    fun getPhotos() = photoDao.getPhotos()

    fun getPhoto(id: Int) = photoDao.getPhoto(id)

}
