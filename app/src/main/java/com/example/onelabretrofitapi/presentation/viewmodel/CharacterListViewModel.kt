package com.example.onelabretrofitapi.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.onelabretrofitapi.core.Resource
import com.example.onelabretrofitapi.core.onFailure
import com.example.onelabretrofitapi.core.onSuccess
import com.example.onelabretrofitapi.domain.repository.CharactersRepository
import com.example.onelabretrofitapi.domain.useCase.CharacterListUseCase
import com.example.onelabretrofitapi.domain.useCase.CharacterPagingDataUseCase
import com.example.onelabretrofitapi.domain.useCase.CharacterSaveUseCase
import com.example.onelabretrofitapi.presentation.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val characterListUseCase: CharacterListUseCase,
    private val characterSaveUseCase: CharacterSaveUseCase,
    private val characterPagingUseCase: CharacterPagingDataUseCase
): ViewModel() {
    private val _charactersLiveData = MutableLiveData<Resource<List<Character>>>()
    private val _data = MutableLiveData<Resource<PagingData<Character>>>()
    val characterLiveData: LiveData<Resource<List<Character>>>
        get() = _charactersLiveData
    val data: LiveData<Resource<PagingData<Character>>>
        get() = _data


    fun getPagingData() {
        _data.value = Resource.Loading
        viewModelScope.launch {
            characterPagingUseCase.execute().collect {data ->
                try {
                    _data.value = Resource.Success(data)
                } catch (e: Exception) {
                    _data.value = Resource.Error(e)
                }
            }
        }
    }
    fun getCharacters() {
        _charactersLiveData.value = Resource.Loading
        viewModelScope.launch {
            characterListUseCase.execute()
                .onFailure {throwable ->
                    _charactersLiveData.value = Resource.Error(throwable)
                }
                .onSuccess { data ->
                    _charactersLiveData.value = Resource.Success(data.characterList)
                }
        }
    }

    fun saveCharacter(character: Character) {
        viewModelScope.launch {
            characterSaveUseCase.execute(character)
        }
    }
}