package com.catalogapptest.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.catalogapptest.R
import com.catalogapptest.databinding.FragmentDetailsActivityBinding
import com.catalogapptest.viewmodel.ActivitiesDetailViewModel


class ActivitiesDetailFragment : Fragment() {

    private var activityID: Long? = null
    private var urlImage: String? = null

    private lateinit var viewModel: ActivitiesDetailViewModel
    private lateinit var binding: FragmentDetailsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            activityID = it.getLong(ARG_ACTIVITY_ID)
            urlImage = it.getString(ARG_URL_IMAGE)
        }
        viewModel = ViewModelProvider(this).get(ActivitiesDetailViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details_activity, container, false)
        binding.viewModel = viewModel
        initData()
        return binding.root
    }

    private fun initData() {
        activityID?.let { it ->
            viewModel.getDetails(it).observe(viewLifecycleOwner, {activity ->
                urlImage?.let{url ->
                    activity.thumbnail = url
                }
                binding.model = activity
            })
        }
    }

    companion object {

        private const val ARG_ACTIVITY_ID = "article_id"
        private const val ARG_URL_IMAGE = "url_image"

        @JvmStatic
        fun newInstance(articleId: Long, urlImage: String) =
            ActivitiesDetailFragment().apply {
                arguments = Bundle().apply {
                    putLong(ARG_ACTIVITY_ID, articleId)
                    putString(ARG_URL_IMAGE, urlImage)
                }
            }
    }
}