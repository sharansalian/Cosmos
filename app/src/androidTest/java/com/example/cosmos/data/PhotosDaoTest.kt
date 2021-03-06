package com.example.cosmos.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.cosmos.data.local.PhotosDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PhotosDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var photosDao: PhotosDao
    private val photoA = Photo(1, "A", "", "", "", "", "")
    private val photoB = Photo(2, "B", "", "", "", "", "")
    private val photoC = Photo(3, "C", "", "", "", "", "")

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        photosDao = database.photoDao()

        photosDao.insertAll(listOf(photoB, photoC, photoA))

    }

    @After fun closeDb() {
        database.close()
    }

    @Test fun testGetPhotos() = runBlocking {
        val photos = photosDao.getPhotos()
        assertThat(photos.size, equalTo(3))

        assertThat(photos[0], equalTo(photoA))
        assertThat(photos[1], equalTo(photoB))
        assertThat(photos[2], equalTo(photoC))
    }

    @Test fun testGetPhoto() = runBlocking {
        assertThat(photosDao.getPhoto(photoA.id).first(), equalTo(photoA))
    }

}
