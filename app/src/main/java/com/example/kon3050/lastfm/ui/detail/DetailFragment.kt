package com.example.kon3050.lastfm.ui.detail


import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.kon3050.lastfm.R
import com.example.kon3050.lastfm.ui.base.BaseFragment
import com.example.kon3050.lastfm.ui.navigation.OnBackPressListener
import javax.inject.Inject

const val BUNDLE_ARTIST_NAME = "artist_name"

/**
 * A simple [Fragment] subclass.
 *
 */
class DetailFragment : BaseFragment(), OnBackPressListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        userComponent.inject(this)
    }

    override fun onBackPressed() {
        navigator.navigateToMainActivity()
    }

    companion object {

        @JvmStatic
        fun newInstance(artistName: String) :Fragment {
            val fragment = DetailFragment()
            val args = Bundle()
            args.putString(BUNDLE_ARTIST_NAME,artistName)
            fragment.arguments = args
            return fragment
        }
    }
}
