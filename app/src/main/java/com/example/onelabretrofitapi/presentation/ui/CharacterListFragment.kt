package com.example.onelabretrofitapi.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onelabretrofitapi.core.functional.Resource
import com.example.onelabretrofitapi.databinding.FragmentCharacterListBinding
import com.example.onelabretrofitapi.presentation.adapter.CharacterPagingAdapter
import com.example.onelabretrofitapi.presentation.model.Character
import com.example.onelabretrofitapi.presentation.viewmodel.CharacterListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CharacterListFragment : Fragment() {
    private lateinit var binding: FragmentCharacterListBinding

    private val charactersAdapter by lazy(LazyThreadSafetyMode.NONE) {
        CharacterPagingAdapter(
            onItemClick = { id ->
                onItemClick(id)
            },
            onIconClick = { character ->
                onIconClick(character)
            }
        )
    }

    private val viewModel: CharacterListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
//        viewModel.getCharacters()
        viewModel.getPagingData()
    }

    private fun initObservers() {
//        viewModel.characterLiveData.observe(viewLifecycleOwner, ::onGetCharacters)
        viewModel.data.observe(viewLifecycleOwner) {
            viewLifecycleOwner.lifecycleScope.launch {
                onGetPagingData(it)
            }
        }
    }

    private suspend fun onGetPagingData(pagingData: Resource<PagingData<Character>>) {
        val isLoading = pagingData is Resource.Loading
        binding.rv.isVisible = isLoading.not()
        binding.progress.isVisible = isLoading
        when (pagingData) {
            is Resource.Success -> {
                charactersAdapter.submitData(pagingData.data)
            }

            is Resource.Error -> {
                Log.e("ListFragment", "Failed to load list", pagingData.exception)
            }

            else -> Unit
        }
    }

//    private fun onGetCharacters(characterListResource: Resource<List<Character>>) {
//        val isLoading = characterListResource is Resource.Loading
//        binding.rv.isVisible = isLoading.not()
//        binding.progress.isVisible = isLoading
//        when (characterListResource) {
//            is Resource.Success -> {
//                charactersAdapter.submitList(characterListResource.data)
//                Log.d("ListFragment", "${characterListResource.data}")
//            }
//
//            is Resource.Error -> {
//                Log.e("ListFragment", "Failed to load list", characterListResource.exception)
//            }
//
//            else -> Unit
//        }
//    }

    private fun onItemClick(id: Int) {
        val action = CharacterListFragmentDirections.actionCharacterListFragmentToChaarcterInfoFragment(id)
        findNavController().navigate(action)
        Log.d("ListFragment", "$id item")
    }

    private fun onIconClick(character: Character) {
        viewModel.saveCharacter(character)
    }

    private fun initViews() {
        with(binding) {
            rv.adapter = charactersAdapter
            rv.layoutManager = LinearLayoutManager(context)
        }
    }

}