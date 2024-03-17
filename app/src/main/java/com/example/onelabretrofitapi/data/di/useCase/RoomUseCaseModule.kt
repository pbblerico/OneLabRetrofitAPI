package com.example.onelabretrofitapi.data.di.useCase

import com.example.onelabretrofitapi.data.db.CharacterDao
import com.example.onelabretrofitapi.data.repository.RoomRepository
import com.example.onelabretrofitapi.domain.useCase.CharacterDeleteByIdUseCase
import com.example.onelabretrofitapi.domain.useCase.CharacterDeleteByIdUseCaseImpl
import com.example.onelabretrofitapi.domain.useCase.CharacterSaveUseCase
import com.example.onelabretrofitapi.domain.useCase.CharacterSaveUseCaseImpl
import com.example.onelabretrofitapi.domain.useCase.CharacterSavedListUseCase
import com.example.onelabretrofitapi.domain.useCase.CharacterSavedListUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RoomUseCaseModule {

    @Provides
    fun provideCharacterSavedListUseCase(repo: RoomRepository): CharacterSavedListUseCase = CharacterSavedListUseCaseImpl(repo)

    @Provides
    fun provideCharacterSaveUseCase(repo: RoomRepository): CharacterSaveUseCase = CharacterSaveUseCaseImpl(repo)

    @Provides
    fun provideCharacterDeleteByIdUseCase(repo: RoomRepository): CharacterDeleteByIdUseCase = CharacterDeleteByIdUseCaseImpl(repo)
}