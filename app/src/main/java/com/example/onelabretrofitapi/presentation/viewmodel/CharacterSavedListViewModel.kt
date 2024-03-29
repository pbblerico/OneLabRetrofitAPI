package com.example.onelabretrofitapi.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onelabretrofitapi.core.functional.Resource
import com.example.onelabretrofitapi.core.functional.onFailure
import com.example.onelabretrofitapi.core.functional.onSuccess
import com.example.onelabretrofitapi.domain.useCase.CharacterDeleteByIdUseCase
import com.example.onelabretrofitapi.domain.useCase.CharacterSavedListUseCase
import com.example.onelabretrofitapi.presentation.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterSavedListViewModel @Inject constructor(
    private val savedCharacters: CharacterSavedListUseCase,
    private val deleteCharacters: CharacterDeleteByIdUseCase
): ViewModel() {
    private val _charactersLiveData = MutableStateFlow<Resource<List<Character>>>(Resource.Empty)
    val characterLiveData: StateFlow<Resource<List<Character>>>
        get() = _charactersLiveData

    init {
        viewModelScope.launch {
            getCharacters()
        }
    }

    fun getCharacters() {
        _charactersLiveData.value = Resource.Loading
        viewModelScope.launch {
            savedCharacters.execute()
                .onFailure {throwable ->
                    _charactersLiveData.value = Resource.Error(throwable)
                }
                .onSuccess {data ->
                    _charactersLiveData.value = Resource.Success(data)
                }
        }
    }

    fun deleteCharacterById(id: Int) {
        viewModelScope.launch {
            deleteCharacters.execute(id)
        }
        getCharacters()
    }
}