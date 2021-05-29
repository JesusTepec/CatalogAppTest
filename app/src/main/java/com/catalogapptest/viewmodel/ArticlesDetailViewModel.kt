package com.catalogapptest.viewmodel

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.catalogapptest.KEY_TOKEN
import com.catalogapptest.TOKEN
import com.catalogapptest.di.DaggerAppComponent
import com.catalogapptest.model.Article
import com.catalogapptest.network.response.ArticleDetailResponse
import com.catalogapptest.repository.ArticleRepository
import com.pixplicity.easyprefs.library.Prefs
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import timber.log.Timber
import javax.inject.Inject

class ArticlesDetailViewModel : ViewModel() {

    @Inject
    lateinit var repository: ArticleRepository

    var loading: ObservableField<Int> = ObservableField()

    init {
        loading.set(View.GONE)
        DaggerAppComponent.create().inject(this)
    }

    init {
        Timber.tag(this.javaClass.simpleName)
        Prefs.putString(KEY_TOKEN, TOKEN)
    }

    fun getDetails(articleId: Long) : MutableLiveData<Article>{
        loading.set(View.VISIBLE)
        val liveDataResponse = MutableLiveData<Article>()
        repository.getDetails(getToken(), articleId)
            .subscribeWith(object : DisposableSingleObserver<ArticleDetailResponse>() {
                override fun onSuccess(response: ArticleDetailResponse) {
                    loading.set(View.GONE)
                    liveDataResponse.postValue(response.data.article)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    loading.set(View.GONE)
                }
            })
        return liveDataResponse
    }

    private fun getToken(): String {
        return Prefs.getString(KEY_TOKEN, "")
    }

}