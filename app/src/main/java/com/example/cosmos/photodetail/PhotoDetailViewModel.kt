package com.example.cosmos.photodetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.cosmos.data.PhotoRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class PhotoDetailViewModel @AssistedInject constructor(
    photoRepository: PhotoRepository,
    @Assisted private val id: String
) : ViewModel() {

    val photo = photoRepository.getPhoto(id.toInt()).asLiveData()

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
