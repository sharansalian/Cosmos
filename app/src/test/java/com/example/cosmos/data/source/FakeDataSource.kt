package com.example.cosmos.data.source

import androidx.lifecycle.LiveData
import com.example.cosmos.data.Photo
import com.example.cosmos.data.PhotosDataSource

class FakeDataSource(var photos: MutableList<Photo>? = mutableListOf()) : PhotosDataSource {

    override fun observePhotos(): LiveData<List<Photo>> {
        TODO("Not yet implemented")
    }

    override suspend fun getPhotos(): List<Photo> {
        photos?.let { return (ArrayList(it)) }
        return ArrayList()
    }

    override suspend fun getPhoto(photoId: String): Photo {
        TODO("Not yet implemented")
    }

    override fun observePhoto(photoId: String): LiveData<Photo> {
        TODO("Not yet implemented")
    }

    override suspend fun insertPhoto(photo: Photo) {
        photo.let { photos?.add(photo) }
    }

    override suspend fun clearPhotos() {
        photos?.clear()
    }

}