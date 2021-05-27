package com.catalogapptest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.catalogapptest.KEY_TOKEN
import com.catalogapptest.TOKEN
import com.catalogapptest.model.Activity
import com.catalogapptest.network.response.ActivitiesResponse
import com.catalogapptest.repository.ActivityRespository
import com.pixplicity.easyprefs.library.Prefs
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import timber.log.Timber

class MainViewModel : ViewModel() {

    private val disposables = CompositeDisposable()
    private val repository = ActivityRespository()

    init {
        Timber.tag(this.javaClass.simpleName)
        Prefs.putString(KEY_TOKEN, TOKEN)
    }

    fun getActivities(): MutableLiveData<List<Activity>> {
        val liveDataResponse = MutableLiveData<List<Activity>>()
        disposables.add(
            repository.getActivities(getToken())
                .subscribeWith(object : DisposableSingleObserver<ActivitiesResponse>() {
                    override fun onSuccess(response: ActivitiesResponse) {
                        liveDataResponse.postValue(response.data.activities)
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
        return liveDataResponse
    }

    private fun getToken(): String {
        return Prefs.getString(KEY_TOKEN, "")
    }

}