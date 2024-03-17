package com.example.onelabretrofitapi.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.onelabretrofitapi.data.api.CharactersApi
import com.example.onelabretrofitapi.presentation.model.Character

//class CharacterPagingResource(private val charactersApi: CharactersApi): PagingSource<Int, Character>() {
//    override fun getRefreshKey(state: PagingState<Int, Character>): Int? = 0
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
//
//    }
//
//}