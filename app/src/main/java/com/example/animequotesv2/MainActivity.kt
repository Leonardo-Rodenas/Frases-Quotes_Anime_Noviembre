package com.example.animequotesv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import com.example.animequotesv2.databinding.ActivityMainBinding
import androidx.navigation.ui.NavigationUI
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navegador:NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       var navhost: NavHostFragment = supportFragmentManager.findFragmentById(R.id.fragCont) as NavHostFragment
        navegador = navhost.navController;

        NavigationUI.setupActionBarWithNavController(this, navegador)

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navegador, null)
    }
}