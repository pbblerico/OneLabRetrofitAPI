package com.example.onelabretrofitapi.presentation

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
import com.example.onelabretrofitapi.R
import com.example.onelabretrofitapi.core.Resource
import com.example.onelabretrofitapi.databinding.FragmentCharacterListBinding
import com.example.onelabretrofitapi.presentation.adapter.CharacterAdapter
import com.example.onelabretrofitapi.presentation.model.Character
import com.example.onelabretrofitapi.presentation.viewmodel.CharacterListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CharacterListFragment : Fragment() {
    private lateinit var binding: FragmentCharacterListBinding

    private val charactersAdapter by lazy(LazyThreadSafetyMode.NONE) {
        CharacterAdapter(
            onItemClick = { id ->
                onItemClick(id)
            },
            onIconClick = { id ->
                onIconClick(id)
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
        viewModel.getCharacters()
    }

    private fun initObservers() {
        viewModel.characterLiveData.observe(viewLifecycleOwner, ::onGetCharacters)
    }

    private fun onGetCharacters(characterListResource: Resource<List<Character>>) {
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

    private fun onItemClick(id: Int) {
        val action = CharacterListFragmentDirections.actionCharacterListFragment2ToChaarcterInfoFragment(id)
        findNavController().navigate(action)
        Log.d("ListFragment", "$id item")
    }

    private fun onIconClick(id: Int) {
//        val action =
        Log.d("ListFragment", "$id icon")

    }

    private fun initViews() {
        with(binding) {
            rv.adapter = charactersAdapter
            rv.layoutManager = LinearLayoutManager(context)
        }
    }

}