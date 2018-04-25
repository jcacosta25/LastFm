package com.example.kon3050.lastfm.ui.navigation


import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import com.example.kon3050.lastfm.ui.detail.BUNDLE_ARTIST_NAME
import com.example.kon3050.lastfm.ui.detail.DetailActivity
import com.example.kon3050.lastfm.ui.detail.DetailFragment
import com.example.kon3050.lastfm.ui.home.MainActivity
import com.example.kon3050.lastfm.ui.home.TopArtistFragment
import javax.inject.Inject

class NavigatorImpl @Inject
constructor(private val mNavigatorManager: NavigatorManager, private val mContext: Context) : Navigator {


    override fun navigateToMainActivity() {
        mNavigatorManager.clearBackStack()
        val intent = Intent(mContext, MainActivity::class.java)
        //intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        mContext.startActivity(intent)
    }

    override fun navigateToDetailActivity(artistName: String) {
        val intent = Intent(mContext, DetailActivity::class.java)
        intent.putExtra(BUNDLE_ARTIST_NAME, artistName)
        //intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        mContext.startActivity(intent)
    }

    override fun navigateToDetailScreen(artistName: String) {
        var fragment: Fragment? = mNavigatorManager.getFragmentByTag(DetailFragment::class.java.simpleName)
        if (fragment == null) {
            fragment = DetailFragment.newInstance(artistName)
        }
        mNavigatorManager.navigateWithStack(fragment)
    }

    override fun navigateToTopArtistScreen() {
        val tag = TopArtistFragment::class.java.simpleName
        var fragment: Fragment? = mNavigatorManager.getFragmentByTag(tag)
        if (fragment == null) {
            fragment = TopArtistFragment.newInstance()
        }
        mNavigatorManager.navigateWithStack(fragment)
    }

    override fun getNavigator(): NavigatorManager {
        return mNavigatorManager
    }
}
