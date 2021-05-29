package com.catalogapptest.di

import com.catalogapptest.viewmodel.ActivitiesViewModel
import com.catalogapptest.viewmodel.ArticlesDetailViewModel
import com.catalogapptest.viewmodel.ArticlesViewModel
import dagger.Component

@Component
interface AppComponent {

    fun inject(viewModel: ActivitiesViewModel)
    fun inject(viewModel: ArticlesViewModel)
    fun inject(viewModel: ArticlesDetailViewModel)

}