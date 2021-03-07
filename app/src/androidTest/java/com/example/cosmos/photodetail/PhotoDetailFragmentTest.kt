package com.example.cosmos.photodetail

import androidx.room.Room
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.cosmos.R
import com.example.cosmos.data.AppDatabase
import com.example.cosmos.data.DefaultPhotosRepository
import com.example.cosmos.utilities.launchFragmentInHiltContainer
import com.example.cosmos.utilities.testPhoto
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

/**
 * Integration test for the Task Details screen.
 */
@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
@HiltAndroidTest
class PhotoDetailFragmentTest {

    private lateinit var appDatabase: AppDatabase

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var photoRepository: DefaultPhotosRepository

    @Before
    fun initRepository() {
        hiltRule.inject()

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }


    @Test
    fun activeTaskDetails_DisplayedInUi() {
        // GIVEN - Add active (incomplete) task to the DB
        photoRepository.insertPhoto(testPhoto)

        // WHEN - Details fragment launched to display task
        val bundle = PhotoDetailFragmentArgs(testPhoto.id.toLong()).toBundle()
        launchFragmentInHiltContainer<PhotoDetailFragment>(bundle, R.style.ThemeOverlay_AppCompat)

        // THEN - Task details are displayed on the screen
        // make sure that the title/description are both shown and correct
        onView(withId(R.id.photo_title)).check(matches(isDisplayed()))
        onView(withId(R.id.photo_title)).check(matches(withText("Active Task")))
        onView(withId(R.id.photo_explanation)).check(matches(isDisplayed()))

    }


}