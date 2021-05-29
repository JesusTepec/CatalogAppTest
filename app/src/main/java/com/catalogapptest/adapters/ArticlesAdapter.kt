package com.catalogapptest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.catalogapptest.databinding.ItemArticleListBinding
import com.catalogapptest.model.Article
import java.util.*


class ArticlesAdapter(private val mContext: Context, mList: ArrayList<Article>) :
    RecyclerView.Adapter<ArticlesAdapter.CustomViewHolder>() {

    private var articleList: ArrayList<Article>

    init {
        this.articleList = mList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val binding = ItemArticleListBinding.inflate(inflater, parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.itemBinding.article = articleList[position]

    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    fun updateList(updatedList: ArrayList<Article>) {
        articleList = updatedList
        notifyDataSetChanged()
    }

    fun getItemAt(position: Int): Article {
        return articleList[position]
    }

    inner class CustomViewHolder(
        val itemBinding: ItemArticleListBinding
    ) : RecyclerView.ViewHolder(itemBinding.root)

}