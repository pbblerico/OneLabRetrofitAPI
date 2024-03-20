package com.example.onelabretrofitapi.data.di.useCase

import com.example.onelabretrofitapi.domain.repository.CharactersRepository
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
    fun provideCharacterSavedListUseCase(repo: CharactersRepository): CharacterSavedListUseCase = CharacterSavedListUseCaseImpl(repo)

    @Provides
    fun provideCharacterSaveUseCase(repo: CharactersRepository): CharacterSaveUseCase = CharacterSaveUseCaseImpl(repo)

    @Provides
    fun provideCharacterDeleteByIdUseCase(repo: CharactersRepository): CharacterDeleteByIdUseCase = CharacterDeleteByIdUseCaseImpl(repo)
}