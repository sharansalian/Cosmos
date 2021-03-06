package com.example.cosmos.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.cosmos.data.Photo
import com.example.cosmos.data.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject internal constructor(photoRepository: PhotoRepository) : ViewModel() {

    val photos: LiveData<List<Photo>> = photoRepository.getPhotos().asLiveData()

}