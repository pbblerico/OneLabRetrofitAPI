package com.example.onelabretrofitapi.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import coil.size.Scale
import com.example.onelabretrofitapi.R
import com.example.onelabretrofitapi.core.functional.Resource
import com.example.onelabretrofitapi.databinding.FragmentChaarcterInfoBinding
import com.example.onelabretrofitapi.presentation.model.Character
import com.example.onelabretrofitapi.presentation.viewmodel.CharacterInfoViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ChaarcterInfoFragment : Fragment() {
    private lateinit var binding: FragmentChaarcterInfoBinding
    private val args: ChaarcterInfoFragmentArgs by navArgs()
    private val viewModel: CharacterInfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChaarcterInfoBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        Log.d("info", "info ${args.characterId}")
        viewModel.getCharacterInfo(args.characterId)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,   object :
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        })
    }

    private fun initObservers() {
        viewModel.characterLiveData.observe(viewLifecycleOwner, ::onGetCharacter)
    }

    private fun onGetCharacter(character: Resource<Character>) {
        val isLoading = character is Resource.Loading
        binding.info.isVisible = isLoading.not()
        binding.progress.isVisible = isLoading
        when (character) {
            is Resource.Success -> {
                setViews(character.data)
            }

            is Resource.Error -> {
                Log.e("ListFragment", "Failed to load details", character.exception)
            }

            else -> Unit
        }
    }

    private fun setViews(character: Character) {
        with(binding) {
            name.text = character.name
            gender.text = character.gender
            species.text = character.species
            status.text = character.status

            img.load(character.image) {
                    crossfade(true)
                    placeholder(R.drawable.ic_launcher_background)
                    scale(Scale.FIT)
                }
        }
    }
}