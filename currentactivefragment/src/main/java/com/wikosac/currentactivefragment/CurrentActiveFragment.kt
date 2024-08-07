package com.wikosac.currentactivefragment

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment

class CurrentActiveFragment(
    private val fragmentActivity: FragmentActivity
) : AppCompatActivity() {

    private lateinit var fragmentLifecycleCallbacks: FragmentLifecycleCallbacks

    fun get(
        navHostFragmentId: Int,
        currentActiveFragment: (Fragment) -> Unit
    ) {
        val sfm = fragmentActivity.supportFragmentManager
        val navHostFragment = sfm.findFragmentById(navHostFragmentId) as NavHostFragment
        fragmentLifecycleCallbacks = FragmentLifecycleCallbacks(navHostFragment) { activeFragment ->
            activeFragment?.let {
                currentActiveFragment(it)
            }
        }
        sfm.registerFragmentLifecycleCallbacks(fragmentLifecycleCallbacks, true)
    }

    fun remove() {
        fragmentActivity.supportFragmentManager.unregisterFragmentLifecycleCallbacks(
            fragmentLifecycleCallbacks
        )
    }

}