package com.catalogapptest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.catalogapptest.R
import com.catalogapptest.databinding.ActivityMainBinding
import com.catalogapptest.viewmodel.MainViewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        Timber.tag(this.javaClass.simpleName)

        viewModel = MainViewModel()

        initData()
    }

    private fun initData() {
        viewModel.getActivities()
    }
}