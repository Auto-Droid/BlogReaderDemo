package com.sourabhkarkal.blogreaderdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sourabhkarkal.blogreaderdemo.domain.BlogSubscriber
import com.sourabhkarkal.blogreaderdemo.model.BlogResponseDTO
import com.sourabhkarkal.blogreaderdemo.scheduler.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainActivityViewModel : ViewModel() {

    @Inject
    lateinit var blogSubscriber: BlogSubscriber
    @Inject
    lateinit var scheduler: Scheduler

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val dataResponse: MutableLiveData<BlogResponseDTO> = MutableLiveData()

    companion object {
        private const val TAG = "MainActivityViewModel"
    }


    fun getDataResponse(): LiveData<BlogResponseDTO> {
        return dataResponse
    }


    fun callAllApi() {
        compositeDisposable.add(blogSubscriber.getBlogData("1", "10")
            .subscribeOn(scheduler.newThread())
            .observeOn(scheduler.mainThread())
            .subscribe { response -> dataResponse.postValue(response) })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}