package com.example.kon3050.lastfm.ui.home


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kon3050.lastfm.R
import com.example.kon3050.lastfm.databinding.FragmentTopArtistBinding
import com.example.kon3050.lastfm.ui.base.BaseFragment
import com.example.kon3050.lastfm.ui.model.TopArtistUiModel
import com.example.kon3050.lastfm.ui.navigation.OnBackPressListener
import com.example.kon3050.lastfm.ui.views.TopArtistView
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class TopArtistFragment : BaseFragment(),TopArtistView,OnBackPressListener {

    lateinit var viewModel: TopArtistViewModel
    lateinit var binding: FragmentTopArtistBinding
    private var adapter: TopArtistAdapter? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val adapterListener = TopArtistAdapterListener<TopArtistUiModel> {
        _,_,(artist) -> navigator.navigateToDetailActivity(artist)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentTopArtistBinding>(inflater,R.layout.fragment_top_artist,container,false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        userComponent.inject(this)

        binding.rvTopSongs.layoutManager = LinearLayoutManager(activity)
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(TopArtistViewModel::class.java)

        viewModel.loadTopArtist().observe(this, Observer { response ->
            adapter = TopArtistAdapter(response?.artists!!.toList())
            binding.rvTopSongs.adapter = adapter
            adapter!!.setTopArtistAdapterListener(adapterListener)
        })
    }

    override fun onSuccessArtistsLoaded(artistModel: List<TopArtistUiModel>) {
        adapter = TopArtistAdapter(artistModel)
        binding.rvTopSongs.adapter = adapter
        adapter!!.setTopArtistAdapterListener(adapterListener)
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(throwable: Throwable) {
        throwable.printStackTrace()
        Snackbar.make(binding.root, throwable.message as CharSequence, Snackbar.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
       activity?.finish()
    }

    companion object {
        @JvmStatic
        fun newInstance() : Fragment{
            return TopArtistFragment()
        }
    }
}
