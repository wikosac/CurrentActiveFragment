package com.wikosac.currentactivefragment

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.wikosac.currentactivefragment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var currentActiveFragment: CurrentActiveFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        currentActiveFragment = CurrentActiveFragment(this)
        currentActiveFragment.get(R.id.nav_host_fragment_activity_main) { activeFragment ->
            val fragmentName = activeFragment.javaClass.simpleName
            Log.d("CurrentActiveFragment", "Current active fragment: $fragmentName")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::currentActiveFragment.isInitialized) {
            currentActiveFragment.unregister()
        }
    }
}