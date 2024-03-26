package com.example.onelabretrofitapi.presentation.ui

import android.Manifest
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.onelabretrofitapi.CustomBroadcastReceiver
import com.example.onelabretrofitapi.core.functional.Resource
import com.example.onelabretrofitapi.core.notification.CharacterNotificationManager
import com.example.onelabretrofitapi.databinding.FragmentCharacterListBinding
import com.example.onelabretrofitapi.presentation.adapter.CharacterAdapter
import com.example.onelabretrofitapi.presentation.adapter.CharacterPagingAdapter
import com.example.onelabretrofitapi.presentation.model.Character
import com.example.onelabretrofitapi.presentation.viewmodel.CharacterListViewModel
import com.example.onelabretrofitapi.utils.PermissionResult
import com.example.onelabretrofitapi.utils.checkSelfPermission
import com.example.onelabretrofitapi.utils.isPermissionGranted
import com.example.onelabretrofitapi.utils.isTiramisuOrUp
import com.example.onelabretrofitapi.worker.MyWorker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission())
        { granted ->
            if(granted) {

            }
        }

    private val settingsLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if(isTiramisuOrUp().not()) {
            when {
                Manifest.permission.POST_NOTIFICATIONS.isPermissionGranted(requireContext()) -> {

                }
                else -> {

                }
            }
        }
    }

    @Inject
    lateinit var characterNotificationManager: CharacterNotificationManager

    private lateinit var binding: FragmentCharacterListBinding

    private val charactersPagingAdapter by lazy(LazyThreadSafetyMode.NONE) {
        CharacterPagingAdapter(
            onItemClick = { id ->
                onItemClick(id)
            },
            onIconClick = { character ->
                onIconClick(character)
            }
        )
    }

    private val charactersAdapter by lazy(LazyThreadSafetyMode.NONE) {
        CharacterAdapter(
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

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPostNotificationPermission()
        initViews()
        initObservers()
        viewModel.getCharacters(false)
//        viewModel.getPagingData()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun checkPostNotificationPermission() {
        (context as? FragmentActivity)?.let {
            Manifest.permission.POST_NOTIFICATIONS.checkSelfPermission(
                it,
                permissionLauncher
            ) {permissionResult ->
                when(permissionResult) {
                    PermissionResult.GRANTED -> {

                    }
                    PermissionResult.SHOW_PERMISSION_RATIONALE -> {
                        showRationaleDialog()
                    }
                    PermissionResult.NOT_GRANTED -> {
                        Unit
                    }
                }
            }
        }
    }

    private fun showRationaleDialog() {
        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setMessage("You need to provide access in settings")
            .setTitle("Alert")
            .setPositiveButton("Settings") {dialog, _ ->
                dialog.dismiss()
                redirectToSettings()
            }.setNegativeButton("Cancel") {dialog, _ ->
                dialog.dismiss()
            }
        val alert = dialog.create()
        alert.show()
    }

    private fun redirectToSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", requireContext().packageName, null)
        intent.data = uri
        settingsLauncher.launch(intent)
    }

    private fun initObservers() {
        viewModel.characterLiveData.observe(viewLifecycleOwner, ::onGetCharacters)
//        viewModel.data.observe(viewLifecycleOwner) {
//            viewLifecycleOwner.lifecycleScope.launch {
//                onGetPagingData(it)
//            }
//        }
    }

    private suspend fun onGetPagingData(pagingData: Resource<PagingData<Character>>) {
//        val isLoading = pagingData is Resource.Loading
//        binding.rv.isVisible = isLoading.not()
//        binding.progress.isVisible = isLoading
//        when (pagingData) {
//            is Resource.Success -> {
//                charactersAdapter.submitData(pagingData.data)
//            }
//
//            is Resource.Error -> {
//                Log.e("ListFragment", "Failed to load list", pagingData.exception)
//            }
//
//            else -> Unit
//        }
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
        val action = CharacterListFragmentDirections.actionCharacterListFragmentToChaarcterInfoFragment(id)
        Navigation.findNavController(binding.root).navigate(action)
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