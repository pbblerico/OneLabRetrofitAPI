package com.example.onelabretrofitapi.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onelabretrofitapi.core.Resource
import com.example.onelabretrofitapi.databinding.FragmentCharacterSavedListBinding
import com.example.onelabretrofitapi.presentation.adapter.CharacterAdapter
import com.example.onelabretrofitapi.presentation.model.Character
import com.example.onelabretrofitapi.presentation.viewmodel.CharacterSavedListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CharacterSavedListFragment : Fragment() {
    private lateinit var binding: FragmentCharacterSavedListBinding
    private val viewModel: CharacterSavedListViewModel by viewModels()
    private val charactersAdapter: CharacterAdapter by lazy(LazyThreadSafetyMode.NONE) {
        CharacterAdapter(
            onItemClick = { id ->
                onItemClick(id)
            },
            onIconClick = { character ->
                onIconClick(character)
            }
        )
    }

    private fun onIconClick(character: Character) {
        character.id?.let { viewModel.deleteCharacterById(it) }
    }

    private fun onItemClick(id: Int) {
        val action = CharacterSavedListFragmentDirections.actionCharacterSavedListFragmentToChaarcterInfoFragment(id)
        findNavController().navigate(action)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterSavedListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
        viewModel.getCharacters()
    }

    private fun initObservers() {
        viewModel.characterLiveData.observe(viewLifecycleOwner, ::onGetCharacterList)
    }

    private fun onGetCharacterList(characterListResource: Resource<List<Character>>) {
        val isLoading = characterListResource is Resource.Loading
        binding.rv.isVisible = isLoading.not()
        binding.progress.isVisible = isLoading
        when (characterListResource) {
            is Resource.Success -> {
                charactersAdapter.submitList(characterListResource.data)
                Log.d("ListFragment", "${characterListResource.data}")
            }

            is Resource.Error -> {
                Log.e("ListFragment", "Failed to load list", characterListResource.exception)
            }

            else -> Unit
        }
    }

    private fun initViews() {
        with(binding) {
            rv.adapter = charactersAdapter
            rv.layoutManager = LinearLayoutManager(context)
        }
    }
}