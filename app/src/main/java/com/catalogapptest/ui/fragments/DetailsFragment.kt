package com.catalogapptest.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.catalogapptest.R
import com.catalogapptest.databinding.FragmentDetailsBinding
import com.catalogapptest.viewmodel.ArticlesDetailViewModel


class DetailsFragment : Fragment() {

    private var articleID: Long? = null

    private lateinit var viewModel: ArticlesDetailViewModel
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            articleID = it.getLong(ARG_ARTICLE_ID)
        }
        viewModel = ViewModelProvider(this).get(ArticlesDetailViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
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
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putLong(ARG_ARTICLE_ID, articleId)
                }
            }
    }
}