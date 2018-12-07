package com.example.kon3050.lastfm.ui.navigation

import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.example.kon3050.lastfm.R
import javax.inject.Inject

class NavigatorManager @Inject constructor(private val fragmentManager: FragmentManager) {

    private var currentFragment: Fragment? = null

    fun clearBackStack() {
        val fragmentTransaction = fragmentManager.beginTransaction()
        val entryCount = fragmentManager.backStackEntryCount
        for (i in 0 until entryCount) {
            fragmentTransaction.remove(
                fragmentManager.findFragmentByTag(
                    fragmentManager.getBackStackEntryAt(
                        i
                    ).name
                )
            )
        }
        fragmentTransaction.commit()
        currentFragment = null
    }

    /**
     * Displays a Fragment with no stack
     *
     * @param fragment Fragment
     */
    fun navigateWithNoStack(fragment: Fragment) {
        fragment.takeIf { it.isAdded }.let {
            currentFragment = fragment
            return@let
        }

        if (currentFragment == null) {
            fragmentManager.beginTransaction()
                .add(R.id.container, fragment, fragment.javaClass.simpleName)
                .commitAllowingStateLoss()
        } else {
            fragmentManager.beginTransaction()
                .replace(R.id.container, fragment, fragment.javaClass.simpleName)
                .commit()
        }
        currentFragment = fragment
    }

    /**
     * Displays a Fragment and keeps the Stack
     *
     * @param fragment Fragment
     */
    fun navigateWithStack(fragment: Fragment) {

        fragment.takeIf { it.isAdded }.let {
            currentFragment = fragment
            return@let
        }

        if (currentFragment == null) {
            fragmentManager.beginTransaction()
                .add(R.id.container, fragment, fragment.javaClass.simpleName)
                .addToBackStack(fragment.javaClass.simpleName)
                .commitAllowingStateLoss()
        } else {
            fragmentManager.beginTransaction()
                .replace(R.id.container, fragment, fragment.javaClass.simpleName)
                .addToBackStack(fragment.javaClass.simpleName)
                .commit()
        }
        currentFragment = fragment
    }

    fun showDialog(dialogFragment: DialogFragment) {
        currentFragment = dialogFragment
        dialogFragment.show(fragmentManager, dialogFragment.tag)
    }

    /**
     * Returns the current visible Fragment
     *
     * @return The current visible Fragment.
     */
    fun getCurrentFragment(): Fragment? {
        if (currentFragment == null) {
            currentFragment = fragmentManager.findFragmentById(R.id.container)
        }
        return currentFragment
    }

    fun getFragmentByTag(tag: String): Fragment? = fragmentManager.findFragmentByTag(tag)


    fun popStack() {
        fragmentManager.popBackStackImmediate()
    }
}