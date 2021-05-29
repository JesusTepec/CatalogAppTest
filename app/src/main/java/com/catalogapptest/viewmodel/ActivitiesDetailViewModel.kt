package com.catalogapptest.viewmodel

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.catalogapptest.KEY_BABY_ID
import com.catalogapptest.KEY_TOKEN
import com.catalogapptest.TOKEN
import com.catalogapptest.di.DaggerAppComponent
import com.catalogapptest.model.Activity
import com.catalogapptest.model.Article
import com.catalogapptest.network.response.ActivityDetailResponse
import com.catalogapptest.network.response.ArticleDetailResponse
import com.catalogapptest.repository.ActivityRespository
import com.catalogapptest.repository.ArticleRepository
import com.pixplicity.easyprefs.library.Prefs
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import timber.log.Timber
import javax.inject.Inject

class ActivitiesDetailViewModel : ViewModel() {

    @Inject
    lateinit var repository: ActivityRespository

    var loading: ObservableField<Int> = ObservableField()

    init {
        loading.set(View.GONE)
        DaggerAppComponent.create().inject(this)
    }

    init {
        Timber.tag(this.javaClass.simpleName)
        Prefs.putString(KEY_TOKEN, TOKEN)
    }

    fun getDetails(activityId: Long) : MutableLiveData<Activity>{
        loading.set(View.VISIBLE)
        val liveDataResponse = MutableLiveData<Activity>()
        repository.getDetails(getToken(), activityId, getBabyId(), "en")
            .subscribeWith(object : DisposableSingleObserver<ActivityDetailResponse>() {
                override fun onSuccess(response: ActivityDetailResponse) {
                    loading.set(View.GONE)
                    liveDataResponse.postValue(response.data.activity)
                }

                override fun onError(e: Throwable) {
                    Timber.d(e.cause)
                    loading.set(View.GONE)
                }
            })
        return liveDataResponse
    }

    private fun getToken(): String {
        return Prefs.getString(KEY_TOKEN, "")
    }

    private fun getBabyId() = Prefs.getInt(KEY_BABY_ID, 0)

}