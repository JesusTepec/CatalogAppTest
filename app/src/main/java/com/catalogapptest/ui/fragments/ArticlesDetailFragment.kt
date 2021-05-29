package com.catalogapptest.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.catalogapptest.R
import com.catalogapptest.databinding.FragmentDetailsArticleBinding
import com.catalogapptest.viewmodel.ArticlesDetailViewModel


class ArticlesDetailFragment : Fragment() {

    private var articleID: Long? = null

    private lateinit var viewModel: ArticlesDetailViewModel
    private lateinit var binding: FragmentDetailsArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            articleID = it.getLong(ARG_ARTICLE_ID)
        }
        viewModel = ViewModelProvider(this).get(ArticlesDetailViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details_article, container, false)
        binding.viewModel = viewModel
        initData()
        return binding.root
    }

    private fun initData() {
        articleID?.let { it ->
            viewModel.getDetails(it).observe(viewLifecycleOwner, {article ->
                binding.model = article
            })
        }
    }

    companion object {

        private const val ARG_ARTICLE_ID = "article_id"

        @JvmStatic
        fun newInstance(articleId: Long) =
            ArticlesDetailFragment().apply {
                arguments = Bundle().apply {
                    putLong(ARG_ARTICLE_ID, articleId)
                }
            }
    }
}