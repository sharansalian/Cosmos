package com.example.cosmos.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.cosmos.data.Photo
import com.example.cosmos.data.PhotosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject internal constructor(photosRepository: PhotosRepository) : ViewModel() {

    val photos: LiveData<List<Photo>> = photosRepository.observePhotos()

}