package com.example.cosmos.di

import android.content.Context
import com.example.cosmos.data.AppDatabase
import com.example.cosmos.data.PhotoDao
import com.example.cosmos.data.PhotoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
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
    fun providePhotoDao(appDatabase: AppDatabase): PhotoDao {
        return appDatabase.photoDao()
    }
}


@Module
@InstallIn(SingletonComponent::class)
object PhotoRepositoryModule {

    @Singleton
    @Provides
    fun providePhotosRepository(
        photoDao: PhotoDao
    ): PhotoRepository {
        return PhotoRepository(
            photoDao
        )
    }
}