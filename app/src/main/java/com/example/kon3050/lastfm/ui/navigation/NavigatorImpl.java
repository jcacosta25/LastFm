package com.example.kon3050.lastfm.ui.navigation;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.example.kon3050.lastfm.ui.detail.DetailActivity;
import com.example.kon3050.lastfm.ui.detail.DetailFragment;
import com.example.kon3050.lastfm.ui.home.MainActivity;
import com.example.kon3050.lastfm.ui.home.TopArtistFragment;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import static com.example.kon3050.lastfm.ui.detail.DetailFragmentKt.BUNDLE_ARTIST_NAME;

public class NavigatorImpl implements Navigator{

    private NavigatorManager mNavigatorManager;
    private Context mContext;


    @Inject
    public NavigatorImpl(NavigatorManager navigatorManager, Context context) {
        this.mNavigatorManager = navigatorManager;
        this.mContext = context;
    }


    @Override
    public void navigateToMainActivity() {
       mNavigatorManager.clearBackStack();
        Intent intent = new Intent(mContext, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mContext.startActivity(intent);
    }

    @Override
    public void navigateToDetailActivity(@NotNull String artistName) {
        Intent intent = new Intent(mContext, DetailActivity.class);
        intent.putExtra(BUNDLE_ARTIST_NAME,artistName);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mContext.startActivity(intent);
    }

    @Override
    public void navigateToDetailScreen(@NotNull String artistName) {
        Fragment fragment = mNavigatorManager.getFragmentByTag(DetailFragment.class.getSimpleName());
        if (fragment == null) {
            fragment = DetailFragment.newInstance(artistName);
        }
        mNavigatorManager.navigateWithStack(fragment);
    }

    @Override
    public void navigateToTopArtistScreen() {
        String tag = TopArtistFragment.class.getSimpleName();
        Fragment fragment = mNavigatorManager.getFragmentByTag(tag);
        if (fragment == null) {
            fragment = TopArtistFragment.newInstance();
        }
        mNavigatorManager.navigateWithStack(fragment);
    }

    @NotNull
    @Override
    public NavigatorManager getNavigator() {
        return mNavigatorManager;
    }
}
