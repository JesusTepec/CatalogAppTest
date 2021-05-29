package com.catalogapptest.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.catalogapptest.R
import com.catalogapptest.adapters.ActivitiesAdapter
import com.catalogapptest.databinding.FragmentActivitiesBinding
import com.catalogapptest.model.Activity
import com.catalogapptest.viewmodel.ActivitiesViewModel


class ActivitiesFragment : Fragment() {

    private lateinit var viewModel: ActivitiesViewModel
    private lateinit var binding: FragmentActivitiesBinding
    private lateinit var adapter: ActivitiesAdapter

    private var activityList = ArrayList<Activity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ActivitiesViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_activities, container, false)
        binding.viewModel = viewModel
        initRecyclerView()
        initData()
        return binding.root
    }

    /**
     * init for observables
     */
    private fun initData() {
        viewModel.getActivities().observe(viewLifecycleOwner, {activities ->
            activities?.let {
                activityList.addAll(it)
                adapter.updateList(activityList)
            }
        })
    }

    /**
     * configure for activity list
     */
    private fun initRecyclerView() {
        context?.let {
            binding.rvActivities.layoutManager = LinearLayoutManager(it)
            adapter = ActivitiesAdapter(it, activityList)
            binding.rvActivities.adapter = adapter
        }
    }
}