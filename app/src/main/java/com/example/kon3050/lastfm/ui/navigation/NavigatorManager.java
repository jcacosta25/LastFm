package com.example.kon3050.lastfm.ui.navigation;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.kon3050.lastfm.R;

import javax.inject.Inject;

public class NavigatorManager {

    private FragmentManager mFragmentManager;
    private Fragment mCurrentFragment;

    /**********************************************************************************************
     * Constructors
     **********************************************************************************************/

    @Inject
    public NavigatorManager(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
    }

    /**********************************************************************************************
     * Public Methods
     **********************************************************************************************/

    public void clearBackStack() {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        int entryCount = mFragmentManager.getBackStackEntryCount();
        for (int i = 0; i < entryCount; i++) {
            String name = mFragmentManager.getBackStackEntryAt(i).getName();
            fragmentTransaction.remove(mFragmentManager.findFragmentByTag(name));
        }
        fragmentTransaction.commit();
        mCurrentFragment = null;
    }

    /**
     * Displays a Fragment with no stack
     *
     * @param fragment Fragment
     */
    public void navigateWithNoStack(Fragment fragment) {
        if (fragment.isAdded()) {
            mCurrentFragment = fragment;
            return;
        }

        if (mCurrentFragment == null) {
            mFragmentManager.beginTransaction()
                    .add(R.id.container, fragment, fragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();
        } else {
            mFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment, fragment.getClass().getSimpleName())
                    .commit();
        }
        mCurrentFragment = fragment;
    }

    /**
     * Displays a Fragment and keeps the Stack
     *
     * @param fragment Fragment
     */
    public void navigateWithStack(Fragment fragment) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        if (fragment.isAdded()) {
            mCurrentFragment = fragment;
            return;
        }

        if (mCurrentFragment == null) {
            transaction.add(R.id.container, fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();
        } else {
            mFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commit();
        }

        mCurrentFragment = fragment;
    }

    public void showDialog(DialogFragment dialogFragment) {
        mCurrentFragment = dialogFragment;
        dialogFragment.show(mFragmentManager, dialogFragment.getTag());
    }


    /**
     * Returns the current visible Fragment
     *
     * @return The current visible Fragment.
     */
    public Fragment getCurrentFragment() {
        if (mCurrentFragment == null) {
            mCurrentFragment = mFragmentManager.findFragmentById(R.id.container);
        }
        return mCurrentFragment;
    }

    public Fragment getFragmentByTag(String tag) {
        return mFragmentManager.findFragmentByTag(tag);
    }

    public void popStack() {
        mFragmentManager.popBackStackImmediate();
    }
}
