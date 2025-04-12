package com.example.practicabooksoffline.hiilt.modules

import com.example.practicabooksoffline.api.LibraryService
import com.example.practicabooksoffline.db.dao.BookDao
import com.example.practicabooksoffline.repositories.BookRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    fun provideBookRepository(
        bookDao: BookDao,
        libraryService: LibraryService
    ): BookRepository {
        return BookRepository(
            bookDao,
            libraryService
        )
    }
}