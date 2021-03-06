package com.example.cosmos.di

import android.content.Context
import com.example.cosmos.data.AppDatabase
import com.example.cosmos.data.PhotoRepository
import com.example.cosmos.data.local.PhotosDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun providePhotoDao(appDatabase: AppDatabase): PhotosDao {
        return appDatabase.photoDao()
    }
}


@Module
@InstallIn(SingletonComponent::class)
object PhotoRepositoryModule {

    @Singleton
    @Provides
    fun providePhotosRepository(
        photosDao: PhotosDao
    ): PhotoRepository {
        return PhotoRepository(
            photosDao
        )
    }
}