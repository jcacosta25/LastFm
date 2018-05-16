package com.example.kon3050.lastfm.ui.home


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kon3050.lastfm.R
import com.example.kon3050.lastfm.databinding.FragmentTopArtistBinding
import com.example.kon3050.lastfm.ui.base.BaseFragment
import com.example.kon3050.lastfm.ui.model.TopArtistUiModel
import com.example.kon3050.lastfm.ui.navigation.OnBackPressListener
import com.example.kon3050.lastfm.ui.utils.MarginDecoration
import com.example.kon3050.lastfm.ui.views.TopArtistView
import javax.inject.Inject


class TopArtistFragment : BaseFragment(), TopArtistView, OnBackPressListener {

    private lateinit var viewModel: TopArtistViewModel
    private lateinit var binding: FragmentTopArtistBinding
    private var adapter: TopArtistAdapter? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val adapterListener = object : TopArtistAdapterListener<TopArtistUiModel> {
        override fun onItemClick(view: View, position: Int, item: TopArtistUiModel) {
            navigator.navigateToDetailActivity(item.artistName)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentTopArtistBinding>(inflater, R.layout.fragment_top_artist, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        userComponent.inject(this)

        val layoutManager = GridLayoutManager(activity,2)
        val itemDecoration = MarginDecoration(activity!!)
        binding.rvTopArtist.addItemDecoration(itemDecoration)
        binding.rvTopArtist.setHasFixedSize(true)
        binding.rvTopArtist.layoutManager = layoutManager

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TopArtistViewModel::class.java)

        viewModel.loadTopArtist().observe(this, Observer { response ->
            adapter = TopArtistAdapter(response?.artists!!.toList())
            binding.rvTopArtist.adapter = adapter
            adapter!!.setTopArtistAdapterListener(adapterListener)
        })
    }

    override fun onSuccessArtistsLoaded(artistModel: List<TopArtistUiModel>) {
        adapter = TopArtistAdapter(artistModel)
        binding.rvTopArtist.adapter = adapter
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
        fun newInstance(): Fragment {
            return TopArtistFragment()
        }
    }
}
