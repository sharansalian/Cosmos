package com.example.cosmos.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.cosmos.data.Photo
import com.example.cosmos.data.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject internal constructor(photoRepository: PhotoRepository) : ViewModel() {

    val photos: LiveData<List<Photo>> = photoRepository.getPhotos().asLiveData()

}