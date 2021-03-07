package com.example.cosmos.photodetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cosmos.data.Photo
import com.example.cosmos.data.PhotosRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class PhotoDetailViewModel @AssistedInject constructor(
    photoRepository: PhotosRepository,
    @Assisted private val photoId: String
) : ViewModel() {

    private val _photo = photoRepository.observePhoto(photoId)

    val photo: LiveData<Photo> = _photo

    companion object {
        fun provideFactory(
            assistedFactory: PhotoDetailViewModelFactory,
            id: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(id) as T
            }
        }
    }
}

@AssistedFactory
interface PhotoDetailViewModelFactory {
    fun create(id: String): PhotoDetailViewModel
}
