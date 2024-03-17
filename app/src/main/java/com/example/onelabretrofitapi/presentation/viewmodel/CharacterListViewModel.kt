package com.example.onelabretrofitapi.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onelabretrofitapi.core.Resource
import com.example.onelabretrofitapi.core.onFailure
import com.example.onelabretrofitapi.core.onSuccess
import com.example.onelabretrofitapi.data.repository.CharactersRepository
import com.example.onelabretrofitapi.presentation.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val repo: CharactersRepository
): ViewModel() {
    private val _charactersLiveData = MutableLiveData<Resource<List<Character>>>()
    val characterLiveData: LiveData<Resource<List<Character>>>
        get() = _charactersLiveData

    fun getCharacters() {
        _charactersLiveData.value = Resource.Loading
        viewModelScope.launch {
            repo.getCharacterList()
                .onFailure {throwable ->  
                    _charactersLiveData.value = Resource.Error(throwable)
                }
                .onSuccess { data ->
                    _charactersLiveData.value = Resource.Success(data.characterList)
                }
        }
    }
}