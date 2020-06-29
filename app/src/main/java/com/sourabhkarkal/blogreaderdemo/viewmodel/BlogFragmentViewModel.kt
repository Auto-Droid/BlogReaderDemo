package com.sourabhkarkal.blogreaderdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sourabhkarkal.blogreaderdemo.BlogApplication
import com.sourabhkarkal.blogreaderdemo.domain.BlogSubscriber
import com.sourabhkarkal.blogreaderdemo.model.BlogResponseDTO
import com.sourabhkarkal.blogreaderdemo.scheduler.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class BlogFragmentViewModel : ViewModel {

    constructor() : super() {
        BlogApplication.appComponent.inject(this)
    }

    @Inject lateinit var blogSubscriber: BlogSubscriber
    @Inject lateinit var scheduler: Scheduler

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val dataResponse: MutableLiveData<ArrayList<BlogResponseDTO>> = MutableLiveData()

    companion object {
        private const val TAG = "MainActivityViewModel"
    }


    fun getDataResponse(): LiveData<ArrayList<BlogResponseDTO>> {
        return dataResponse
    }


    fun callAllApi(page: Int, limit: Int) {
        compositeDisposable.add(blogSubscriber.getBlogData("$page", "$limit")
            .subscribeOn(scheduler.newThread())
            .observeOn(scheduler.mainThread())
            .subscribe { response -> dataResponse.postValue(response as ArrayList<BlogResponseDTO>?) })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}