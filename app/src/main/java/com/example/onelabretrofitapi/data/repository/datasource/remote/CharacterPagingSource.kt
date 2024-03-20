package com.example.onelabretrofitapi.data.repository.datasource.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.onelabretrofitapi.data.api.CharactersApi
import com.example.onelabretrofitapi.data.mapper.toCharacterList
import com.example.onelabretrofitapi.presentation.model.Character
import javax.inject.Inject

class CharacterPagingSource @Inject constructor(
    private val api: CharactersApi
): PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val pageNumber = params.key ?: 1
            val response = api.getCharacters(pageNumber)
            val nextPage = if(pageNumber < (response.info.pages ?: 1)) pageNumber + 1 else null
            LoadResult.Page(
                data = response.toCharacterList().characterList,
                prevKey = null,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}