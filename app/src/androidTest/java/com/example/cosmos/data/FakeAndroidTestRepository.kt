package com.example.cosmos.data

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.runBlocking

class FakeAndroidTestRepository : PhotosRepository {

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
        runBlocking { refreshPhotos() }

        // Figuring out the flow for why we loose state of the list I think it is because of the lifecycle
        return liveData { Photo(0, "ooooo", "zxzc", "Active Task", "Active Title", "uuuuu") }
    }

    override fun insertPhoto(photo: Photo) {
        photosServiceData[photo.id.toString()] = photo
    }

    override fun clearPhotos() {
        photosServiceData.clear()
    }

    @VisibleForTesting
    fun addPhotos(vararg photos: Photo) {
        for (task in photos) {
            photosServiceData[task.id.toString()] = task
        }
        runBlocking { refreshPhotos() }
    }
}