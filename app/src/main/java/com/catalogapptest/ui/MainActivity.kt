package com.catalogapptest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.catalogapptest.R
import com.catalogapptest.databinding.ActivityMainBinding
import com.catalogapptest.ui.fragments.ActivitiesFragment
import com.catalogapptest.ui.fragments.ArticlesFragment
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        Timber.tag(this.javaClass.simpleName)
        replaceFragment(NAME_FRAGMENT_ACTIVITIES)
        initViews()
    }

    private fun initViews() {
        binding.btnActivities.setOnClickListener{
            replaceFragment(NAME_FRAGMENT_ACTIVITIES)
        }
        binding.btnArticles.setOnClickListener {
            replaceFragment(NAME_FRAGMENT_ARTICLES)
        }
    }

    private fun replaceFragment(nameFragment: String) {
        val fragment = fragmentsFactory(nameFragment)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contentList, fragment)
        transaction.addToBackStack(null).commit()
    }

    private fun fragmentsFactory(fragmentName: String): Fragment {
        return when(fragmentName) {
            NAME_FRAGMENT_ACTIVITIES -> ActivitiesFragment()
            NAME_FRAGMENT_ARTICLES -> ArticlesFragment()
            else -> ActivitiesFragment()
        }
    }

    companion object {
        private const val NAME_FRAGMENT_ACTIVITIES = "activities"
        private const val NAME_FRAGMENT_ARTICLES = "articles"
    }
}