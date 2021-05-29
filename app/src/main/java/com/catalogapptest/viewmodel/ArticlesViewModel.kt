package com.catalogapptest.viewmodel

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.catalogapptest.KEY_BABY_ID
import com.catalogapptest.KEY_SKILL_ID
import com.catalogapptest.KEY_TOKEN
import com.catalogapptest.TOKEN
import com.catalogapptest.di.DaggerAppComponent
import com.catalogapptest.model.Article
import com.catalogapptest.network.response.ArticlesResponse
import com.catalogapptest.repository.ArticleRepository
import com.pixplicity.easyprefs.library.Prefs
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import timber.log.Timber
import javax.inject.Inject

class ArticlesViewModel : ViewModel() {

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

    fun getArticles(): MutableLiveData<List<Article>?> {
        loading.set(View.VISIBLE)
        val liveDataResponse = MutableLiveData<List<Article>?>()
        repository.getArticles(getToken(), getSkillId(), getBabyId())
            .subscribeWith(object : DisposableSingleObserver<ArticlesResponse>() {
                override fun onSuccess(response: ArticlesResponse) {
                    loading.set(View.GONE)
                    if(response.data.articles.isNotEmpty()) {
                        liveDataResponse.postValue(response.data.articles)
                    }
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    loading.set(View.GONE)
                    liveDataResponse.postValue(null)
                }
            })
        return liveDataResponse
    }

    private fun getToken(): String {
        return Prefs.getString(KEY_TOKEN, "")
    }

    private fun getSkillId() = Prefs.getInt(KEY_SKILL_ID, 0)

    private fun getBabyId() = Prefs.getInt(KEY_BABY_ID, 0)
}