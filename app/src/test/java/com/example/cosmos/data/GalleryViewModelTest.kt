package com.example.cosmos.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cosmos.data.source.FakeRepository
import com.example.cosmos.gallery.GalleryViewModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GalleryViewModelTest {

    // Subject under test
    private lateinit var viewModel: GalleryViewModel

    // Use a fake repository to be injected into the viewmodel
    lateinit var photosRepository: FakeRepository

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        photosRepository = FakeRepository()
        photosRepository.addPhotos(testPhotos[0], testPhotos[1])
        viewModel = GalleryViewModel(photosRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun loadAllPhotosFromRepository() {
        // Pause dispatcher so we can verify initial values
        mainCoroutineRule.pauseDispatcher()

        // Given an initialized GalleryViewModel with initialized photos
        // Observe the items to keep LiveData emitting
        viewModel.photos.observeForTesting {

            // Execute pending coroutines actions
            mainCoroutineRule.resumeDispatcher()

            // And data correctly loaded
            assertThat(viewModel.photos.getOrAwaitValue()[0].title).isEqualTo("A")
            assertThat(viewModel.photos.getOrAwaitValue()[1].title).isEqualTo("B")
        }
    }
}