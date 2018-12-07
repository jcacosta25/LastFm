package com.example.kon3050.lastfm.ui.detail


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kon3050.lastfm.R
import com.example.kon3050.lastfm.databinding.FragmentDetailBinding
import com.example.kon3050.lastfm.ui.base.BaseFragment
import com.example.kon3050.lastfm.ui.navigation.OnBackPressListener
import javax.inject.Inject


const val BUNDLE_ARTIST_NAME = "artist_name"

/**
 * A simple [Fragment] subclass.
 *
 */
class DetailFragment : BaseFragment(), OnBackPressListener {

    private lateinit var viewModel: DetailArtistViewModel
    private lateinit var binding: FragmentDetailBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        userComponent.inject(this)
        viewModel =
                ViewModelProviders.of(this, viewModelFactory).get(DetailArtistViewModel::class.java)

        arguments?.let { args ->
            args.getString(BUNDLE_ARTIST_NAME)?.let { name ->
                if (name.isNotBlank()) {
                    viewModel.setArtistName(name)
                }
            }
        }

        viewModel.getArtist().observe(this, Observer { detailModel ->
            if (detailModel != null && !detailModel.error) {
                binding.artist = detailModel
            }
        })
    }

    override fun onBackPressed() {
        requireActivity().finish()
    }

    companion object {

        @JvmStatic
        fun newInstance(artistName: String): Fragment {
            val fragment = DetailFragment()
            val args = Bundle()
            args.putString(BUNDLE_ARTIST_NAME, artistName)
            fragment.arguments = args
            return fragment
        }
    }
}
