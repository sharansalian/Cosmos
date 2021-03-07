package com.example.cosmos.di

import android.content.Context
import com.example.cosmos.data.AppDatabase
import com.example.cosmos.data.DefaultPhotosRepository
import com.example.cosmos.data.PhotosDataSource
import com.example.cosmos.data.PhotosRepository
import com.example.cosmos.data.local.PhotosDao
import com.example.cosmos.data.local.PhotosLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
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

    @Singleton
    @Provides
    fun providePhotosLocalDataSource(
        database: AppDatabase
    ): PhotosLocalDataSource {
        return PhotosLocalDataSource(
            database.photoDao()
        )
    }

    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO


    @Singleton
    @Provides
    fun providesPhotoDataSource(
        photosDao: PhotosDao
    ): PhotosDataSource {
        return PhotosLocalDataSource(
            photosDao
        )
    }
}


@Module
@InstallIn(ApplicationComponent::class)
object PhotoRepositoryModule {


    @Singleton
    @Provides
    fun providePhotosRepository(
        photosDataSource: PhotosDataSource,
        ioDispatcher: CoroutineDispatcher
    ): PhotosRepository {
        return DefaultPhotosRepository(
            photosDataSource, ioDispatcher
        )
    }


}