package com.catalogapptest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.catalogapptest.databinding.ItemActivityListBinding
import com.catalogapptest.model.Activity
import java.util.*


class ActivitiesAdapter(private val mContext: Context, mList: ArrayList<Activity>) :
    RecyclerView.Adapter<ActivitiesAdapter.MovieViewHolder>() {

    private var movieList: ArrayList<Activity>

    init {
        this.movieList = mList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val binding = ItemActivityListBinding.inflate(inflater, parent, false)

        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.itemBinding.activity = movieList[position]

        /*
        val url = "http://image.tmdb.org/t/p/w500${movieList[position].posterPath}"
        Glide.with(mContext).load(url)
            .into(holder.itemBinding.posterImage)
         */
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun updateList(updatedList: ArrayList<Activity>) {
        movieList = updatedList
        notifyDataSetChanged()
    }

    fun getItemAt(position: Int): Activity {
        return movieList[position]
    }

    inner class MovieViewHolder(
        val itemBinding: ItemActivityListBinding
    ) : RecyclerView.ViewHolder(itemBinding.root)

}