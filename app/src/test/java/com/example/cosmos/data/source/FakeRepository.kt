package com.example.cosmos.data.source

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cosmos.data.Photo
import com.example.cosmos.data.PhotosRepository
import kotlinx.coroutines.runBlocking

class FakeRepository : PhotosRepository {

    var photosServiceData: LinkedHashMap<String, Photo> = LinkedHashMap()

    private val observablePhotos = MutableLiveData<List<Photo>>()

    override fun observePhotos(): LiveData<List<Photo>> {
        runBlocking { refreshPhotos() }
        return observablePhotos
    }

    private suspend fun refreshPhotos() {
        observablePhotos.value = getPhotos()
    }

    override suspend fun getPhotos(): List<Photo> {
        return photosServiceData.values.toList()
    }

    override suspend fun getPhoto(id: String) {
        TODO("Not yet implemented")
    }

    override fun observePhoto(photoId: String): LiveData<Photo> {
        TODO("Not yet implemented")
    }

    override fun insertPhoto(photo: Photo) {
        TODO("Not yet implemented")
    }

    override fun clearPhotos() {
        TODO("Not yet implemented")
    }


    @VisibleForTesting
    fun addPhotos(vararg photos: Photo) {
        for (task in photos) {
            photosServiceData[task.id.toString()] = task
        }
        runBlocking { refreshPhotos() }
    }
}