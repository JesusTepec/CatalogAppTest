package com.catalogapptest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.catalogapptest.model.TestResponse
import com.catalogapptest.repository.TestRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import timber.log.Timber

class MainViewModel : ViewModel() {

    private val disposables = CompositeDisposable()
    private val repository = TestRepository()
    init {
        Timber.tag(this.javaClass.simpleName)
    }

    fun testRequest(): MutableLiveData<String> {
        val liveDataResponse = MutableLiveData<String>()
        disposables.add(
            repository.test()
                .subscribeWith(object: DisposableSingleObserver<List<TestResponse>>() {
                    override fun onSuccess(response: List<TestResponse>) {
                        if(response.isNotEmpty()) {
                            var ids = ""
                            for (item in response) {
                                ids += item.idOperation + "-"
                            }
                            liveDataResponse.postValue(ids)
                        }
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
        return liveDataResponse
    }

}