package com.catalogapptest.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.catalogapptest.R
import com.catalogapptest.adapters.ArticlesAdapter
import com.catalogapptest.databinding.FragmentActivitiesBinding
import com.catalogapptest.databinding.FragmentArticlesBinding
import com.catalogapptest.model.Article
import com.catalogapptest.viewmodel.ArticlesViewModel

class ArticlesFragment : Fragment() {

    private lateinit var viewModel: ArticlesViewModel
    private lateinit var binding: FragmentArticlesBinding
    private lateinit var adapter: ArticlesAdapter

    private var articleList = ArrayList<Article>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ArticlesViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_articles, container, false)
        binding.viewModel = viewModel
        initRecyclerView()
        initData()
        return binding.root
    }

    /**
     * init for observables
     */
    private fun initData() {
        viewModel.getArticles().observe(viewLifecycleOwner, {articles ->
            articles?.let {
                articleList.addAll(it)
                adapter.updateList(articleList)
            }
        })
    }

    /**
     * configure for article list
     */
    private fun initRecyclerView() {
        context?.let {
            binding.rvArticles.layoutManager = LinearLayoutManager(it)
            adapter = ArticlesAdapter(it, articleList)
            binding.rvArticles.adapter = adapter
        }
    }

}