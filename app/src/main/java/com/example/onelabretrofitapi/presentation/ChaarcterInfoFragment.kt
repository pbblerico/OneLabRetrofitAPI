package com.example.onelabretrofitapi.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.onelabretrofitapi.databinding.FragmentChaarcterInfoBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ChaarcterInfoFragment : Fragment() {
    private lateinit var binding: FragmentChaarcterInfoBinding
//    val args:

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChaarcterInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}