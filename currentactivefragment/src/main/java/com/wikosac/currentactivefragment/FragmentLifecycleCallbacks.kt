package com.wikosac.currentactivefragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment

class FragmentLifecycleCallbacks(
    private val navHostFragment: NavHostFragment,
    private val onFragmentChanged: (Fragment?) -> Unit
) : FragmentManager.FragmentLifecycleCallbacks() {

    override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
        super.onFragmentResumed(fm, f)
        if (f.parentFragment == navHostFragment) {
            onFragmentChanged(f)
        }
    }

    override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {
        super.onFragmentPaused(fm, f)
        if (f.parentFragment == navHostFragment) {
            onFragmentChanged(null)
        }
    }
}