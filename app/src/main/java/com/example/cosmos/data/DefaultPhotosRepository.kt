package com.example.cosmos.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DefaultPhotosRepository @Inject constructor(
    private val photosLocalDataSource: PhotosDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PhotosRepository {

    override fun observePhotos() = photosLocalDataSource.observePhotos()

    override suspend fun getPhotos() = coroutineScope {
        return@coroutineScope photosLocalDataSource.getPhotos()
    }

    override suspend fun getPhoto(id: String) {
        withContext(ioDispatcher) {
            photosLocalDataSource.getPhoto(id)
        }
    }

    override fun observePhoto(photoId: String): LiveData<Photo> =
        photosLocalDataSource.observePhoto(photoId)

    override fun insertPhoto(photo: Photo) = runBlocking {
        photosLocalDataSource.insertPhoto(photo)
    }

    override fun clearPhotos() = runBlocking {
        photosLocalDataSource.clearPhotos()
    }
}
