package com.example.cosmos.gallery

import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.cosmos.CosmosActivity
import com.example.cosmos.R
import com.example.cosmos.data.AppDatabase
import com.example.cosmos.data.PhotoRepository
import com.example.cosmos.utilities.testPhoto
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class GalleryFragmentTest {

    private lateinit var appDatabase: AppDatabase

    @Inject
    lateinit var photoRepository: PhotoRepository


    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltRule.inject()

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun testSample() {
        // GIVEN - One photo already in the repository
        photoRepository.insertPhoto(testPhoto)

        // WHEN - On startup
        launchActivity()

        // THEN - Verify task is displayed on screen
        Espresso.onView(ViewMatchers.withText("potato chips"))
            .check(matches(ViewMatchers.isDisplayed()))
    }


    @Test
    fun testEmptyCosmos() {
        // GIVEN - One photo already in the repository
        photoRepository.clearPhotos()

        // WHEN - On startup
        launchActivity()

        // THEN - Verify task is displayed on screen
        Espresso.onView(ViewMatchers.withText("Your universe is empty"))
            .check(matches(ViewMatchers.isDisplayed()))
    }

    private fun launchActivity(): ActivityScenario<CosmosActivity>? {
        val activityScenario = launch(CosmosActivity::class.java)
        activityScenario.onActivity { activity ->
            // Disable animations in RecyclerView
            (activity.findViewById(R.id.photo_list) as RecyclerView).itemAnimator = null
        }
        return activityScenario
    }
}
