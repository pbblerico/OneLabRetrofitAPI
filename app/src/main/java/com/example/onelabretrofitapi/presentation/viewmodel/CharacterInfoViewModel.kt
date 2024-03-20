package com.example.onelabretrofitapi.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onelabretrofitapi.core.Resource
import com.example.onelabretrofitapi.core.onFailure
import com.example.onelabretrofitapi.core.onSuccess
import com.example.onelabretrofitapi.domain.useCase.CharacterInfoUseCase
import com.example.onelabretrofitapi.presentation.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CharacterInfoViewModel @Inject constructor(
    private val characterInfoUseCase: CharacterInfoUseCase
): ViewModel()  {
    private val _characterInfoLiveData = MutableLiveData<Resource<Character>>()
    val characterLiveData: LiveData<Resource<Character>>
        get() = _characterInfoLiveData

    fun getCharacterInfo(id: Int) {
        _characterInfoLiveData.value = Resource.Loading
        viewModelScope.launch {
            characterInfoUseCase.execute(id)
                .onFailure {throwable ->
                    _characterInfoLiveData.value = Resource.Error(throwable)
                }
                .onSuccess { data ->
                    _characterInfoLiveData.value = Resource.Success(data)
                }
        }
    }
}