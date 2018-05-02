package com.example.kon3050.lastfm.ui.home

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.kon3050.lastfm.R
import com.example.kon3050.lastfm.databinding.ItemArtistBinding
import com.example.kon3050.lastfm.ui.model.TopArtistUiModel

class TopArtistAdapter(val artist: List<TopArtistUiModel>) : RecyclerView.Adapter<TopArtistAdapter.ViewHolder>() {

    lateinit var listener: TopArtistAdapterListener<TopArtistUiModel>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemArtistBinding>(LayoutInflater.from(parent.context), R.layout.item_artist, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = artist[position]
        holder.bind.model = model
        holder.bind.root.setOnClickListener {
            listener.onItemClick(it,position,model)
        }
    }

    override fun getItemCount(): Int = artist.size

    fun setTopArtistAdapterListener(listener: TopArtistAdapterListener<TopArtistUiModel>) {
        this.listener = listener
    }

    inner class ViewHolder(binding: ItemArtistBinding) : RecyclerView.ViewHolder(binding.root) {
        val bind = binding
    }
}

