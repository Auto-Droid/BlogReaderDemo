package com.sourabhkarkal.blogreaderdemo.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sourabhkarkal.blogreaderdemo.R
import com.sourabhkarkal.blogreaderdemo.model.BlogResponseDTO
import com.sourabhkarkal.blogreaderdemo.viewmodel.BlogFragmentViewModel

class BlogFragment : Fragment()  {

    private var blogViewModel: BlogFragmentViewModel? = null
    private lateinit var resultAppList : ArrayList<BlogResponseDTO>;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_blog, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        blogViewModel = ViewModelProviders.of(this).get(BlogFragmentViewModel::class.java)
        blogViewModel?.getDataResponse()?.observe(this, observerPostLiveData)
        blogViewModel?.callAllApi(1, 10)

    }

    private val observerPostLiveData = Observer<List<BlogResponseDTO>> { result ->
        resultAppList = result as ArrayList<BlogResponseDTO>;

    }


}