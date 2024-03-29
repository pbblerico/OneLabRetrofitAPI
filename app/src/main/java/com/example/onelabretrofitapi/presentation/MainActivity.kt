package com.example.onelabretrofitapi.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.onelabretrofitapi.MyService
import com.example.onelabretrofitapi.R
import com.example.onelabretrofitapi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val serviceIntent = Intent(this, MyService::class.java)
        startService(serviceIntent)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_host) as NavHostFragment
        val navController = navHostFragment.navController


        val graph = navController.navInflater.inflate(R.navigation.character_nav)
        navController.setGraph(graph, intent.extras)
        binding.bottomNav.setupWithNavController(navController)
    }
}