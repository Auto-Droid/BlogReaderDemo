package com.sourabhkarkal.blogreaderdemo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sourabhkarkal.blogreaderdemo.BlogApplication
import com.sourabhkarkal.blogreaderdemo.domain.BlogSubscriber
import com.sourabhkarkal.blogreaderdemo.model.BlogResponseDTO
import com.sourabhkarkal.blogreaderdemo.repo.storage.BlogDaoRepo
import com.sourabhkarkal.blogreaderdemo.scheduler.Scheduler
import com.sourabhkarkal.blogreaderdemo.utils.ContextProvider
import com.sourabhkarkal.blogreaderdemo.utils.Utils
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class BlogFragmentViewModel : ViewModel {

    constructor() : super() {
        BlogApplication.appComponent.inject(this)
    }

    @Inject lateinit var blogSubscriber: BlogSubscriber
    @Inject lateinit var scheduler: Scheduler
    @Inject lateinit var blogDaoRepo: BlogDaoRepo

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val dataResponse: MutableLiveData<ArrayList<BlogResponseDTO>> = MutableLiveData()
    private val networkStatusResponse: MutableLiveData<Boolean> = MutableLiveData()

    companion object {
        private const val TAG = "MainActivityViewModel"
    }


    fun getDataResponse(): LiveData<ArrayList<BlogResponseDTO>> {
        return dataResponse
    }

    fun getNetworkStatusResponse(): LiveData<Boolean> {
        return networkStatusResponse
    }




    fun callAllApi(page: Int, limit: Int) {
        if(Utils.isNetworkAvailable(ContextProvider.contextProvider?.provideContext()!!)) {
            // Online Loading
            compositeDisposable.add(blogSubscriber.getBlogData("$page", "$limit")
                .subscribeOn(scheduler.newThread())
                .observeOn(scheduler.mainThread())
                .subscribe({ response -> dataResponse.postValue(response as ArrayList<BlogResponseDTO>?) }
                    , { error -> Log.e(TAG, error.message) })
            )
        } else { // Offline Loading
            blogDaoRepo.getAllArticle()?.subscribeOn(scheduler.newThread())
                ?.observeOn(scheduler.mainThread())?.subscribe { it->
                    dataResponse.postValue(it as ArrayList<BlogResponseDTO>?)
                    networkStatusResponse.postValue(true)
                }?.let { it1 -> compositeDisposable.add(it1) }
        }

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}