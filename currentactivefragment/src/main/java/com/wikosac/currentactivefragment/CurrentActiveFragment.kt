package com.wikosac.currentactivefragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment

object CurrentActiveFragment {

    fun get(
        fragmentActivity: FragmentActivity,
        navHostFragmentId: Int,
        currentActiveFragment: (Fragment) -> Unit
    ) {
        val sfm = fragmentActivity.supportFragmentManager
        val fragmentLifecycleCallbacks: FragmentLifecycleCallbacks
        val navHostFragment = sfm.findFragmentById(navHostFragmentId) as NavHostFragment
        fragmentLifecycleCallbacks = FragmentLifecycleCallbacks(navHostFragment) { activeFragment ->
            activeFragment?.let {
                currentActiveFragment(it)
            }
        }
        sfm.registerFragmentLifecycleCallbacks(fragmentLifecycleCallbacks, true)
    }

}