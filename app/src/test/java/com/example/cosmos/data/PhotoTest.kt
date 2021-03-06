package com.example.cosmos.data

import org.junit.Assert.assertEquals
import org.junit.Test

class PhotoTest {

    @Test
    fun test_default_values() {
        val defaultPhoto = Photo(1, "", "", "", "", "")
        assertEquals("", defaultPhoto.copyright)
    }
}
