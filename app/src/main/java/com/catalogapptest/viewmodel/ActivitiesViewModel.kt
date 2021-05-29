package com.catalogapptest.viewmodel

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.catalogapptest.KEY_TOKEN
import com.catalogapptest.TOKEN
import com.catalogapptest.di.DaggerAppComponent
import com.catalogapptest.model.Activity
import com.catalogapptest.network.response.ActivitiesResponse
import com.catalogapptest.repository.ActivityRespository
import com.pixplicity.easyprefs.library.Prefs
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import timber.log.Timber
import javax.inject.Inject

class ActivitiesViewModel : ViewModel() {

    @Inject lateinit var repository: ActivityRespository

    var loading: ObservableField<Int> = ObservableField()

    init {
        loading.set(View.GONE)
        DaggerAppComponent.create().inject(this)
    }

    init {
        Timber.tag(this.javaClass.simpleName)
    }

    fun getActivities(): MutableLiveData<List<Activity>?> {
        loading.set(View.VISIBLE)
        val liveDataResponse = MutableLiveData<List<Activity>?>()
        repository.getActivities(getToken())
            .subscribeWith(object : DisposableSingleObserver<ActivitiesResponse>() {
                override fun onSuccess(response: ActivitiesResponse) {
                    liveDataResponse.postValue(response.data.activities)
                    loading.set(View.GONE)
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
}